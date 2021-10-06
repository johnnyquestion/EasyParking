var app = angular.module('easyParking', []);
app.controller('registroControlador', function ($scope, $http) {
    $scope.registrarVehiculo = function () {
        console.log('guardar');

        if ($scope.tipoVehiculo === undefined || $scope.fecha === undefined || $scope.hora === undefined || $scope.tipoVehiculo === undefined) {
            alert("Todos los campos son obligatorios.");
        } else {
            let registro = {
                proceso: 'guardarVehiculo',
                placa: $scope.placa,
                fecha: $scope.fecha,
                hora: $scope.hora,
                tipoVehiculo: $scope.tipoVehiculo
            };
            console.log(fecha);               

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
            if (respuesta.status === 200) {
                $scope.registros = respuesta.data.data;
            } else {
                console.error(respuesta);
            }
        });
    };
    $scope.borrarVehiculo = function(placa){
        let params = {
            proceso: 'borrarVehiculo',
            placa: placa
        };
        console.log(params);
        $http({
            method: 'GET',
            url: 'peticionesVehiculo.jsp',
            params: params
        }).then(function(respuesta){
            console.log(respuesta);
            if(respuesta.data.success){
                alert("Borrado con éxito");
                $scope.listarVehiculos();
            } else{
                alert("No se pudo XD");
            }
        });
    };
    
    $scope.actualizarVehiculo = function(){
        if ($scope.tipoVehiculo === undefined || $scope.fecha === undefined || $scope.hora === undefined || $scope.tipoVehiculo === undefined) {
            alert("Todos los campos son obligatorios.");
        } else {
            let params = {
                proceso: 'actualizarVehiculo',
                placa: $scope.placa,
                fecha: $scope.fecha,
                hora: $scope.hora,
                tipoVehiculo: $scope.tipoVehiculo
            };
            console.log(params);
            $http({
                method: 'GET',
                url: 'peticionesVehiculo.jsp',
                params: params
            }).then(function (respuesta) {
                console.log(respuesta);
                alert("Actualizado");
            });
        };
    };
    
    $scope.mostrarRegistroVehiculo = function(){
        console.log('Im here');
        $scope.mostrarListaVehiculos = false;
        $scope.actualizar = false;
        $scope.registroActualizar = undefined;
        $scope.placa = undefined;
        $scope.fecha = undefined;
        $scope.hora = undefined;
        $scope.tipoVehiculo = undefined;
    };
    
    $scope.mostrarActualizar = function (registro) {
        console.log(registro);
        $scope.mostrarListaVehiculos = false;
        $scope.actualizar = true;
        $scope.registroActualizar = registro;
        $scope.placa = registro.veh_placa;
        $scope.fecha = registro.fecha;
        $scope.hora = registro.hora;
        $scope.tipoVehiculo = registro.Tipo_Vehiculo_idTipo_Vehiculo;
    };
});

