package com.papang.papang.repository;

import com.papang.papang.entity.Perfume;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PerfumeRepository extends JpaRepository<Perfume, Integer> {


    @Query("SELECT d FROM Perfume d " +
            "WHERE d.concentration = :concentration AND d.size between :size1 AND :size2 " +
            "AND d.style = :style AND d.main = :main AND d.first = :first AND d.second = :second")
    List<Perfume> findByRecommendationPerfume(int concentration, int size1, int size2, int style,
                                              int main, int first, int second);

    @Query("select p from Perfume p where p.name = :name")
    List<Perfume> findByName(String name);
}
