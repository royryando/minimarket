package com.market.dao;

import com.market.service.ListBarangTransaksiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;

/**
 * Created by Roy on 28/06/2017.
 */
@Service
public class ListBarangTransaksiDao implements ListBarangTransaksiService {

    private EntityManager emf;

    @Autowired
    public void setEmf(EntityManager emf) {
        this.emf = emf;
    }

}
