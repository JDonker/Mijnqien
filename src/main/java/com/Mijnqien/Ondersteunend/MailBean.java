package com.Mijnqien.Ondersteunend;


import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MailBean {
    @Bean
    public Properties QienMail() {

        Properties p = new Properties();

        p.put("mail.smtp.auth", true);
        p.put("mail.smtp.starttls.enable", true);
        p.put("mail.smtp.host", "smtp.live.com");
        p.put("mail.smtp.port", "587");
        return p;

    }
    
}
