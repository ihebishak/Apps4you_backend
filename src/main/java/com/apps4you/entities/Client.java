package com.apps4you.entities;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
@Document(collection = "Client")
public class Client {
	@Id
	private String id;
	private String fullName;

	private String emailClient;
	private String subject;
	private String comment;
	private String status;

	

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmailClient() {
		return emailClient;
	}

	public void setEmailClient(String emailClient) {
		this.emailClient = emailClient;
	}

	public String getComment() {
		return comment;
	}

	public Client(String fullName, String emailClient, String subject, String comment) {
		super();
		this.fullName = fullName;
		this.emailClient = emailClient;
		this.subject = subject;
		this.comment = comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}
