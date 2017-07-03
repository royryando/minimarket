/**
 * 
 */
(function(){
	'use strict';
	
	angular
		.module('miniMarket')
		.controller('KasirController', KasirController);
	
	KasirController.$inject = ['$http'];
	
	function KasirController($http){
		var vm = this;
		
		vm.searchNama = '';
		
		vm.produk = [];
		vm.deskripsi = [];
		vm.listProduk = listProduk;
		vm.deskripsiProduk = deskripsiProduk;
		vm.cariByNama = cariByNama;
		
		init();
		
		function init(){
			listProduk();
		}
		
		function listProduk(){
			var url = "http://localhost:8080/api/produk/list";
			var kasirPromise = $http.get(url);
			kasirPromise.then(function(response){
				vm.produk = response.data;
			});
		}
		
		function deskripsiProduk(kode){
			var url = "http://localhost:8080/api/produk/cari-by-kode/" + kode;
			var kasirPromise = $http.get(url);
			kasirPromise.then(function(response){
				vm.deskripsi = response.data;
			});
		}
		
		function cariByNama(nama){
			var url = "http://localhost:8080/api/produk/cari-by-nama?nama=" + nama;
			var kasirPromise = $http.get(url);
			kasirPromise.then(function(response){
				vm.produk = response.data;
			});
		}
		
	}
})();