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
		vm.stok_habis = 0;
		
		vm.produk = [];
		vm.deskripsi = [];
		vm.cacheProduk = []; // Array produk sebelum dikirim ke API CREATE atau UPDATE
		vm.tipe = [];

		vm.deskripsiProduk = deskripsiProduk;
		vm.listProduk = listProduk;
		vm.hapusProduk = hapusProduk;
		vm.setCache = setCache;
		vm.remCache = remCache;
		vm.listTipe = listTipe;
		vm.cariNamaTipe = cariNamaTipe;
		vm.createProduk = createProduk;
		//vm.cekStokHabis = cekStokHabis;
		
		init();
		
		function init(){
			listProduk();
		}
		
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

		function hapusProduk(kode){
			var url = urlAPI + "/produk/delete/" + kode;
			$http.post(url).then(function(response){
				vm.produk = response.data;
			});
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

		function listTipe(){
			var url = urlAPI + "/tipe/list";
			var kP = $http.get(url);
			kP.then(function (response) {
				vm.tipe = response.data;
            });
		}

		function cariNamaTipe(kode){
			var url = urlAPI + "/tipe/cari-by-kode/" + kode;
			var prom = $http.get(url);
			prom.then(function(response){
				vm.cacheProduk.tipe.tipe = response.tipe;
			});
		}

		function createProduk(){
			var url = urlAPI + "/produk/createupdate";
			// TODO respon body ke URL
			console.log(url);
		}
/*
		function cekStokHabis(){
		    var url = urlAPI + "/produk/stok-habis";
		    var prom = $http.get(url);
		    prom.then(function (response) {
                vm.stok_habis = response.data;
            });
        }
*/
	}
})();