package com.example.ecommercev1.web;

import com.example.ecommercev1.dao.ProductRepository;
import com.example.ecommercev1.entites.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.Files;
import java.nio.file.Paths;

@RestController
public class CatalogueRestController {
    @Autowired
    private ProductRepository productRepository;

    @GetMapping(path = "productimage/{id}", produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] getPhoto(@PathVariable("id") Long id) throws Exception {
        Product product = productRepository.findById(id).get();
        return Files.readAllBytes(Paths.get(System.getProperty("user.home") + "/Ecom/Products/" + product.getPhotoName()));
    }
}
