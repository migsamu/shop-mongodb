package org.iesfm.shop.mongo.repositories;

import org.iesfm.shop.mongo.pojos.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface OrderRepository extends MongoRepository<Order, Integer> {
    List<Order> findByClientNif(String nif);
}
