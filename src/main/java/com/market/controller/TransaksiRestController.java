package com.market.controller;

import com.market.model.Transaksi;
import com.market.repository.TransaksiRepository;
import com.market.service.TransaksiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transaksi")
public class TransaksiRestController {

    private TransaksiService transaksiService;

    private TransaksiRepository transaksiRepository;

    @Autowired
    public TransaksiRestController(TransaksiRepository transaksiRepository){
        this.transaksiRepository = transaksiRepository;
    }

    @Autowired
    public void setTransaksiService(TransaksiService transaksiService) {
        this.transaksiService = transaksiService;
    }

    @Autowired
    public void setTransaksiRepository(TransaksiRepository transaksiRepository) {
        this.transaksiRepository = transaksiRepository;
    }

    @RequestMapping(value = {"/list", "/list/"}, method = RequestMethod.GET)
    public List<Transaksi> list(){
        return transaksiRepository.findAll();
    }

    @RequestMapping(value = "/cari-by-kode/{kode}", method = RequestMethod.GET)
    public Transaksi listByKode(@PathVariable String kode){
        return transaksiService.cariByKode(kode);
    }

    @RequestMapping(value = {"/createupdate", "/createupdate/"}, method = RequestMethod.POST)
    public List<Transaksi> createUpdate(@RequestBody Transaksi transaksi){
        transaksiRepository.save(transaksi);
        return transaksiRepository.findAll();
    }

    @RequestMapping(value = "/delete/{kode}", method = RequestMethod.POST)
    public List<Transaksi> delete(@PathVariable String kode){
        transaksiRepository.delete(kode);
        return transaksiRepository.findAll();
    }
}
