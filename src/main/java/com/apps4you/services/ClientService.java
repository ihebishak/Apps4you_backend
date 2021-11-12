package com.apps4you.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import com.apps4you.repositories.clientRepository;
import com.apps4you.entities.Client;

@Service
public class ClientService {
	
	@Autowired
	private clientRepository clientRepository;
	

	public Boolean saveClient(@RequestBody Client client) {
		Optional<Client> exists = clientRepository.findByEmailClient(client.getEmailClient());
		boolean isUserExist = exists.isPresent();
		if (!isUserExist) {
			clientRepository.save(client);
		}
		return !isUserExist;
	}

	public void deleteClientById(String id) {
		clientRepository.deleteById(id);
	}

	public ResponseEntity<Client> updateClient(String id, Client client) {
		Optional<Client> clientData = clientRepository.findById(id);

		if (clientData.isPresent()) {
			Client _client = clientData.get();
			_client.setFullName(client.getFullName());
			_client.setEmailClient(client.getEmailClient());
			_client.setSubject(client.getSubject());
			_client.setComment(client.getComment());
			_client.setStatus(client.getStatus());
			

			return new ResponseEntity<>(clientRepository.save(_client), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	public List<Client> getAllClients() {
		return clientRepository.findAll();
	}

}
