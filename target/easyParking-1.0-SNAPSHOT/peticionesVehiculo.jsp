<%-- 
    Document   : peticionesVehiculo
    Created on : 24/09/2021, 03:46:59 PM
    Author     : USUARIO
--%>
<%@page import="logica.UsuarioData"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.google.gson.Gson"%>
<%@page import="java.util.List"%>
<%@page import="logica.Vehiculo"%>
<%@page import="modelo.Response"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<%
    Response<Vehiculo> respuesta = new Response<Vehiculo>();
    String proceso = request.getParameter("proceso"); //request HTTP 
    //a los request se les puede pasar parámetros
    //se va a validar el tipo de proceso

    Vehiculo v = new Vehiculo(); //se piden los parámetros del contacto que se quiere guardar
    UsuarioData d;
    switch (proceso) {
        case "guardarVehiculo":
            System.out.println("Guardar Vehiculo");
            v.setVeh_placa(request.getParameter("placa")); //a este hay que convertirlo de entero a string
            v.setVeh_color(request.getParameter("color"));
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date date = format.parse(request.getParameter("fecha"));
            java.sql.Date sql = new java.sql.Date(date.getTime());
            v.setFecha(sql);
            SimpleDateFormat hora = new SimpleDateFormat("hh:mm");
            Date dateHora = hora.parse(request.getParameter("hora"));
            java.sql.Time sqlHora = new java.sql.Time(dateHora.getTime());
            v.setHora(sqlHora);
            v.setVeh_modelo(request.getParameter("modelo"));
            v.setTipo_Vehiculo_idTipo_Vehiculo(request.getParameter("tipoVehiculo"));
            respuesta.setSuccess(v.guardarVehiculo());
            break;
        case "borrarVehiculo":
            System.out.println("Eliminar Vehiculo");
            String placa = request.getParameter("placa");
            respuesta.setSuccess(v.borrarVehiculo(placa));
            break;
        case "listarVehiculo":
            System.out.println("Listar Vehiculos");
            List<Vehiculo> listaVehiculos = v.listarVehiculo();            
            respuesta = new Response<Vehiculo>(true, listaVehiculos);
            break;
        case "actualizarVehiculo":

            System.out.println("Actualizar Vehiculo");
            v.setVeh_placa(request.getParameter("placa"));
            v.setVeh_color(request.getParameter("color"));
            DateFormat formatActualizar = new SimpleDateFormat("yyyy-MM-dd");
            Date dateActualizar = formatActualizar.parse(request.getParameter("fecha"));
            java.sql.Date sqlActualizar = new java.sql.Date(dateActualizar.getTime());
            v.setFecha(sqlActualizar);
            SimpleDateFormat horaActualizar = new SimpleDateFormat("hh:mm");
            Date dateHoraActualizar = horaActualizar.parse(request.getParameter("hora"));
            java.sql.Time sqlHoraActualizar = new java.sql.Time(dateHoraActualizar.getTime());
            v.setHora(sqlHoraActualizar);
            v.setVeh_modelo(request.getParameter("modelo"));
            v.setTipo_Vehiculo_idTipo_Vehiculo(request.getParameter("tipoVehiculo"));
            System.out.println(sqlActualizar);            
            respuesta.setSuccess(v.actualizarVehiculo());
            break;
                    
        case "login":
            d = new UsuarioData(request.getParameter("usuario"), request.getParameter("contrasena"));
            if (d.validarlogin(request.getParameter("usuario"), request.getParameter("contrasena"))){
                respuesta.setMessage("Usuario validado");
            }else{
                respuesta.setMessage("Error login");
            }
        break;
        
        default:
            respuesta.setMessage("Lo sentimos, los datos que ha enviado, son inválidos."
                    + " Corrijalos y vuelva a intentar por favor.");
            break;
    }
    response.setContentType("application/json;charset=iso-8859-1");
    out.print(respuesta.toJson());
%>

