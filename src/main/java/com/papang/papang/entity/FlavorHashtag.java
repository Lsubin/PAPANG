package com.papang.papang.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@Table(name="flavor_hashtag")
@IdClass(HashtagVOKey.class)
public class FlavorHashtag {

    @Id
    @Column(name="flavor", nullable = false)
    int flavor;

    @Id
    @Column(name="hashtag", nullable = false)
    String hashtag;

    public int getFlavor(){
        return this.flavor;
    }

    public String getHashtag(){
        return this.hashtag;
    }

}
