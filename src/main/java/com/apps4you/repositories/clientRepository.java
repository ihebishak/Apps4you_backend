package com.apps4you.repositories;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.apps4you.entities.Client;


@Repository
public interface clientRepository extends MongoRepository<Client, String> {
	Optional<Client> findByEmailClient(String email);

}
