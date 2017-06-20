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
		return em.createQuery("from Produk", Produk.class).getResultList();
		//return em.createNativeQuery("SELECT kode_produk,nama,stok,tipe.tipe,harga_jual,harga_beli,tanggal_masuk,deskripsi FROM Produk,Tipe ORDER BY nama ASC").getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Produk> listProdukFilterNama(String nama) {
		EntityManager em = emf.createEntityManager();
		return em.createNativeQuery("SELECT * FROM Produk WHERE nama LIKE '%" + nama + "%'", Produk.class).getResultList();
		//return null;
	}

	@Override
	public Produk listProdukFilterKode(String kode) {
		EntityManager em = emf.createEntityManager();
		Produk hasil;
		try{
			hasil = em.createQuery("from Produk where kode_produk='" + kode + "'", Produk.class).getSingleResult();
		}
		catch(Exception ex)
		{
			return null;
		}
		return hasil;
	}
	
}
