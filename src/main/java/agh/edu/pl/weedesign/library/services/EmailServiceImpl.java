package agh.edu.pl.weedesign.library.services;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
public class EmailServiceImpl {

    private final JavaMailSender emailSender;

    public EmailServiceImpl() {
        this.emailSender = getJavaMailSender();
    }

    public void sendSimpleMessage(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("noreply@baeldung.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        (new SendEmail(emailSender, message)).start();
    }

    private JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.poczta.onet.pl");
        mailSender.setPort(587);

        mailSender.setUsername("apkabiblioteka@onet.pl");
        mailSender.setPassword("Apkabiblioteka1");

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");


        return mailSender;
    }
}

class SendEmail extends Thread{
    JavaMailSender emailSender;
    SimpleMailMessage message;

    SendEmail(JavaMailSender sender, SimpleMailMessage message){
        this.emailSender = sender;
        this.message = message;
    }

    @Override
    public void run(){
        this.emailSender.send(this.message);
    }
}

