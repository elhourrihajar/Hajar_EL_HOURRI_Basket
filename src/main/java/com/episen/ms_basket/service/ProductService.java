package com.episen.ms_basket.service;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import com.episen.ms_basket.model.Product;
import com.episen.ms_basket.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;



@Service
public class ProductService{

    @Autowired
    private ProductRepository repository;


    public void create_product(Product product) {

        if(StringUtils.isEmpty(product.getGtin()) || StringUtils.isEmpty(product.getLabel())) {
            throw new RuntimeException("You came across a product exception");
        }

        if(repository.getProductByGtin(product.getGtin())!= null) {
            throw new RuntimeException("This product already exists");
        }
        repository.add(product);
    }


    public List<Product> getAll(){
        return repository.getAll();
    }

    public void update_product(Product productToUpdate) {

        if(null == repository.getProductByGtin(productToUpdate.getGtin())) {
            throw new RuntimeException("This product is not found");
        }

        repository.update(productToUpdate);
    }

    public void delete_product(String gtin) {

        if(null == repository.getProductByGtin(gtin)) {
            throw new RuntimeException("This product is not found");
        }

        repository.delete(gtin);
    }


    public Product getProductByGtin(String gtin) {
        return repository.getProductByGtin(gtin);
    }



}
