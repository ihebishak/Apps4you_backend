package com.apps4you.services;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Service
public class MailService {

	@Autowired
	private JavaMailSender mailSender;

	public String send(@RequestParam("file") MultipartFile multipartFile, String fullname, String email, String subject,
			String comment) throws MessagingException, UnsupportedEncodingException {

		String full_name = fullname;
		String emaile = email;
		String subjecte = subject;
		String content = comment;
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		String mailSubject = subjecte;
		String mailContent = "<p><b>Nom et Prénom du candidat : </b>" + full_name + "</p>";
		mailContent += "<p><b>E-mail du candidat : </b>" + emaile + "</p>";
		mailContent += "<p><b>Commentaire du candidat :</b>" + content + "</p>";
		helper.setFrom("ihebishak8@gmail.com");
		helper.setTo("recrutement@apps4you.org");
		helper.setSubject(mailSubject);
		helper.setText(mailContent, true);

		if (!multipartFile.isEmpty()) {
			String filename = StringUtils.cleanPath(multipartFile.getOriginalFilename());
			InputStreamSource source = new InputStreamSource() {
				@Override
				public InputStream getInputStream() throws IOException {
					return multipartFile.getInputStream();
				}
			};
			helper.addAttachment(filename, source);
		}
		mailSender.send(message);
		return "message postuler sent";
	}

	public String contact(String fullname, String email, String subject, String comment)
			throws MessagingException, UnsupportedEncodingException {

		String full_name = fullname;
		String emaile = email;
		String subjecte = subject;
		String content = comment;
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		String mailSubject = subjecte;
		String mailContent = "<p><b>Nom et Prénom : </b>" + full_name + "</p>";
		mailContent += "<p><b>E-mail : </b>" + emaile + "</p>";
		mailContent += "<p><b>Message :</b>" + content + "</p>";
		helper.setFrom("ihebishak8@gmail.com");
		helper.setTo("info@apps4you.org");
		helper.setSubject(mailSubject);
		helper.setText(mailContent, true);

		mailSender.send(message);
		return "message contact us sent";
	}
}
