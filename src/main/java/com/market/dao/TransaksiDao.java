package com.market.dao;

import com.market.model.Transaksi;
import com.market.service.TransaksiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

@Service
public class TransaksiDao implements TransaksiService {

    private EntityManagerFactory emf;

    @Autowired
    public void setEmf(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public Transaksi cariByKode(String kode) {
        EntityManager em = emf.createEntityManager();
        Transaksi hasil;
        try
        {
            hasil = em.createQuery("from Transaksi where kode_transaksi='" + kode + "'", Transaksi.class).getSingleResult();
        }
        catch(Exception ex)
        {
            System.out.println(ex);
            hasil = null;
        }
        return hasil;
    }
}
