package com.market.service;

import com.market.model.BarangTransaksi;

import java.util.List;

/**
 * Created by Roy on 03/07/2017.
 */
public interface BarangTransaksiService {
    List<BarangTransaksi> listBarang(String kode);
}
