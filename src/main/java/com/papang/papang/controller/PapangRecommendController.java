package com.papang.papang.controller;

import com.papang.papang.entity.Perfume;
import com.papang.papang.entity.Recommendation;
import com.papang.papang.repository.PerfumeRepository;
import com.papang.papang.repository.RecommendationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/papang_perfume")
public class PapangRecommendController {
    @Autowired
    RecommendationRepository r_repo;

    @GetMapping(produces = "application/json; charset=utf-8")
    public List<Recommendation> getPerfume(){
        return r_repo.findAll();
    }
}
