(function() {
    'use strict';

    angular
        .module('miniMarket')
        .controller('AdminController', AdminController);

    AdminController.$inject = ['$http'];

    function AdminController($http) {
        var vm = this;

        var urlAPI = 'http://localhost:8080/api';

        vm.searchNama = '';
        vm.status = 'Loading...';
        vm.disabled = true;
        vm.dataTipe = '';
        vm.stok_habis = 0;

        vm.produk = [];
        vm.deskripsi = [];
        vm.cacheProduk = [];
        vm.user = [];
        vm.cacheUser = [];
        vm.tipe = [];
        vm.cacheTipe = [];
        vm.transaksi = [];
        vm.barangTransaksi = [];
        vm.detailPopupTransaksi = [];
        vm.cachePassword = [];
        vm.setting = [];

        vm.deskripsiProduk = deskripsiProduk;
        vm.listProduk = listProduk;
        vm.hapusProduk = hapusProduk;
        vm.setCache = setCache;
        vm.remCache = remCache;
        vm.listTipe = listTipe;
        vm.cariNamaTipe = cariNamaTipe;
        vm.createProduk = createProduk;
        vm.deleteTipe = deleteTipe;
        vm.insertUpdateTipe = insertUpdateTipe;
        vm.remCacheTipe = remCacheTipe;
        vm.setCacheTipe = setCacheTipe;
        vm.listTransaksi = listTransaksi;
        vm.listBarangTransaksi = listBarangTransaksi;
        vm.remBarangTransaksi = remBarangTransaksi;
        vm.setDetail = setDetail;
        vm.listUser = listUser;
        /*vm.createupdateUser = createupdateUser;
        vm.deleteUser = deleteUser;
        vm.remCacheUser = remCacheUser;
        vm.setCacheUser = setCacheUser;*/
        vm.ubahPassword = ubahPassword;
        vm.update = update;

        init();

        function init() {
            listTipe();
            listProduk();
            listTransaksi();
            listUser();
        }

        function listProduk() {
            var url = urlAPI + "/produk/list";
            var kasirPromise = $http.get(url);
            kasirPromise.then(function(response) {
                vm.produk = response.data;
            });
        }

        function deskripsiProduk(kode) {
            var url = urlAPI + "/produk/cari-by-kode/" + kode;
            var kasirPromise = $http.get(url);
            kasirPromise.then(function(response) {
                vm.deskripsi = response.data;
            });
        }

        function hapusProduk(kode) {
            var url = urlAPI + "/produk/delete/" + kode;
            if(confirm('Yakin ingin menghapus?')==true){
                $http.post(url).then(function(response) {
                    vm.produk = response.data;
                });
            }
        }

        function setCache(kode) {
            var url = urlAPI + "/produk/cari-by-kode/" + kode;
            var kP = $http.get(url);
            kP.then(function(response) {
                vm.cacheProduk = response.data;
            });
        }

        function remCache() {
            vm.cacheProduk = [];
        }

        function cariNamaTipe(kode) {
            var url = urlAPI + "/tipe/cari-by-kode/" + kode;
            var prom = $http.get(url);
            prom.then(function(response) {
                vm.cacheProduk.tipe.tipe = response.tipe;
            });
        }

        function createProduk() {
            var url = urlAPI + "/produk/createupdate";
            var prom = $http.post(url, {
                kode_produk: vm.cacheProduk.kode_produk,
                tipe: {
                    kode_tipe: vm.cacheProduk.tipe.kode_tipe,
                    tipe: vm.cacheProduk.tipe.tipe
                },
                nama: vm.cacheProduk.nama,
                stok: vm.cacheProduk.stok,
                harga_jual: vm.cacheProduk.harga_jual,
                harga_beli: vm.cacheProduk.harga_beli,
                tanggal_masuk: "",
                deskripsi: vm.cacheProduk.deskripsi
            });
            prom.then(function(response) {
                vm.cacheProduk = [];
                vm.produk = response.data;
            });
        }

        function listTipe() {
            var url = urlAPI + "/tipe/list";
            var kP = $http.get(url);
            kP.then(function(response) {
                vm.tipe = response.data;
            });
        }

        function deleteTipe(kode){
            var url = urlAPI + '/tipe/delete/' + kode;
            if(confirm('Yakin ingin menghapus?')==true){
                var kP = $http.post(url);
                kP.then(function(response){
                    vm.tipe = response.data;
                });
            }
        }

        function insertUpdateTipe(){
            if(vm.status=='Tambah'){vm.cacheTipe.kode_tipe=0;}
            var url = urlAPI + '/tipe/createupdate';
            var kP = $http.post(url, {
                kode_tipe: vm.cacheTipe.kode_tipe,
                tipe: vm.cacheTipe.tipe
            });
            kP.then(function(response){
               vm.tipe = response.data;
            });
        }

        function remCacheTipe(){
            vm.cacheTipe = [];
        }

        function setCacheTipe(kode){
            var url = urlAPI + "/tipe/cari-by-kode/" + kode;
            var kP = $http.get(url);
            kP.then(function(response) {
                vm.cacheTipe = response.data;
            });
        }

        function listTransaksi(){
            var url = urlAPI + '/transaksi/list';
            var p = $http.get(url);
            p.then(function(response){
               vm.transaksi = response.data;
            });
        }

        function remBarangTransaksi(){
            vm.barangTransaksi = [];
        }

        function setDetail(kode, tgl, jam){
            vm.detailPopupTransaksi = {
                kode_transaksi: kode,
                tanggal: tgl,
                jam: jam
            };
        }

        function listBarangTransaksi(kode_transaksi){
            var url = urlAPI + '/barang/list/' + kode_transaksi;
            var p = $http.get(url);
            p.then(function (response) {
                vm.barangTransaksi = response.data;
            });
        }

        function listUser(){
            var url = urlAPI + '/user/list';
            var p = $http.get(url);
            p.then(function(response){
               vm.user = response.data;
            });
        }

        function update(){
            var url = urlAPI + '/user/update?id=1&username=' + vm.setting.username + '&name=' + vm.setting.name;
            $http.get(url);
            vm.setting = [];
        }
/*
        function createupdateUser(){
            var url = urlAPI + '/user/createupdate';
            if(vm.status=='Tambah'){vm.cacheUser.id=0;}
            var p = $http.post(url, {
                "id": vm.cacheUser.id,
                "username": vm.cacheUser.username,
                "password": vm.cacheUser.password,
                "name": vm.cacheUser.name,
                "active": vm.cacheUser.active,
                "roles":[
                    {
                        "role_id": 0,
                        "role": 'ROLE_KASIR'
                    }
                ]
            });
            p.then(function(response){
                vm.cacheUser = [];
                vm.user = response.data;
            });
        }

        function deleteUser(id){
            var url = urlAPI + '/user/delete/' + id;
            if(confirm('Yakin ingin menghapus?')==true) {
                var p = $http.post(url);
                p.then(function (response) {
                    vm.user = response.data;
                });
            }
        }

        function remCacheUser(){
            vm.cacheUser = [];
        }

        function setCacheUser(id){
            var url = urlAPI + '/user/search/' + id;
            var p = $http.get(url);
            p.then(function(response){
               vm.cacheUser = response.data;
            });
        }
*/
        function ubahPassword(){
            var url = urlAPI + '/user/password?id=1' + '&password=' + vm.setting.password;
            $http.get(url);
            vm.setting = [];
        }

    }
})();