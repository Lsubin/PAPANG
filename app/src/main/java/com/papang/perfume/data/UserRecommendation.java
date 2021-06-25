package com.papang.perfume.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRecommendation {

    String email;
    String concentration;
    String size;
    int style;
    int flavor1;
    int flavor2;
    int flavor3;

    public String getEmail() {
        return email;
    }

    public String getConcentration() {
        return concentration;
    }

    public String getSize() {
        return size;
    }

    public int getStyle() {
        return style;
    }

    public int getFlavor1() {
        return flavor1;
    }

    public int getFlavor2() {
        return flavor2;
    }

    public int getFlavor3() {
        return flavor3;
    }
}
