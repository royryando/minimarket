package com.market.controller;

import com.market.model.BarangTransaksi;
import com.market.model.Transaksi;
import com.market.repository.BarangTransaksiRepository;
import com.market.repository.TransaksiRepository;
import com.market.service.BarangTransaksiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Roy on 03/07/2017.
 */
@RestController
@RequestMapping("/api/transaksi")
public class TransaksiRestController {

    TransaksiRepository transaksiRepository;
    BarangTransaksiRepository barangTransaksiRepository;
    BarangTransaksiService barangTransaksiService;

    @Autowired
    public void setBarangTransaksiService(BarangTransaksiService barangTransaksiService) {
        this.barangTransaksiService = barangTransaksiService;
    }

    @Autowired
    public void setBarangTransaksiRepository(BarangTransaksiRepository barangTransaksiRepository) {
        this.barangTransaksiRepository = barangTransaksiRepository;
    }

    @Autowired
    public void setTransaksiRepository(TransaksiRepository transaksiRepository) {
        this.transaksiRepository = transaksiRepository;
    }

    @RequestMapping(value = {"/list", "/list/"}, method = RequestMethod.GET)
    public List<Transaksi> listTransaksi(){
        return transaksiRepository.findAll();
    }

    @RequestMapping(value = "/list-by-kode/{kode}", method = RequestMethod.GET)
    public Transaksi listbyKode(@PathVariable String kode){
        return transaksiRepository.findOne(kode);
    }

    @RequestMapping(value = {"/createupdate", "/createupdate/"}, method = RequestMethod.POST)
    public Transaksi createUpdate(@RequestBody Transaksi transaksi){
        transaksiRepository.save(transaksi);
        return transaksiRepository.findOne(transaksi.getKode_transaksi());
    }

    @RequestMapping(value = "/delete/{kode}", method = RequestMethod.POST)
    public List<Transaksi> deleteTransaksi(@PathVariable String kode){
        transaksiRepository.delete(kode);
        return transaksiRepository.findAll();
    }

    // Transaksi Barang

    @RequestMapping(value = "/list-barang/{kode}", method = RequestMethod.GET)
    public List<BarangTransaksi> listBarang(@PathVariable String kode){
        return barangTransaksiService.listBarang(kode);
    }

    @RequestMapping(value = {"/create-update-barang", "/create-update-barang/"}, method = RequestMethod.POST)
    public List<BarangTransaksi> createUpdateBarang(@RequestBody BarangTransaksi barangTransaksi){
        barangTransaksiRepository.save(barangTransaksi);
        return barangTransaksiService.listBarang(barangTransaksi.getKode_transaksi());
    }
}
