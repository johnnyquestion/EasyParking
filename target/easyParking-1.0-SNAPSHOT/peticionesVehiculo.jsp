<%-- 
    Document   : peticionesVehiculo
    Created on : 24/09/2021, 03:46:59 PM
    Author     : USUARIO
--%>
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

    Vehiculo v = new Vehiculo(); //se piden los parámetros del contacto que se quiere guardar
    switch (proceso) {
        case "guardarVehiculo":
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
            String placa = request.getParameter("placa");
            respuesta.setSuccess(v.borrarVehiculo(placa));
            break;
        case "listarVehiculo":
            List<Vehiculo> listaVehiculos = v.listarVehiculo();            
            respuesta = new Response<Vehiculo>(true, listaVehiculos);
            break;
        case "actualizarVehiculo":

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
            v.setPlacaAntigua(request.getParameter("placaAntigua"));
            respuesta.setSuccess(v.actualizarVehiculo());
            
            break;
        default:
            respuesta.setMessage("Lo sentimos, los datos que ha enviado, son inválidos."
                    + " Corrijalos y vuelva a intentar por favor.");
            break;
    }
    response.setContentType("application/json;charset=iso-8859-1");
    out.print(respuesta.toJson());
%>

