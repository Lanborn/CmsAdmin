package com.coderlan.controller;

import cn.hutool.core.map.MapUtil;
import com.coderlan.common.lang.Const;
import com.coderlan.common.lang.Result;
import com.coderlan.entity.CmsUser;
import com.coderlan.service.CmsUserService;
import com.google.code.kaptcha.Producer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.Principal;
import java.util.UUID;

@Slf4j
@RestController
public class AuthController extends BaseController {
    @Autowired
    private Producer producer;
    /**
     * 图片验证码
     */
    @GetMapping("/captcha")
    public Result captcha() throws IOException {

        String code = producer.createText();
        String key = UUID.randomUUID().toString();

        // 测试
        key = "aaaaa";
        code = "11111";


        BufferedImage image = producer.createImage(code);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(image, "jpg", outputStream);

        BASE64Encoder encoder = new BASE64Encoder();
        String str = "data:image/jpeg;base64,";

        String base64Img = str + encoder.encode(outputStream.toByteArray());

        // 存储到redis中
        redisUtil.hset(Const.CAPTCHA_KEY,key, code,120);


        return Result.succ(
                MapUtil.builder()
                        .put("token", key)
                        .put("captchaImg", base64Img)
                        .build()
        );

    }

    /**
     * 获取用户信息接口
     * @param principal
     * @return
     */
    @GetMapping("/cms/userInfo")
    public Result userInfo(Principal principal) {

        CmsUser cmsUser = cmsUserService.getByUsername(principal.getName());

        return Result.succ(MapUtil.builder()
                .put("id", cmsUser.getId())
                .put("username", cmsUser.getUsername())
                .put("realname", cmsUser.getRealname())
                .put("cellphone", cmsUser.getCellphone())
                .put("avatar", cmsUser.getAvatar())
                .put("created", cmsUser.getCreateTime())
                .map()
        );
    }
}
