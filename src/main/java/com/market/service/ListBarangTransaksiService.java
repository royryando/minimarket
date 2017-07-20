package com.market.service;

import com.market.model.ListBarangTransaksi;

import java.util.List;

/**
 * Created by Roy on 28/06/2017.
 */
public interface ListBarangTransaksiService {
    List<ListBarangTransaksi> listBy(String kode);
}
