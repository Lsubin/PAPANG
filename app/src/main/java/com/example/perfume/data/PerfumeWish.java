package com.example.perfume.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PerfumeWish {

    String name;
    int wish_count;

    public String getName(){
        return this.name;
    }

    public int getWish_count(){
        return this.wish_count;
    }

}
