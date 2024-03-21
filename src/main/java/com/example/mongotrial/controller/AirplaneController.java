package com.example.mongotrial.controller;

import com.example.mongotrial.Model.Product;
import com.example.mongotrial.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class AirplaneController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/airplane")
    public String listProducts(Model model) {
        return "airplane";
    }
}