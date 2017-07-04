package com.market.repository;

import com.market.model.BarangTransaksi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Roy on 03/07/2017.
 */
@Repository
public interface BarangTransaksiRepository extends JpaRepository<BarangTransaksi, Integer> {
}
