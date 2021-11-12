package com.apps4you.controllers;

import java.io.UnsupportedEncodingException;
import javax.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.apps4you.services.MailService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class MailController {

	@Autowired
	private MailService mailService;

	@PostMapping("/send")
	public String send(@RequestParam("file") MultipartFile multipartFile, String fullname, String email, String subject,
			String comment) throws MessagingException, UnsupportedEncodingException {

		return mailService.send(multipartFile, fullname, email, subject, comment);

	}

	@PostMapping("/contactUs")
	public String contact(String fullname, String email, String subject, String comment)
			throws MessagingException, UnsupportedEncodingException {

		return mailService.contact(fullname, email, subject, comment);
	}

}
