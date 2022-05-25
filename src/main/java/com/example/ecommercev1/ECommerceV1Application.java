package com.example.ecommercev1;

import com.example.ecommercev1.dao.CategoryRepository;
import com.example.ecommercev1.dao.ProductRepository;
import com.example.ecommercev1.entites.Category;
import com.example.ecommercev1.entites.Product;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import java.util.Random;

@SpringBootApplication
public class ECommerceV1Application implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(ECommerceV1Application.class, args);
    }

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private RepositoryRestConfiguration repositoryRestConfiguration;

    @Override
    public void run(String... args) throws Exception {
        repositoryRestConfiguration.exposeIdsFor(Product.class, Category.class);
        categoryRepository.save(new Category(null, "Computers", null, null, null));
        categoryRepository.save(new Category(null, "Printers", null, null, null));
        categoryRepository.save(new Category(null, "Smart phone", null, null, null));
        Random random = new Random();
        categoryRepository.findAll().forEach(category -> {
            for (int i = 0; i < 10; i++) {
                Product product = new Product();
                product.setName(RandomString.make(18));
                product.setDescription(RandomString.make(18));
                product.setAvailable(random.nextBoolean());
                product.setPromotion(random.nextBoolean());
                product.setSelected(random.nextBoolean());
                product.setCurrentPrice(100 + random.nextInt(10000));
                product.setCategory(category);
                product.setPhotoName("unknown.png");
                productRepository.save(product);
            }

        });
    }
}
