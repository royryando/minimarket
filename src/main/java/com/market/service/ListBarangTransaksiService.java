package com.market.service;

import com.market.model.ListBarangTransaksi;

import java.util.List;

public interface ListBarangTransaksiService {
    List<ListBarangTransaksi> listBy(String kode);
}
