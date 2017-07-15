package com.market.dao;

import com.market.model.ListBarangTransaksi;
import com.market.service.ListBarangTransaksiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

@Service
public class ListBarangTransaksiDao implements ListBarangTransaksiService {

    @Autowired
    private EntityManagerFactory emf;

    @Autowired
    public void setEmf(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public List<ListBarangTransaksi> listBy(String kode) {
        EntityManager em = emf.createEntityManager();
        return em.createQuery("from ListBarangTransaksi where kode_transaksi='" + kode + "'", ListBarangTransaksi.class).getResultList();
    }
}
