/**
 * 
 */
(function(){
	//'use strict';

	var app = angular.module('miniMarket', ['ngCookies']);
	//angular
	//	.module('miniMarket')
    app.controller('KasirController', ['$scope','$cookies','$http', function($scope, $cookies, $http){

        var baseURL = 'http://localhost:8080/api';

        $scope.searchNama = '';
        $scope.produk = [];
        $scope.deskripsi = [];
        $scope.cart = [];
        $scope.total = 0;
        $scope.kode_transaksi = '';

        $scope.listProduk = listProduk;
        $scope.deskripsiProduk = deskripsiProduk;
        $scope.cariByNama = cariByNama;
        $scope.addToCart = addToCart;
        $scope.removeFromCart = removeFromCart;

        init();

        function init(){
            listProduk();
        }

        function listProduk(){
            var url = baseURL + "/produk/list";
            var kasirPromise = $http.get(url);
            kasirPromise.then(function(response){
                $scope.produk = response.data;
            });
        }

        function deskripsiProduk(kode){
            var url = baseURL + "/produk/cari-by-kode/" + kode;
            var kasirPromise = $http.get(url);
            kasirPromise.then(function(response){
                $scope.deskripsi = response.data;
            });
        }

        function cariByNama(nama){
            var url = baseURL + "/produk/cari-by-nama?nama=" + nama;
            var kasirPromise = $http.get(url);
            kasirPromise.then(function(response){
                $scope.produk = response.data;
            });
        }

        /**
         ** ###KERANJANG###
         **/

         if(!angular.isUndefined($cookies.get('total'))){
            $scope.total = parseFloat($cookies.get('total'));
         }

         if(!angular.isUndefined($cookies.get('cart'))){
            $scope.cart = $cookies.getObject('cart');
         }

         // TODO cek stok sebelum tambah ke Cart
         function addToCart(produk){
            if($scope.cart.length === 0){
                produk.jumlah_beli = 1;
                $scope.cart.push(produk);
            }else{
                var repeat = false;
                for(var i = 0; i < $scope.cart.length; i++){
                    if($scope.cart[i].kode_produk === produk.kode_produk){
                        repeat = true;
                        $scope.cart[i].jumlah_beli += 1;
                    }
                }
                if(!repeat){
                    produk.jumlah_beli = 1;
                    $scope.cart.push(produk);
                }
            }
            var expireDate = new Date();
            expireDate.setDate(expireDate.getDate() + 1);
            $cookies.putObject('cart', $scope.cart, {'expires': expireDate});
            $scope.cart = $cookies.getObject('cart');

            $scope.total += parseFloat(produk.harga_jual);
            $cookies.put('total', $scope.total, {'expires': expireDate});
         }

         function removeFromCart(produk){
             if(produk.jumlah_beli > 1){
                 produk.jumlah_beli -= 1;
                 var expireDate = new Date();
                 expireDate.setDate(expireDate.getDate() + 1);
                 $cookies.putObject('cart', $scope.cart, {'expires': expireDate});
                 $scope.cart = $cookies.getObject('cart');
             }else if(produk.jumlah_beli === 1){
                 var index = $scope.cart.indexOf(produk);
                 $scope.cart.splice(index, 1);
                 expireDate = new Date();
                 expireDate.setDate(expireDate.getDate() + 1);
                 $cookies.putObject('cart', $scope.cart, {'expires': expireDate});
                 $scope.cart = $cookies.getObject('cart');
             }

             $scope.total -= parseFloat(produk.harga_jual);
             $cookies.put('total', $scope.total, {'expires': expireDate});
         }
    }]);

})();