package com.episen.ms_basket.repository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.episen.ms_basket.model.Basket;
import org.springframework.stereotype.Component;




@Component
public class BasketRepository {

    private Map<String,Basket> basketInMemory = new HashMap<String,Basket>();


    public void add(Basket basket) {
        System.out.println("add Basket by the Basket : " + basket.getUser());

        basketInMemory.put(basket.getId_basket(),basket);
    }

    public Basket getBasketsById(String Basket_Id) {
        System.out.println("Get baskets by name -> Login : " + Basket_Id);
       
        return basketInMemory.get(Basket_Id);
    }

    public List<Basket> getAll(){
        System.out.println("Get All -> count : " + basketInMemory.size());

        return new ArrayList<>(basketInMemory.values());
    }

    public void update(Basket basket) {

    	basketInMemory.put(basket.getId_basket(),basket);
    }

    public void delete(String id_basket) {

        basketInMemory.remove(id_basket);
    }
}