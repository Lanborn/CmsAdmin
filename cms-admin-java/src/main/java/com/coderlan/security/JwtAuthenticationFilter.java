package com.coderlan.security;

import cn.hutool.core.util.StrUtil;
import com.coderlan.entity.CmsUser;
import com.coderlan.service.CmsUserService;
import com.coderlan.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthenticationFilter extends BasicAuthenticationFilter {

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    UserDetailServiceImpl userDetailService;

    @Autowired
    CmsUserService cmsUserService;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        String jwt = request.getHeader(jwtUtils.getHeader());
        if (StrUtil.isBlankOrUndefined(jwt)) {
            chain.doFilter(request,response);
            return;
        }

        Claims claimByToken = jwtUtils.getClaimByToken(jwt);
        if (claimByToken == null) {
            throw new JwtException("token 异常");
        }
        if(jwtUtils.isTokenExpired(claimByToken)) {
            throw new JwtException("token已过期");
        }

        String username = claimByToken.getSubject();

        CmsUser cmsUser = cmsUserService.getByUsername(username);

        // 获取用户权限信息
        UsernamePasswordAuthenticationToken token
                = new UsernamePasswordAuthenticationToken(username, null,userDetailService.getUserAuthority(cmsUser.getId()));

        SecurityContextHolder.getContext().setAuthentication(token);

        chain.doFilter(request,response);

    }
}
