/*function move_up() {
    document.getElementById('keranjang').scrollTop += 10;
}

function move_down() {
    document.getElementById('keranjang').scrollTop -= 10;
}*/
$(document).ready(function() {   
    $('[data-toggle="tooltip"]').tooltip();
});

var app = angular.module('app', []);
app.controller('ctrl', function($scope) {
    $scope.user = 'Roy';
});