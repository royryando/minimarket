package com.market.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/kasir")
public class KasirController {

	@RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
	public String index(){
		return "kasir";
	}
}
