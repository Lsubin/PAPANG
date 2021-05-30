package com.example.perfume.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Price {

    int perfumeID;          // 향수ID 1-끝까지
    String check;           // 최저가 있는지 없는지
    String url1;            // URL
    String url2;            // URL
    String url3;            // URL

    public int getPerfumeID(){
        return this.perfumeID;
    }

    public String getCheck(){
        return this.check;
    }

    public String getUrl1(){
        return this.url1;
    }

    public String getUrl2(){
        return this.url2;
    }

    public String getUrl3(){
        return this.url3;
    }

}
