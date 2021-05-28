package com.papang.papang.controller;

import com.papang.papang.entity.Perfume;
import com.papang.papang.repository.PerfumeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/perfume")
public class PerfumeController {

    @Autowired
    PerfumeRepository p_repo;

    @GetMapping(value = "/find", produces = "application/json; charset=utf-8")
    public List<Perfume> getPerfume(){
        return p_repo.findAll();
    }

    @GetMapping(value = "/find/{perfumeID}", produces = "application/json; charset=utf-8")
    public Perfume getPerfumeOne(@PathVariable("perfumeID") int perfumeID){
        return p_repo.findById(perfumeID).orElse(null);
    }

    @GetMapping(value = "/recommendation/{name}", produces = "application/json; charset=utf-8")
    public List<Perfume> getPerfumeRecommendation(@PathVariable("name") String name){
        return p_repo.findByName(name);
    }

}
