package com.episen.ms_basket.service;

import java.util.List;

import com.episen.ms_basket.model.Basket;
import com.episen.ms_basket.repository.BasketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;




@Service
public class BasketService {

    @Autowired
    private BasketRepository repository;

    public void create_basket(Basket basket) {

        repository.add(basket);
    }


    public List<Basket> getAll(){
        return repository.getAll();
    }

    public void update_basket(Basket basket) {
        repository.update(basket);
    }

    public void delete_basket(String basket) {

        repository.delete(basket);
    }



}
