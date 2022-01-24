package org.iesfm.shop.mongo.controllers;

import org.iesfm.shop.mongo.pojos.Order;
import org.iesfm.shop.mongo.repositories.ClientRepository;
import org.iesfm.shop.mongo.repositories.OrderRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {

    private OrderRepository orderRepository;
    private ClientRepository clientRepository;

    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @RequestMapping(method = RequestMethod.POST, path = "/orders")
    public void insert(@RequestBody Order order) {
        orderRepository.save(order);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/clients/{nif}/orders")
    public List<Order> listByNif(@PathVariable("nif") String nif) {
       return orderRepository.findByClientNif(nif);
    }

}
