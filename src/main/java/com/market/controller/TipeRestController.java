package com.market.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.market.model.Tipe;
import com.market.service.TipeService;
import com.market.service.repository.TipeRepository;

@RestController
@RequestMapping("/api/tipe")
public class TipeRestController {

	private TipeService tipeService;
	
	private TipeRepository tipeRepository;
	
	@Autowired
	public TipeRestController(TipeRepository tipeRepository){
		this.tipeRepository = tipeRepository;
	}
	
	@Autowired
	public void setTipeRepository(TipeRepository tipeRepository) {
		this.tipeRepository = tipeRepository;
	}

	@Autowired
	public void setProdukService(TipeService tipeService) {
		this.tipeService = tipeService;
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public List<Tipe> findAll(){
		return tipeService.listTipe();
	}
	
	@RequestMapping(value = "/cari-by-kode/{kode}", method = RequestMethod.GET)
	public Tipe cariTipeByKode(@PathVariable Integer kode){
		return tipeService.cariTipeByKode(kode);
	}
	
	@RequestMapping(value = "/cari-by-nama", method = RequestMethod.GET)
	@ResponseBody
	public Tipe cariTipeByNama(String nama){
		return tipeService.cariTipeByNama(nama);
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public List<Tipe> createupdate(@RequestBody Tipe tipe){
		tipeRepository.save(tipe);
		return tipeService.listTipe();
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
	public List<Tipe> delete(@PathVariable Integer id){
		tipeRepository.delete(id);
		return tipeService.listTipe();
	}
}
