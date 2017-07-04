/**
 * 
 */
(function(){
	'use strict';
	
	angular
		.module('miniMarket')
		.controller('AdminController', AdminController);
	
	AdminController.$inject = ['$http'];
	
	function AdminController($http){
		var vm = this;

		var urlAPI = 'http://localhost:8080/api';

		vm.searchNama = '';
		vm.status = 'Loading...';
		vm.disabled = true;
		vm.dataTipe = '';
		vm.stok_habis = 0; //unused
		
		vm.produk = [];
		vm.deskripsi = [];
		vm.cacheProduk = []; // Array produk sebelum dikirim ke API CREATE atau UPDATE
		vm.tipe = [];
		vm.cacheTipe = [];

		vm.deskripsiProduk = deskripsiProduk;
		vm.listProduk = listProduk;
		vm.hapusProduk = hapusProduk;
		vm.setCache = setCache;
		vm.remCache = remCache;
		vm.listTipe = listTipe;
		vm.cariNamaTipe = cariNamaTipe;
		vm.createProduk = createProduk;
		vm.createupdateTipe = createupdateTipe;
		vm.setCacheTipe = setCacheTipe;
		vm.remCacheTipe = remCacheTipe;
		vm.deleteTipe = deleteTipe;
		//vm.cekStokHabis = cekStokHabis;
		
		init();
		
		function init(){
		    listTipe();
			listProduk();
		}

		/*
		*   Fungsi untuk Produk
		 */
		function listProduk(){
			var url = urlAPI + "/produk/list";
			var kasirPromise = $http.get(url);
			kasirPromise.then(function(response){
				vm.produk = response.data;
			});
		}
		
		function deskripsiProduk(kode){
			var url = urlAPI + "/produk/cari-by-kode/" + kode;
			var kasirPromise = $http.get(url);
			kasirPromise.then(function(response){
				vm.deskripsi = response.data;
			});
		}

		function hapusProduk(kode, nama){
		    if(confirm('Yakin ingin menghapus produk ' + nama + '?') == true){

                var url = urlAPI + "/produk/delete/" + kode;
                $http.post(url).then(function(response){
                    vm.produk = response.data;
                });
            }
		}

		function setCache(kode){
			var url = urlAPI + "/produk/cari-by-kode/" + kode;
			var kP = $http.get(url);
			kP.then(function (response) {
				vm.cacheProduk = response.data;
            });
		}

		function remCache(){
			vm.cacheProduk = [];
		}

		function cariNamaTipe(kode){
			var url = urlAPI + "/tipe/cari-by-kode/" + kode;
			var prom = $http.get(url);
			prom.then(function(response){
				vm.cacheProduk.tipe.tipe = response.tipe;
			});
		}

		function createProduk(){
		    // validasi
            /*
            if(vm.cacheProduk.kode_produk == '' || vm.cacheProduk.nama == '' || vm.cacheProduk.tipe.kode_tipe == '' || vm.cacheProduk.stok =='' || vm.cacheProduk.harga_jual == '' || vm.cacheProduk.harga_beli == '' || vm.cacheProduk.deskripsi == ''){
                alert('Data yang anda masukkan belum lengkap');
            }else if(vm.cacheProduk.harga_jual >= 9999999999){
                alert('Silahkan masukkan harga jual kurang dari 10 digit');
            }else if(vm.cacheProduk.harga_beli >= 9999999999) {
                alert('Silahkan masukkan harga jual kurang dari 10 digit')
            }else if(vm.cacheProduk.stok >= 9999999999){
                alert('Silahkan masukkan stok kurang dari 10 digit');
            }else{*/
                var url = urlAPI + "/produk/createupdate";
                var prom = $http.post(url, {
                    kode_produk: vm.cacheProduk.kode_produk,
                    tipe: {
                        kode_tipe: vm.cacheProduk.tipe.kode_tipe
                    },
                    nama: vm.cacheProduk.nama,
                    stok: vm.cacheProduk.stok,
                    harga_jual: vm.cacheProduk.harga_jual,
                    harga_beli: vm.cacheProduk.harga_beli,
                    tanggal_masuk: "2000-04-24",
                    deskripsi: vm.cacheProduk.deskripsi
                });
                prom.then(function(response){
                    vm.produk = response.data;
                });
                // TODO respon body ke URL
                //console.log(url);
            //} //Tutup validasi
		}

		/*
		*   Fungsi untuk Tipe
		 */

        function listTipe(){
            var url = urlAPI + "/tipe/list";
            var kP = $http.get(url);
            kP.then(function (response) {
                vm.tipe = response.data;
            });
        }

        function createupdateTipe(){
            if(vm.status == 'Tambah Tipe'){
                vm.cacheTipe.kode_tipe = 0;
            }
            var url = urlAPI + '/tipe/createupdate';
            var prom = $http.post(url, {
                kode_tipe: vm.cacheTipe.kode_tipe,
                tipe: vm.cacheTipe.tipe
            });
            prom.then(function(response){
               vm.tipe = response.data;
            });
        }

        function setCacheTipe(kode){
            var url = urlAPI + "/tipe/cari-by-kode/" + kode;
            var prom = $http.get(url);
            prom.then(function (response) {
                vm.cacheTipe = response.data;
            });
        }

        function remCacheTipe(){
            vm.cacheTipe = [];
        }

        function deleteTipe(kode, nama){
            if(confirm('Yakin ingin menghapus tipe ' + nama + ' dan\nsemua produk dengan tipe tersebut?') == true){
                var url = urlAPI + '/tipe/delete/' + kode;
                var prom = $http.post(url);
                prom.then(function(response){
                    vm.tipe = response.data;
                });
            }
        }
	}
})();