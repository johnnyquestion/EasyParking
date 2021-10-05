var app = angular.module('easyParking', []);
app.controller('registroControlador', function($scope, $http){
    $scope.registrarVehiculo = function(){
        console.log('guardar');
        let registro = {
            proceso: 'guardarVehiculo',
            placa: $scope.placa,
            fecha: $scope.fecha,
            hora: $scope.hora,
            tipoVehiculo: $scope.tipoVehiculo
        };
        $http({
            method: 'POST',
            url: 'peticionesVehiculo.jsp',
            params: registro
        }).then(function(respuesta){
           console.log(respuesta);
           alert("Guardado");
        });
    };
});

