package com.apps4you.controllers;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.apps4you.entities.Opportunity;
import com.apps4you.services.OpportunityService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class OpportunityController {

	@Autowired
	private OpportunityService opportunityService;

	@RequestMapping(method = RequestMethod.POST, value = "/saveOpportunity")
	public void saveOpportunity(@RequestBody Opportunity opportunity) {
		opportunityService.saveOpportunity(opportunity);
	}

	@DeleteMapping("/deleteOpportunity/{id}")
	void deleteOpportunityById(@PathVariable String id) {
		opportunityService.deleteOpportunityById(id);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/opportunities")
	public List<Opportunity> listOpportunity() {
		return opportunityService.listOpportunity();
	}

	@RequestMapping(value = "/opportunity/{Id}", method = RequestMethod.GET)
	public Optional<Opportunity> getOpportunityById(@PathVariable String Id) {
		return opportunityService.getOpportunityById(Id);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/updateOpportunity/{id}")
	public ResponseEntity<Opportunity> updateOpportunity(@PathVariable("id") String id,
			@RequestBody Opportunity opportunity) {
		return opportunityService.updateOpportunity(id, opportunity);
	}

	@PostMapping("/upload")
	public ResponseEntity<List<String>> uploadFiles(@RequestParam("files") List<MultipartFile> multipartFiles)
			throws IOException {
		return opportunityService.uploadFiles(multipartFiles);
	}
}
