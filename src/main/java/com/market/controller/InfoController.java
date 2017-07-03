package com.market.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/api")
public class InfoController {
	@RequestMapping(value = {"", "/", "/produk", "/tipe", "/transaksi", "/produk/", "/tipe/", "/transaksi/"})
	public String apiInfo(){
		return "info";
	}
}