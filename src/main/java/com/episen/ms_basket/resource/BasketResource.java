package com.episen.ms_basket.resource;
import com.episen.ms_basket.model.Basket;
import com.episen.ms_basket.security.JwtUtil;
import com.episen.ms_basket.service.BasketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping(value="baskets",produces = {"application/json"})
public class BasketResource {

    @Autowired
    private BasketService basketService;


    @Autowired
    private JwtUtil jwt;

    //Methods to execute an operation over users (create,update,delete)

    @PostMapping("create")
    public ResponseEntity<Basket> create_basket(@RequestBody Basket basket) throws Exception {
        basketService.create_basket(basket);
        return new ResponseEntity<Basket>(basket, HttpStatus.CREATED);
    }

    @PutMapping("update")
    public void update_user(@RequestBody Basket basket) {

        basketService.update_basket(basket);
    }


    @DeleteMapping("delete/{id_basket}")
    public void delete_user(@PathVariable("id_basket") String basket) {

        basketService.delete_basket(basket);
    }


    //Methods to get an information about users (list of total users, a certain user)

    @GetMapping("list")
    public ResponseEntity<List<Basket>> getAll(){
        System.out.println("try thissss ===  ");
        return new ResponseEntity<List<Basket>>(basketService.getAll(), HttpStatus.OK);

    }


}
