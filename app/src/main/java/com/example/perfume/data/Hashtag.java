package com.example.perfume.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Hashtag {

    int flavor;
    String hashtag;

    public int getFlavor(){
        return this.flavor;
    }

    public String getHashtag(){
        return this.hashtag;
    }
}
