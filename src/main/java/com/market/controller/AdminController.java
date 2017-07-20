package com.market.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@RequestMapping(value = {"", "/"})
	public String indexAdmin(){
		return "admin-main";
	}
	
	@RequestMapping(value = {"/produk", "/produk/"}, method = RequestMethod.GET)
	public String listProduk(){
		return "admin-produk";
	}

	@RequestMapping(value = {"/tipe", "/tipe/"}, method = RequestMethod.GET)
	public String adminTipe(){
		return "admin-tipe";
	}

	@RequestMapping(value = {"/transaksi", "/transaksi/"}, method = RequestMethod.GET)
    public String adminTransaksi(){
	    return "admin-transaksi";
    }

    @RequestMapping(value = {"/kasir", "/kasir/"}, method = RequestMethod.GET)
	public String adminAkun(){
    	return "admin-user";
	}
}
