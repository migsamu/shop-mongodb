package org.iesfm.shop.mongo.controllers;

import org.iesfm.shop.mongo.pojos.Article;
import org.iesfm.shop.mongo.repositories.ArticleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class ArticleController {

    private ArticleRepository articleRepository;

    public ArticleController(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @RequestMapping(method = RequestMethod.POST, path = "/articles")
    public void Insert(@RequestBody Article article) {
        if (articleRepository.existsById(article.getId())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "El articuloo ya existe");
        } else {
            articleRepository.save(article);
        }
    }

    @RequestMapping(method = RequestMethod.GET, path = "/articles")
    public List<Article> listArticles() {
        return articleRepository.findAll();
    }

}
