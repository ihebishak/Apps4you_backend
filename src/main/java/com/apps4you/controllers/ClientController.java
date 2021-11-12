package com.apps4you.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.apps4you.entities.Client;
import com.apps4you.services.ClientService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class ClientController {

	@Autowired
	private ClientService clientService;

	@RequestMapping(method = RequestMethod.POST, value = "/saveClient")
	public Boolean saveClient(@RequestBody Client client) {
		return clientService.saveClient(client);

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/deleteClient/{id}")
	void deleteClientById(@PathVariable String id) {
		clientService.deleteClientById(id);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "updateClient/{id}")
	public ResponseEntity<Client> updateClient(@PathVariable("id") String id, @RequestBody Client client) {
		return clientService.updateClient(id, client);
	}

	@RequestMapping(value = "clients", method = RequestMethod.GET)
	public List<Client> fetchAllClients() {
		return clientService.getAllClients();
	}

}
