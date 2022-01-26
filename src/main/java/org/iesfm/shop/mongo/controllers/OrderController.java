package org.iesfm.shop.mongo.controllers;

import org.iesfm.shop.mongo.pojos.Item;
import org.iesfm.shop.mongo.pojos.Order;
import org.iesfm.shop.mongo.repositories.ArticleRepository;
import org.iesfm.shop.mongo.repositories.ClientRepository;
import org.iesfm.shop.mongo.repositories.OrderRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class OrderController {

    private OrderRepository orderRepository;
    private ClientRepository clientRepository;
    private ArticleRepository articleRepository;

    public OrderController(OrderRepository orderRepository, ClientRepository clientRepository, ArticleRepository articleRepository) {
        this.orderRepository = orderRepository;
        this.clientRepository = clientRepository;
        this.articleRepository = articleRepository;
    }

    @RequestMapping(method = RequestMethod.POST, path = "/orders")
    public void insert(@RequestBody Order order) {
        if (orderRepository.existsById(order.getId())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Ya existe el pedido");
        }

        if (clientRepository.existsById(order.getClientNif())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No existe el cliente");
        }

        if (order.getItems().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Debe haber algún item");
        }

        for (Item item : order.getItems()) {
            if (articleRepository.existsById(item.getArticleId())) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No existe el artículo " + item.getArticleName());
            }
        }

        orderRepository.save(order);

    }

    @RequestMapping(method = RequestMethod.GET, path = "/clients/{nif}/orders")
    public List<Order> listByNif(@PathVariable("nif") String nif) {

        if (!clientRepository.existsById(nif)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No existe el cliente");
        } else {
            return orderRepository.findByClientNif(nif);
        }
    }

}
