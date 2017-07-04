/**
 * 
 */
(function(){

    var app = angular.module('miniMarket', ['ngCookies']);

    app.controller('KasirController', ['$scope', '$cookies', '$http', function($scope, $cookies, $http){

        var baseURL = 'http://localhost:8080/api';

        $scope.searchNama = '';

        $scope.produk = [];
        $scope.deskripsi = [];
        $scope.keranjang = [];
        $scope.total = 0;

        $scope.listProduk = listProduk;
        $scope.deskripsiProduk = deskripsiProduk;
        $scope.cariByNama = cariByNama;
        $scope.tambahKeKeranjang = tambahKeKeranjang;
        $scope.hapusDariKeranjang = hapusDariKeranjang;

        init();

        function init(){
            listProduk();
        }

        function listProduk(){
            var url = baseURL + '/produk/list';
            var kasirPromise = $http.get(url);
            kasirPromise.then(function(response){
                $scope.produk = response.data;
            });
        }

        function deskripsiProduk(kode){
            var url = baseURL + '/produk/cari-by-kode/' + kode;
            var kasirPromise = $http.get(url);
            kasirPromise.then(function(response){
                $scope.deskripsi = response.data;
            });
        }

        function cariByNama(nama){
            var url = baseURL + '/produk/cari-by-nama?nama=' + nama;
            var kasirPromise = $http.get(url);
            kasirPromise.then(function(response){
                $scope.produk = response.data;
            });
        }

        if(!angular.isUndefined($cookies.get('total'))){
            $scope.total = parseFloat($cookies.get('total'));
        }
        if(!angular.isUndefined($cookies.get('keranjang'))){
            $scope.keranjang = $cookies.getObject('keranjang');
        }

        function tambahKeKeranjang(produk){
            if($scope.keranjang.length === 0){
                produk.jumlah_beli = 1;
                $scope.keranjang.push(produk);
            }else{
                var ulang = false;
                for(var i = 0; i < $scope.keranjang.length; i++){
                    if($scope.keranjang[i].kode_produk === produk.kode_produk){
                        ulang = true;
                        $scope.keranjang[i].jumlah_beli += 1;
                    }
                }
                if(!ulang){
                    produk.jumlah_beli = 1;
                    $scope.keranjang.push(produk);
                }
            }

            var expireDate = new Date();
            expireDate.setDate(expireDate.getDate() + 1);
            $cookies.putObject('keranjang', $scope.keranjang, {'expires': expireDate});
            $scope.keranjang = $cookies.getObject('keranjang');

            $scope.total += parseFloat(produk.harga_jual);
            $cookies.put('total', $scope.total, {'expires': expireDate});
        }

        function hapusDariKeranjang(produk){
            if(produk.jumlah_beli > 1){
                produk.jumlah_beli -= 1;
                var expireDate = new Date();
                expireDate.setDate(expireDate.getDate() + 1);
                $cookies.putObject('keranjang', $scope.keranjang, {'expires': expireDate});
                $scope.keranjang = $cookies.getObject('keranjang');
            }else if(produk.jumlah_beli === 1){
                var index  = $scope.keranjang.indexOf(produk);
                $scope.keranjang.splice(index, 1);
                expireDate = new Date();
                expireDate.setDate(expireDate.getDate() + 1);
                $cookies.putObject('keranjang', $scope.keranjang, {'expires': expireDate});
                $scope.keranjang = $cookies.getObject('keranjang');
            }

            $scope.total -= parseFloat(produk.harga_jual);
            $cookies.put('total', $scope.total, {'expires': expireDate});
        }

    }]);
})();