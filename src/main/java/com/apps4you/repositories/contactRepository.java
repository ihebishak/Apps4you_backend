package com.apps4you.repositories;

import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.apps4you.entities.Contact;

@Repository
public interface contactRepository extends MongoRepository<Contact, String> {
	Optional<Contact> findByEmail(String email);

}
