package com.apps4you.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.apps4you.entities.Contact;
import com.apps4you.repositories.contactRepository;

@Service
public class ContactService {

	@Autowired
	private contactRepository contactRepository;

	public Boolean saveContact(@RequestBody Contact contact) {
		Optional<Contact> exists = contactRepository.findByEmail(contact.getEmail());
		boolean isUserExist = exists.isPresent();
		if (!isUserExist) {
			contactRepository.save(contact);
		}
		return !isUserExist;
	}

	public void deleteContactById(String id) {
		contactRepository.deleteById(id);
	}

	public ResponseEntity<Contact> updateContact(String id, Contact contact) {
		Optional<Contact> contactData = contactRepository.findById(id);

		if (contactData.isPresent()) {
			Contact _contact = contactData.get();
			_contact.setFullName(contact.getFullName());
			_contact.setEmail(contact.getEmail());
			_contact.setSubject(contact.getSubject());
			_contact.setMessage(contact.getMessage());

			return new ResponseEntity<>(contactRepository.save(_contact), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	public List<Contact> getAllContacts() {
		return contactRepository.findAll();
	}

}
