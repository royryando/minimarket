package com.market.dao;

import com.market.model.BarangTransaksi;
import com.market.service.BarangTransaksiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

/**
 * Created by Roy on 03/07/2017.
 */
@Service
public class BarangTransaksiDao implements BarangTransaksiService {

    @Autowired
    private EntityManagerFactory emf;

    @Autowired
    public void setEmf(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public List<BarangTransaksi> listBarang(String kode) {
        EntityManager em = emf.createEntityManager();
        List<BarangTransaksi> hasil;
        try{
            hasil = em.createQuery("FROM BarangTransaksi WHERE kode_transaksi='" + kode + "'", BarangTransaksi.class).getResultList();
        }catch(Exception ex){
            System.out.println(ex);
            hasil = null;
        }
        return hasil;
    }
}
