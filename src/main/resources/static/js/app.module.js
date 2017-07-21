/**
 * 
 */
angular.module('miniMarket', [])
.controller('produk', KasirsController);

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
			var url = "http://localhost:8080/api/produk";
			var kasirsPromise = $http.get(url);
			kasirsPromise.then(function(response){
				console.log('fungsi respon data');
				vm.kasirs = response.data;
			});
		}
}