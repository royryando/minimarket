
(function(){

	var app = angular.module('miniMarket', ['ngCookies']);
    app.controller('KasirController', ['$scope','$cookies','$http', function($scope, $cookies, $http){

        var baseURL = 'http://localhost:8080/api';

        $scope.searchNama = '';
        $scope.produk = [];
        $scope.deskripsi = [];
        $scope.cart = [];
        $scope.total = 0;
        $scope.tanggal = '';
        $scope.jam = '';
        $scope.kode_transaksi = '';
        $scope.bayar = 0;
        $scope.kembali = 0;

        $scope.listProduk = listProduk;
        $scope.deskripsiProduk = deskripsiProduk;
        $scope.cariByNama = cariByNama;
        $scope.addToCart = addToCart;
        $scope.removeFromCart = removeFromCart;
        $scope.getKodeTransaksi = getKodeTransaksi;
        $scope.proses = proses;
        $scope.hitungUang = hitungUang;
        $scope.resetTr = resetTr;
        $scope.inputBarang = inputBarang;

        init();

        function init(){
            listProduk();
            getKodeTransaksi();
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

         if(!angular.isUndefined($cookies.get('total'))){
            $scope.total = parseFloat($cookies.get('total'));
         }

         if(!angular.isUndefined($cookies.get('cart'))){
            $scope.cart = $cookies.getObject('cart');
         }

         // TODO cek stok sebelum tambah ke Cart
         function addToCart(produk, stok){
             var expireDate = new Date();
             expireDate.setDate(expireDate.getDate() + 1);
            if($scope.cart.length === 0){
                produk.jumlah_beli = 1;
                $scope.cart.push(produk);
            }else{
                var repeat = false;
                for(var i = 0; i < $scope.cart.length; i++){
                    if($scope.cart[i].kode_produk === produk.kode_produk){
                        repeat = true;
                        if($scope.cart[i].jumlah_beli < stok){
                            $scope.cart[i].jumlah_beli += 1;
                        }
                    }
                }
                if(!repeat){
                    produk.jumlah_beli = 1;
                    $scope.cart.push(produk);
                    $scope.total += parseFloat(produk.harga_jual);
                }

            }
            $cookies.putObject('cart', $scope.cart, {'expires': expireDate});
            $scope.cart = $cookies.getObject('cart');
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

         function getKodeTransaksi(){
             var kodeTransaksi;
             var hariString = '';
             var tgl = new Date().getDate();
             if(tgl.length==1){tgl = '0' + tgl;}
             var hari = new Date().getDay().toString();
             var bulan = new Date().getMonth().toString();
             if(bulan.length==1){bulan = '0' + bulan;}
             var tahun = new Date().getFullYear().toString();
             var jam = new Date().getHours().toString();
             if(jam.length==1){jam = '0' + jam;}
             var menit = new Date().getMinutes().toString();
             if(menit.length==1){menit = '0' + menit;}
             var detik = new Date().getSeconds().toString();
             if(detik.length==1){detik = '0' + detik;}

             switch(hari){
                 case "0":
                     hariString = 'MU';
                     break;
                 case "1":
                     hariString = 'SN';
                     break;
                 case "2":
                     hariString = 'SA';
                     break;
                 case "3":
                     hariString = 'RU';
                     break;
                 case "4":
                     hariString = 'KS';
                     break;
                 case "5":
                     hariString = 'JT';
                     break;
                 case "6":
                     hariString = 'SU';
                     break;
                 default:
                     hariString = 'ER';
                 break;
             }
             kodeTransaksi = hariString + bulan + tahun + menit + detik;
             $scope.kode_transaksi = kodeTransaksi;
             $scope.tanggal = tahun + '-' + bulan + '-' + tgl;
             $scope.jam = jam + ':' + menit + ':' + detik;
             return kodeTransaksi;
         }

         function proses(){
             var url = baseURL + '/transaksi/createupdate';
             $scope.kode_transaksi = getKodeTransaksi();
             if($scope.bayar==0){
                 alert('Uang tidak bisa 0');
             }else if($scope.bayar<$scope.total){
                 alert('Silahkan masukkan uang lebih')
             }else{
                 $scope.kembali = $scope.bayar - $scope.total;
                 var p = $http.post(url, {
                     kode_transaksi: $scope.kode_transaksi,
                     tanggal: $scope.tanggal,
                     jam: $scope.jam,
                     total_harga: $scope.total,
                     bayar: $scope.bayar,
                     kembali: $scope.kembali
                 });
                 p.then(function(){
                     inputBarang();
                 });
             }
         }

         function resetTr(){
             $scope.kode_transaksi = '';
             $scope.bayar = 0;
             $scope.kembali = 0;
             $scope.total = 0;
             $scope.cart = [];
             var expireDate = new Date();
             expireDate.setDate(expireDate.getDate() + 1);
             $cookies.putObject('cart', $scope.cart, {'expires': expireDate});
             $cookies.put('total', $scope.total, {'expires': expireDate});
             init();
        }

         function hitungUang(){
             if($scope.bayar<$scope.total){
                 $scope.kembali = 0;
             }else{
                 $scope.kembali = $scope.bayar - $scope.total;
             }
         }

         function inputBarang(){
             var url = baseURL + '/barang/insert';
             var i = $scope.cart.length-1;
             while(i!=-1){
                 var p = $http.post(url, {
                     id:0,
                     kode_transaksi:$scope.kode_transaksi,
                     kode_produk:$scope.cart[i].kode_produk,
                     nama_produk:$scope.cart[i].nama,
                     harga_produk:$scope.cart[i].harga_jual,
                     jumlah_beli:$scope.cart[i].jumlah_beli,
                     total_harga:$scope.cart[i].harga_jual*$scope.cart[i].jumlah_beli,
                     laba:($scope.cart[i].harga_jual-$scope.cart[i].harga_beli)*$scope.cart[i].jumlah_beli
                 });
                 p.then(function() {
                 });
                 var url2 = baseURL + '/produk/update-stok?kode=' + $scope.cart[i].kode_produk + '&beli=' + $scope.cart[i].jumlah_beli;
                 $http.get(url2);
                 i--;
             }

             resetTr();
         }
    }]);

})();