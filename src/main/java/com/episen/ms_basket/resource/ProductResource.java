package com.episen.ms_basket.resource;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.annotation.PostConstruct;


import com.episen.ms_basket.model.JwtResponse;
import com.episen.ms_basket.model.Product;
import com.episen.ms_basket.security.JwtUtil;
import com.episen.ms_basket.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="products",produces = {"application/json"})
public class ProductResource {

    @Autowired
    private ProductService productService;

    @Autowired
    private JwtUtil jwt_util;
 
    private Product current_product;


    @PostConstruct
	public void init() {
		try {
			jwt_util.open_jwt_prop();
			jwt_util.update_jwt_prop("notAdmin");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    //Methods to execute an operation over products (create,update,delete)

    @PostMapping("create")
    public ResponseEntity<Product> create_product(@RequestBody Product product) throws Exception {
    	System.out.print(jwt_util.get_jwt_prop());
    	if(jwt_util.get_jwt_prop()=="notAdmin")
    	{
    		throw new RuntimeException("Operation not allowed! Only admin can create products");
    	}
        productService.create_product(product);
        return new ResponseEntity<Product>(product, HttpStatus.CREATED);
    }

    @PutMapping("update")
    public void update_product(@RequestBody Product product) throws Exception {
    	if(jwt_util.get_jwt_prop()=="notAdmin")
    	{
    		throw new RuntimeException("Operation not allowed! Only admin can update products");
    	}
        productService.update_product(product);
    }


    @DeleteMapping("delete/{gtin}")
    public void delete_product(@PathVariable("gtin") String gtin) throws Exception {
    	if(jwt_util.get_jwt_prop()=="notAdmin")
    	{
    		throw new RuntimeException("Operation not allowed! Only admin can delete products");
    	}
        productService.delete_product(gtin);
    }


    //Methods to get an information about products (list of total products, a certain product)

    @GetMapping("list")
    public ResponseEntity<List<Product>> getAll(){

        return new ResponseEntity<List<Product>>(productService.getAll(), HttpStatus.OK);

    }

    @GetMapping("get/{gtin}")
    public ResponseEntity<Product> get_product(@PathVariable("gtin") String gtin) {

        return new ResponseEntity<Product>(productService.getProductByGtin(gtin), HttpStatus.OK);
    }

}
