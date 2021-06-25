package com.papang.perfume.data;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Perfume implements Serializable {

    int perfumeID;          // 향수ID 1-끝까지
    String brand;           // 브랜드 이름
    String name;            // 향수 이름
    int size;               // 향수 사이즈
    int concentration;      // 농도
    int style;              // 스타일(포근한 등등)
    int main;               // 메인 향료
    int first;              // 추가 향료1
    int second;             // 추가 향료2
    String check;             // 판매 하는지 안하는지
    String url;

    public int getPerfumeID(){
        return perfumeID;
    }

    public String getBrand(){
        return brand;
    }

    public String getName(){
        return name;
    }

    public int getSize(){
        return size;
    }

    public int getConcentration(){
        return concentration;
    }

    public int getStyle(){
        return style;
    }

    public int getMain(){
        return main;
    }

    public int getFirst(){
        return first;
    }

    public int getSecond(){
        return second;
    }

    public String getCheck(){
        return check;
    }

    public String getUrl(){
        return url;
    }

}
