package com.papang.papang.repository;

import com.papang.papang.entity.FlavorHashtag;
import com.papang.papang.entity.Perfume;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlavorHashtagRepository extends JpaRepository<FlavorHashtag, Integer> {

    @Query("select f from FlavorHashtag f where f.flavor = :flavor")
    List<FlavorHashtag> findByFlavor(int flavor);

    @Query("select f from FlavorHashtag f where f.flavor = :flavor OR f.flavor = :flavor2")
    List<FlavorHashtag> findByFlavor2(int flavor, int flavor2);

}
