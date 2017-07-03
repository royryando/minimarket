/**
 * 
 */
(function(){
	'use strict';
	angular
		.module('app')
		.controller('KasirsController', KasirsController);
		
		KasirsController.$inject = ['$http'];
		
		function KasirsController($http){
			var vm = this;
			vm.kasirs = [];
			vm.getAll = getAll;
			
			init();
			
			function init(){
				console.log('fungsi init()');
				getAll();
			}
			
			function getAll(){
				console.log('fungsi getAll()');
				var url = "/api/produk";
				var kasirsPromise = $http.get(url);
				kasirsPromise.then(function(response){
					console.log('fungsi respon data');
					vm.kasirs = response.data;
				});
			}
			
			
		}
})();
