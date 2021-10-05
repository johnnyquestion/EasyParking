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
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<%
    String respuesta = "";
    String proceso = request.getParameter("proceso"); //request HTTP 
//a los request se les puede pasar parámetros
//se va a validar el tipo de proceso

    Vehiculo v = new Vehiculo(); //se piden los parámetros del contacto que se quiere guardar
    switch (proceso) {
        case "guardarVehiculo":
            System.out.println("Guardar Vehiculo");
            v.setVeh_placa(request.getParameter("placa")); //a este hay que convertirlo de entero a string
            v.setVeh_color(request.getParameter("color"));
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date date = format.parse(request.getParameter("fecha"));
            java.sql.Date sql = new java.sql.Date(date.getTime());
            System.out.println(sql);
            v.setFecha(sql);
            v.setVeh_modelo(request.getParameter("modelo"));
            v.setTipo_Vehiculo_idTipo_Vehiculo(request.getParameter("tipoVehiculo"));

            if (v.guardarVehiculo()) {
                //si guarda bien el contacto, se concatena otros datos para el json
                respuesta += "\"" + proceso + "\": true";  // el \ se usa para concatenar en json indicando que se hizo el proceso (true)
            } else {
                respuesta += "\"" + proceso + "\": false";  // el \ se usa para concatenar en json indicando que NO se hizo el proceso (false)
            }

            break;
        case "borrarVehiculo":
            System.out.println("Eliminar Vehiculo");
            String placa = request.getParameter("placa");
            if (v.borrarVehiculo(placa)) {
                respuesta += "\"" + proceso + "\": true";  // el \ se usa para concatenar en json indicando que se hizo el proceso (true)
            } else {
                respuesta += "\"" + proceso + "\": false";  // el \ se usa para concatenar en json indicando que NO se hizo el proceso (false)
            }

            break;
        case "listarVehiculo":
            System.out.println("Listar Vehiculos");
            List<Vehiculo> listaVehiculo = v.listarVehiculo();
            if (listaVehiculo.isEmpty()) {
                respuesta += "\"" + proceso + "\": true,\"Contactos\":[]"; //genera una lista vacía en el json
            } else {
                respuesta += "\"" + proceso + "\": true,\"Contactos\":" + new Gson().toJson(listaVehiculo); //guarda la lista en el json
            }

            break;
        case "actualizarVehiculo":

            System.out.println("Actualizar Vehiculo");
            v.setVeh_placa(request.getParameter("placa")); 
            v.setVeh_color(request.getParameter("color"));
            v.setVeh_modelo(request.getParameter("modelo"));
            v.setTipo_Vehiculo_idTipo_Vehiculo(request.getParameter("TipoVeh"));

            if (v.actualizarVehiculo()) {
                //si guarda bien el contacto, se concatena otros datos para el json
                respuesta += "\"" + proceso + "\": true";  // el \ se usa para concatenar en json indicando que se hizo el proceso (true)
            } else {
                respuesta += "\"" + proceso + "\": false";  // el \ se usa para concatenar en json indicando que NO se hizo el proceso (false)
            }

            break;

        default:
            respuesta += "\"ok\": false,";
            respuesta += "\"error\": \"INVALID\",";
            respuesta += "\"errorMsg\": \"Lo sentimos, los datos que ha enviado,"
                    + " son inválidos. Corrijalos y vuelva a intentar por favor.\"";
            break;
    }

    // cierra la respuesta
    respuesta += "}";
    response.setContentType("application/json;charset=iso-8859-1");
    out.print(respuesta);

%>

