package com.market.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.market.model.Tipe;
import com.market.service.TipeService;

@Service
public class TipeDao implements TipeService {

	private EntityManagerFactory emf;

	@Autowired
	public void setEmf(EntityManagerFactory emf) {
		this.emf = emf;
	}
	
	public List<Tipe> listTipe(){
		EntityManager em =emf.createEntityManager();
		return em.createQuery("from Tipe", Tipe.class).getResultList();
	}

	@Override
	public Tipe cariTipeByNama(String nama) {
		EntityManager em =emf.createEntityManager();
		Tipe hasil;
		try{
			hasil = em.createQuery("from Tipe where tipe='" + nama + "'", Tipe.class).getSingleResult();
		}
		catch(Exception ex)
		{
			return null;
		}
		return hasil;
	}

	@Override
	public Tipe cariTipeByKode(Integer kode) {
		EntityManager em =emf.createEntityManager();
		Tipe hasil;
		try{
			 hasil = em.createQuery("from Tipe where kode_tipe='" + kode + "'", Tipe.class).getSingleResult();
		}
		catch(Exception ex){
			return null;
		}
		return hasil;
	}
	
}
