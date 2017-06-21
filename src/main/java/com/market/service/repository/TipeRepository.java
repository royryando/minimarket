package com.market.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.market.model.Tipe;

@Repository
public interface TipeRepository extends JpaRepository<Tipe, Integer> {

}
