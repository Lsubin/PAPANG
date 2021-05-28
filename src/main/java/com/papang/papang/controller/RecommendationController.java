package com.papang.papang.controller;

import com.papang.papang.entity.FlavorHashtag;
import com.papang.papang.entity.Perfume;
import com.papang.papang.repository.PerfumeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/perfume_recommend")
public class RecommendationController {

    @Autowired
    PerfumeRepository p_repo;

    @GetMapping(value = "/{concentration}/{size1}/{size2}/{style}/{main}/{first}/{second}", produces = "application/json; charset=utf-8")
    public List<Perfume> getPerfumeRecommendation2(@PathVariable("concentration") int concentration,
                                                         @PathVariable("size1") int size1, @PathVariable("size2") int size2,
                                                         @PathVariable("style") int style, @PathVariable("main") int main,
                                                         @PathVariable("first") int first, @PathVariable("second") int second){
        return p_repo.findByRecommendationPerfume(concentration, size1, size2, style, main, first, second);
    }

}
