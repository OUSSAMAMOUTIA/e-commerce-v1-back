package com.example.ecommercev1.dao;

import com.example.ecommercev1.entites.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
@CrossOrigin("*")
@RepositoryRestResource
public interface ProductRepository extends JpaRepository<Product, Long> {
    @RestResource(path = "/selectedproduct")
    public List<Product> findBySelectedIsTrue();

    @RestResource(path = "searchbyname")
    public List<Product> findByNameContains(@Param("name") String name);
}
