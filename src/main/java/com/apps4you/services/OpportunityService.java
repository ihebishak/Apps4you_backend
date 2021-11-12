package com.apps4you.services;

import static java.nio.file.Files.copy;
import static java.nio.file.Paths.get;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.apps4you.entities.Opportunity;
import com.apps4you.repositories.opportunityRepository;

@Service
public class OpportunityService {

	@Autowired
	private opportunityRepository opportunityRepository;

	public void saveOpportunity(@RequestBody Opportunity opportunity) {
		opportunityRepository.save(opportunity);
	}

	public void deleteOpportunityById(String id) {
		opportunityRepository.deleteById(id);
	}

	public List<Opportunity> listOpportunity() {
		return (List<Opportunity>) opportunityRepository.findAll();
	}

	public Optional<Opportunity> getOpportunityById(@PathVariable String Id) {
		return opportunityRepository.findById(Id);
	}

	public ResponseEntity<Opportunity> updateOpportunity(String id, Opportunity opportunity) {
		Optional<Opportunity> opportunityData = opportunityRepository.findById(id);

		if (opportunityData.isPresent()) {
			Opportunity _opportunity = opportunityData.get();
			_opportunity.setTitle(opportunity.getTitle());
			_opportunity.setDate(opportunity.getDate());
			_opportunity.setDescription(opportunity.getDescription());
			_opportunity.setLocation(opportunity.getLocation());
			_opportunity.setRequirements(opportunity.getRequirements());
			_opportunity.setImg_location(opportunity.getImg_location());
			_opportunity.setSh_description(opportunity.getSh_description());
			_opportunity.setTechnologies(opportunity.getTechnologies());

			return new ResponseEntity<>(opportunityRepository.save(_opportunity), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<List<String>> uploadFiles(@RequestParam("files") List<MultipartFile> multipartFiles)
			throws IOException {
		Opportunity op = new Opportunity();
		final String DIRECTORY = System.getProperty("user.home") + "/Downloads/uploads/";
		List<String> filenames = new ArrayList<>();
		for (MultipartFile file : multipartFiles) {
			String filename = StringUtils.cleanPath(file.getOriginalFilename());
			Path fileStorage = get(DIRECTORY, filename).toAbsolutePath().normalize();
			copy(file.getInputStream(), fileStorage, REPLACE_EXISTING);
			filenames.add(filename);
			op.setImg_location(fileStorage.toString());
			opportunityRepository.save(op);
		}
		return ResponseEntity.ok().body(filenames);
	}

}
