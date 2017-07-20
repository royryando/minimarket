package com.market.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.market.model.Produk;
import com.market.repository.ProdukRepository;
import com.market.service.ProdukService;

@RestController
@RequestMapping("/api/produk")
public class ProdukRestController {

	private ProdukService produkService;
	
	private ProdukRepository produkRepository;
	
	@Autowired
	public ProdukRestController(ProdukRepository produkRepository){
		this.produkRepository = produkRepository;
	}
	
	@Autowired
	public void setProdukRepository(ProdukRepository produkRepository) {
		this.produkRepository = produkRepository;
	}

	@Autowired
	public void setProdukService(ProdukService produkService) {
		this.produkService = produkService;
	}

	@RequestMapping(value = {"/list", "/list/"}, method = RequestMethod.GET)
	public List<Produk> findAll(){
		return produkService.listProduk();
	}
	
	@RequestMapping(value = {"/cari-by-nama", "cari-by-nama/"}, method = RequestMethod.GET)
	@ResponseBody
	public List<Produk> listProdukFilterNama(String nama){
		return produkService.listProdukFilterNama(nama);
	}
	
	@RequestMapping(value = "/cari-by-kode/{kode}", method = RequestMethod.GET)
	public Produk listProdukFilterKode(@PathVariable String kode){
		return produkService.listProdukFilterKode(kode);
	}
	
	@RequestMapping(value = {"/createupdate", "/createupdate/"}, method = RequestMethod.POST)
	public List<Produk> createUpdate(@RequestBody Produk produk){
		produkRepository.save(produk);
		return produkService.listProduk();
	}
	
	@RequestMapping(value = "/delete/{kode}", method = RequestMethod.POST)
	public List<Produk> delete(@PathVariable String kode){
		produkRepository.delete(kode);
		return produkService.listProduk();
	}

	@RequestMapping(value = "/update-stok", method = RequestMethod.GET)
    @ResponseBody
    public List<Produk> update_stok(String kode, Integer beli){
	    produkService.updateStok(kode, beli);
	    return produkService.listProduk();
    }

/*
	@RequestMapping(value = {"/stok-habis", "/stok-habis/"}, method = RequestMethod.GET)
	public Produk stokhabis(){
		return produkService.stokHabis();
	}
	*/
}
