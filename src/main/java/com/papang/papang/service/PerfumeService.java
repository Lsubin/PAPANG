package com.papang.papang.service;

import com.papang.papang.entity.Perfume;
import com.papang.papang.repository.PerfumeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class PerfumeService {

    private final PerfumeRepository p_repo;

    @Autowired
    public PerfumeService(PerfumeRepository p_repo){
        this.p_repo = p_repo;
    }

    @Transactional(readOnly = true)
    public Optional<Perfume> findById(int id) {
        return p_repo.findById(id);
    }
}
