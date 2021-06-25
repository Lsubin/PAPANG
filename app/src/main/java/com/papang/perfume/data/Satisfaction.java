package com.papang.perfume.data;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Satisfaction implements Serializable {

    String type;
    String text;

    public String getType() {
        return type;
    }

    public String getText() {
        return text;
    }
}