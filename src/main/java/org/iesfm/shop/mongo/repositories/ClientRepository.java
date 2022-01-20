package org.iesfm.shop.mongo.repositories;

import org.iesfm.shop.mongo.pojos.Client;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

@Component
public interface ClientRepository extends MongoRepository<Client, String> {
}
