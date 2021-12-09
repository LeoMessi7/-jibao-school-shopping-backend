package com.t09.jibao.Controller;

/**
 * @author Yuanhao Pei
 * @date 2021/12/7
 */

import com.t09.jibao.domain.Captcha;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.t09.jibao.service.CaptchaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

@RestController
public class CaptchaController {

    @Autowired
    private DefaultKaptcha defaultKaptcha;

    @Autowired
    private CaptchaService captchaService;

    @Autowired
    private HttpServletRequest request;

    /**
     * get image captcha
     * @param response image
     */
    @GetMapping("/getImageCaptcha")
    public void getImageCaptcha(HttpServletResponse response) throws IOException {
        System.out.println(request.getSession().getAttribute("image_id"));
        // create captcha test
        String capText = defaultKaptcha.createText();
        if(request.getSession().getAttribute("image_id") != null) {
            Long image_id = (long) request.getSession().getAttribute("image_id");
            captchaService.deleteCaptcha(image_id);
        }
        // create captcha image
        BufferedImage image = defaultKaptcha.createImage(capText);
        Captcha captcha = captchaService.createImageCaptcha(capText);
        // create captcha
        request.getSession().setAttribute("image_id", captcha.getId());
        System.out.println(request.getSession().getAttribute("image_id"));
        response.setHeader("Cache-Control", "no-store");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");
        ImageIO.write(image, "jpg", response.getOutputStream());
    }
}
