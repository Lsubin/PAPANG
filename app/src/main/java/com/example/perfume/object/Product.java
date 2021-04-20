package com.example.perfume.object;

import java.util.ArrayList;


public class Product {
    public ArrayList<String> shops;
    public ArrayList<String> prices;
    public Boolean price_tag;

    /*
    public Product(String shop, String price){
        shops.add(shop);
        prices.add(price);
    }*/

    public Product(ArrayList<String> shop, ArrayList<String> price){
        shops = shop;
        prices = price;
    }
}
