package com.market.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.market.model.Produk;
import com.market.service.ProdukService;

@Service
public class ProdukDao implements ProdukService {

	private EntityManagerFactory emf;
	
	@Autowired
	public void setEmf(EntityManagerFactory emf) {
		this.emf = emf;
	}

	//@SuppressWarnings("unchecked")
	@Override
	public List<Produk> listProduk() {
		EntityManager em = emf.createEntityManager();
		List<Produk> hasil;
		try{
			hasil = em.createQuery("FROM Produk ORDER BY nama ASC LIMIT 14", Produk.class).getResultList();
		}
		catch(Exception ex)
		{
			System.out.println(ex);
			hasil = null;
		}
		return hasil;
		//return em.createNativeQuery("SELECT kode_produk,nama,stok,tipe.tipe,harga_jual,harga_beli,tanggal_masuk,deskripsi FROM Produk,Tipe ORDER BY nama ASC").getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Produk> listProdukFilterNama(String nama) {
		EntityManager em = emf.createEntityManager();
		List<Produk> hasil;
		try{
			hasil = em.createNativeQuery("SELECT * FROM Produk WHERE nama LIKE '%" + nama + "%' ORDER BY nama ASC LIMIT 14", Produk.class).getResultList();
		}
		catch(Exception ex)
		{
			System.out.println(ex);
			hasil = null;
		}
		return hasil;
	}

	@Override
	public Produk listProdukFilterKode(String kode) {
		EntityManager em = emf.createEntityManager();
		Produk hasil;
		try
        {
			hasil = em.createQuery("from Produk where kode_produk='" + kode + "'", Produk.class).getSingleResult();
		}
		catch(Exception ex)
		{
			System.out.println(ex);
			hasil = null;
		}
		return hasil;
	}

    @Override
    public void updateStok(String kode, Integer beli) {
	    EntityManager em = emf.createEntityManager();
        try
        {
            em.getTransaction().begin();
            em.createNativeQuery("UPDATE Produk set stok=stok-" + beli + " WHERE kode_produk='" + kode + "'", Produk.class).executeUpdate();
            em.getTransaction().commit();
            System.out.println("Update berhasil");
        }
        catch(Exception ex)
        {
            System.out.println(ex);
        }
    }

}
