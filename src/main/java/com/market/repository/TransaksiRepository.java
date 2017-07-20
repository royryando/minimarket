package com.market.repository;

import com.market.model.Transaksi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Roy on 28/06/2017.
 */
@Repository
public interface TransaksiRepository extends JpaRepository<Transaksi, String> {
}
