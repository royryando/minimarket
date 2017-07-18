package com.market.controller;

import com.market.model.ListBarangTransaksi;
import com.market.repository.ListBarangTransaksiRepository;
import com.market.service.ListBarangTransaksiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/barang")
public class ListBarangTransaksiRestController {

    @Autowired
    private ListBarangTransaksiRepository listRepo;
    @Autowired
    private ListBarangTransaksiService listService;

    @Autowired
    public void setListService(ListBarangTransaksiService listService) {
        this.listService = listService;
    }

    @Autowired
    public void setListRepo(ListBarangTransaksiRepository listRepo) {
        this.listRepo = listRepo;
    }

    @RequestMapping(value = {"/insert", "/insert/"}, method = RequestMethod.POST)
    public void insertBarang(@RequestBody ListBarangTransaksi listBarang){
        listRepo.save(listBarang);
    }

    @RequestMapping(value = "/list/{kode}", method = RequestMethod.GET)
    public List<ListBarangTransaksi> listBarang(@PathVariable String kode){
        return listService.listBy(kode);
    }
}
