var app = angular.module('easyParking', []);

app.controller('registroControlador', function ($scope, $http) {
    $scope.login = function () {
            var us_data = {
                proceso: 'login',
                usuario: $scope.usuario,
                contrasena: $scope.contrasena
            };

            $http({
                method: 'POST',
                url: 'peticionesVehiculo.jsp',
                params: us_data
            }).then(function (respuesta) {
                console.log(respuesta.data.message);
                if (respuesta.data.message === 'Usuario validado'){
                    alert("Login");
                    document.location.href = 'easyparking.html'
                }else{
                    alert("Error login");
                }
            });
        
    }
    
    $scope.registrarVehiculo = function () {
        $scope.mostrarRegistrarVehiculo = true;
        console.log('guardar');
        
        if ($scope.tipoVehiculo === undefined || $scope.fecha === undefined || $scope.hora === undefined || $scope.tipoVehiculo === undefined) {
            alert("Todos los campos son obligatorios.");
        } else {
            var registro = {
                proceso: 'guardarVehiculo',
                placa: $scope.placa,
                fecha: $scope.fecha,
                hora: $scope.hora,
                tipoVehiculo: $scope.tipoVehiculo
            };
            console.log(registro);               

            $http({
                method: 'POST',
                url: 'peticionesVehiculo.jsp',
                //url: 'https://testjohnny.free.beeceptor.com',
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
            console.log($scope.registros)
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
                alert("Actualizado");
            });
        };
    };
    
    $scope.mostrarRegistroVehiculo = function(){
        console.log('Im here');
        $scope.mostrarDatos = true;
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
        let horafortmat = registro.hora.split(" ");
        $scope.hora = horafortmat[0];
        $scope.tipoVehiculo = registro.Tipo_Vehiculo_idTipo_Vehiculo;
    };
    
    $scope.mostrarInicio = function(){
        $scope.mostrarDatos = false;
        $scope.mostrarListaVehiculos = false;
        $scope.mostrarRegistrarVehiculo = false;
        $scope.actualizar = false;
    }
});

