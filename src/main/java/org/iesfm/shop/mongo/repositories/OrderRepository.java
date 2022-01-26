package org.iesfm.shop.mongo.repositories;

import org.iesfm.shop.mongo.pojos.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends MongoRepository<Order, Integer> {
    List<Order> findByClientNif(String nif);
}
