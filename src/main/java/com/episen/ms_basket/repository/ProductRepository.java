package com.episen.ms_basket.repository;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.episen.ms_basket.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductRepository {

    private Map<String, Product> productInMemory = new HashMap<String,Product>();


    public void add(Product product) {
        System.out.println("add product -> gtin : " + product.getGtin());

        productInMemory.put( product.getGtin(),product);
    }

    public Product getProductByGtin(String gtin) {
        System.out.println("Get product by -> gtin :  "+ gtin);

        return productInMemory.get(gtin);
    }

    public List<Product> getAll(){
        System.out.println("Get All -> count : " + productInMemory.size());

        return new ArrayList<>(productInMemory.values());
    }

    public void update(Product productToUpdate) {

        productInMemory.put(productToUpdate.getGtin(), productToUpdate);
    }

    public void delete(String gtin) {

        productInMemory.remove(gtin);
    }
}
