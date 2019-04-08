package com.Mijnqien.Ondersteunend;


import java.io.IOException;
import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;

@Configuration
public class MailBean {
    @Bean
    public Properties mailServer() {

        Properties p = new Properties();

        p.put("mail.smtp.auth", true);
        p.put("mail.smtp.starttls.enable", true);
        p.put("mail.smtp.host", "smtp.live.com");
        p.put("mail.smtp.port", "587");
        return p;

    }
    
 
}
