package org.iesfm.shop.mongo.repositories;

import org.iesfm.shop.mongo.pojos.Article;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends MongoRepository<Article, Integer> {
}
