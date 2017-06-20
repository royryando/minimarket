package com.market.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.market.model.Tipe;
import com.market.service.TipeService;

@RestController
@RequestMapping("/api/tipe")
public class TipeRestController {

	private TipeService produkService;
	
	@Autowired
	public void setProdukService(TipeService tipeService) {
		this.produkService = tipeService;
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public List<Tipe> findAll(){
		return produkService.listTipe();
	}
	
	@RequestMapping(value = "/cari-by-kode/{kode}", method = RequestMethod.GET)
	public Tipe cariTipeByKode(@PathVariable Integer kode){
		return produkService.cariTipeByKode(kode);
	}
	
	@RequestMapping(value = "/cari-by-nama", method = RequestMethod.GET)
	@ResponseBody
	public Tipe cariTipeByNama(String nama){
		return produkService.cariTipeByNama(nama);
	}
}
