package com.example.perfume.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PerfumeWish {

    String name;
    String brand;
    int wish_count;

    public String getName(){
        return this.name;
    }

    public String getBrand() {
        return brand;
    }

    public int getWish_count(){
        return this.wish_count;
    }

}
