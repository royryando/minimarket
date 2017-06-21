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
		
		vm.produk = [];
		vm.listProduk = listProduk;
		
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
	}
})();