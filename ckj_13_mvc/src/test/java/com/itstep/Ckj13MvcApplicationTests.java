package com.itstep;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.itstep.service.MailService;

@SpringBootTest
class Ckj13MvcApplicationTests {
	private MailService mailService;
	
	@Autowired
	public Ckj13MvcApplicationTests(MailService mailService) {
		this.mailService = mailService;
	}
	
	@Test
	void SendMailTest() {
		SimpleMailMessage mail = new SimpleMailMessage();
		String s = "It's text is test ";
		mail.setSubject("New job");
		mail.setFrom("java@mail.ru");
		mail.setTo("reciver@mail.com");
		mail.setText("I have for you free vacancion, come to me at 5 oclock!, i will waiting impatiently!");
		System.out.println(Thread.currentThread().getName());
		mailService.sendMail(mail);
		System.out.println("Succefuly!");
	}
	
	
	@Test
	@Disabled
	void testPasswordEncoder() {
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		String str = "111";
		System.out.println(encoder.encode(str));
	}

}
