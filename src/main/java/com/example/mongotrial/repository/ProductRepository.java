package com.example.mongotrial.repository;

import com.example.mongotrial.Model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {

}
