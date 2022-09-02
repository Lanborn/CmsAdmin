package com.coderlan.security;

import com.coderlan.entity.CmsUser;
import com.coderlan.service.CmsUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

	@Autowired
	CmsUserService cmsUserService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		CmsUser cmsUser = cmsUserService.getByUsername(username);
		if (cmsUser == null) {
			throw new UsernameNotFoundException("用户名或密码不正确");
		}
		return new AccountUser(cmsUser.getId(), cmsUser.getUsername(), cmsUser.getPassword(), getUserAuthority(cmsUser.getId()));
	}

	/**
	 * 获取用户权限信息（角色、菜单权限）
	 * @param userId
	 * @return
	 */
	public List<GrantedAuthority> getUserAuthority(Long userId){

		// 角色(ROLE_admin)、菜单操作权限 cms:user:list
		String authority = cmsUserService.getUserAuthorityInfo(userId);  // ROLE_admin,ROLE_normal,cms:user:list,....

		return AuthorityUtils.commaSeparatedStringToAuthorityList(authority);
	}
}
