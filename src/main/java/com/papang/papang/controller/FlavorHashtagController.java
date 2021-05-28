package com.papang.papang.controller;

import com.papang.papang.entity.FlavorHashtag;
import com.papang.papang.entity.Perfume;
import com.papang.papang.repository.FlavorHashtagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/flavor_hashtag")
public class FlavorHashtagController {

    @Autowired
    FlavorHashtagRepository f_repo;

    @GetMapping(value = "/{flavor}", produces = "application/json; charset=utf-8")
    public List<FlavorHashtag> getPerfumeRecommendation(@PathVariable("flavor") int flavor){
        return f_repo.findByFlavor(flavor);
    }

    @GetMapping(value = "/{flavor}/{flavor2}", produces = "application/json; charset=utf-8")
    public List<FlavorHashtag> getPerfumeRecommendation2(@PathVariable("flavor") int flavor,
                                                         @PathVariable("flavor2") int flavor2){
        return f_repo.findByFlavor2(flavor, flavor2);
    }
}
