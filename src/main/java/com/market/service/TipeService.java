package com.market.service;

import java.util.List;

import com.market.model.Tipe;

public interface TipeService {
	List<Tipe> listTipe();
	Tipe cariTipeByKode(Integer kode);
	Tipe cariTipeByNama(String nama);
}
