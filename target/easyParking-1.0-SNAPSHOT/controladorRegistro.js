var app = angular.module('easyParking', []);
app.controller('registroControlador', function ($scope, $http) {
    $scope.registrarVehiculo = function () {
        console.log('guardar');

        if ($scope.tipoVehiculo === undefined || $scope.fecha === undefined || $scope.hora === undefined || $scope.tipoVehiculo === undefined) {
            alert("Todos los campos son obligatorios.")
        } else {
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
            }).then(function (respuesta) {
                console.log(respuesta);
                alert("Guardado");
            });
        };
    };
    $scope.listarVehiculos = function(){
        $scope.mostrarListaVehiculos = true;
        let params = {
            proceso: 'listarVehiculo'
        };
        $http({
            method: 'GET',
            url: 'peticionesVehiculo.jsp',
            params: params
        }).then(function(respuesta){
            console.log(respuesta);
            $scope.registros = respuesta.data.Vehiculos;
            console.log($scope.registros);
        });
    };
    $scope.borrarVehiculo = function(placa){
        let params = {
            proceso: 'borrarVehiculo',
            placa: placa
        };
        console.log(params)
        $http({
            method: 'GET',
            url: 'peticionesVehiculo.jsp',
            params: params
        }).then(function(respuesta){
            console.log(respuesta);
            if(respuesta.data.borrarVehiculo){
                alert("Borrado con éxito")
                $scope.listarVehiculos();
            } else{
                alert("No se pudo XD")
            }
            /*$scope.registros = respuesta.data.Vehiculos;
            console.log($scope.registros);*/
        });
    };
    
    
    
    $scope.mostrarRegistroVehiculo = function(){
        $scope.mostrarListaVehiculos = false;
    }
});

