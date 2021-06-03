package com.example.perfume.data;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Wish implements Serializable {

    String email;
    int perfumeID;
    String brand;
    String name;

    public String getEmail(){
        return this.email;
    }

    public int getPerfumeID(){
        return this.perfumeID;
    }

    public String getBrand(){
        return this.brand;
    }

    public String getName(){
        return this.name;
    }
}
