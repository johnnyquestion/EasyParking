<%-- 
    Document   : peticionesRegistroVehiculo
    Created on : 23/09/2021, 10:14:32 PM
    Author     : Abraham Cañon
--%>

<%@page import="Persistencia.RegistroVehiculo"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    String respuesta = "";
    String proceso = request.getParameter("proceso");

    switch (proceso) {

        case "registrarVehiculo":
            RegistroVehiculo registroVehiculo = new RegistroVehiculo();
            registroVehiculo.setPlaca(request.getParameter("placa"));
            registroVehiculo.setColor(request.getParameter("color"));
            registroVehiculo.setModelo(request.getParameter("modelo"));
            //registroVehiculo.setTipoVehiculo(request.getParameter("tipoVehiculo"));
            if (registroVehiculo.registarVehiculo()) {
                respuesta += "\"" + proceso + "\": true";
            } else {
                respuesta += "\"" + proceso + "\": false";
            }
            break;
        default:
        respuesta += "\"ok\": false,";
        respuesta += "\"error\": \"INVALID\",";
        respuesta += "\"errorMsg\": \"Lo sentimos, los datos que ha enviado,"
                + " son inválidos. Corrijalos y vuelva a intentar por favor.\"";    
    }    
    respuesta += "}";
    response.setContentType("application/json;charset=iso-8859-1");
    out.print(respuesta);
%>