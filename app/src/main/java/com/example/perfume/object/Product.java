package com.example.perfume.object;

import java.util.ArrayList;

public class Product {
    ArrayList<String> shops;
    ArrayList<String> prices;
    Boolean price_tag;

    public Product(String shop, String price){
        shops.add(shop);
        prices.add(price);
    }
}
