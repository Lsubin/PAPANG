package com.papang.perfume.data;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Recommendation implements Serializable {

    int perfumeID;          // 향수ID 1-끝까지
    String brand;           // 브랜드 이름
    String name;            // 향수 이름

    public int getPerfumeID(){
        return perfumeID;
    }

    public String getBrand(){
        return brand;
    }

    public String getName(){
        return name;
    }

}
