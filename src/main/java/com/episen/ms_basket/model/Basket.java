package com.episen.ms_basket.model;


import java.util.List;

public class Basket {

    private String id_basket;
    public String getId_basket() {
		return id_basket;
	}

	public void setId_basket(String id_basket) {
		this.id_basket = id_basket;
	}

	private String user;

    private float totalAmount;


    private String status;

    private List<Product> items;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public float getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(float totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Product> getItems() {
        return items;
    }

    public void setItems(List<Product> items) {
        this.items = items;
    }

}
