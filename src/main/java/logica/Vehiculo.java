/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import persistencia.ConexionBD;


/**
 *
 * @author USUARIO
 */
public class Vehiculo {

    private String veh_placa;
    private String veh_color;
    private String veh_modelo;
    private Date fecha;
    private Time hora;
    private String Tipo_Vehiculo_idTipo_Vehiculo;

    public Vehiculo() {
    }//cierre de cons

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Time getHora() {
        return hora;
    }

    public void setHora(Time hora) {
        this.hora = hora;
    }

    public String getVeh_placa() {
        return veh_placa;
    }

    public String getVeh_color() {
        return veh_color;
    }

    public String getVeh_modelo() {
        return veh_modelo;
    }

    public String getId_tipo_veh() {
        return Tipo_Vehiculo_idTipo_Vehiculo;
    }

    public void setVeh_placa(String veh_placa) {
        this.veh_placa = veh_placa;
    }

    public void setVeh_color(String veh_color) {
        this.veh_color = veh_color;
    }

    public void setVeh_modelo(String veh_modelo) {
        this.veh_modelo = veh_modelo;
    }

    public void setTipo_Vehiculo_idTipo_Vehiculo(String Tipo_Vehiculo_idTipo_Vehiculo) {
        this.Tipo_Vehiculo_idTipo_Vehiculo = Tipo_Vehiculo_idTipo_Vehiculo;
    }
    //metodos para el CRUD de Contacto

    @Override
    public String toString() {
        return "Vehiculo{" + "veh_placa=" + veh_placa + ", veh_color=" + veh_color + ", veh_modelo=" + veh_modelo + ", fecha=" + fecha + ", hora=" + hora + ", Tipo_Vehiculo_idTipo_Vehiculo=" + Tipo_Vehiculo_idTipo_Vehiculo + '}';
    }

    public boolean guardarVehiculo() {
        ConexionBD conexion = new ConexionBD();
        String sentencia = "INSERT INTO vehiculo(veh_placa, fecha, hora, Tipo_Vehiculo_idTipo_Vehiculo)"
                + " VALUES ( '" + this.veh_placa + "','" + this.fecha + "',"
                + "'" + this.hora + "','" + this.Tipo_Vehiculo_idTipo_Vehiculo + "');  ";
        //Vamos a configurar el setAutocommit de la conexionBD a falso
        System.out.println(this.toString());
        System.out.println("Esta es la sentencia"+ sentencia);
        if (conexion.setAutoCommitBD(false)) {// autocommit en false
            if (conexion.insertarBD(sentencia)) {// llamo al metodo insertar de ConexionBD
                conexion.commitBD();
                conexion.closeConnection();
                System.out.println("estes es la sentecia"+sentencia);
                return true;
            } else { //si no logro insertar en la BD
                conexion.rollbackBD();
                conexion.closeConnection();
                return false;
            }
        } else {
            conexion.closeConnection();
            return false;
        }

    }

    public boolean borrarVehiculo(String veh_placa) {
        ConexionBD conexion = new ConexionBD();
        String sentencia = "DELETE FROM vehiculo WHERE veh_placa = '" + veh_placa + "'";
        if (conexion.setAutoCommitBD(false)) {
            if (conexion.borrarBD(sentencia)) {
                conexion.commitBD();
                conexion.closeConnection();
                return true;
            } else {
                conexion.rollbackBD();
                conexion.closeConnection();
                return false;
            }
        } else {
            conexion.closeConnection();
            return false;
        }

    }

    public List<Vehiculo> listarVehiculo() throws SQLException {
        ConexionBD conexion = new ConexionBD();
        String sentencia = "SELECT * FROM vehiculo ORDER BY fecha DESC;";
        List<Vehiculo> listaVehiculo = new ArrayList<>(); // creamos una lista para los contactos
        ResultSet rs = conexion.consultarBD(sentencia);// rs es de tipo ResultSet porque obtenemos datos de la bd        
        while (rs.next()) {
            Vehiculo vehiculo = new Vehiculo();//obj tipo contacto, para tener los set y get
            vehiculo.setVeh_placa(rs.getString("veh_placa"));//seteamos los atributos
            vehiculo.setFecha(rs.getDate("Fecha"));
            vehiculo.setHora(rs.getTime("hora"));
            vehiculo.setTipo_Vehiculo_idTipo_Vehiculo(rs.getString("Tipo_Vehiculo_idTipo_Vehiculo"));
            listaVehiculo.add(vehiculo);
            System.out.println(rs.getTime("hora"));
            System.out.println(rs.getString("hora"));

        }
        conexion.closeConnection();// cierro conexion
        return listaVehiculo;// retorno la lista

    }

    public boolean actualizarVehiculo() {
        ConexionBD conexion = new ConexionBD();
        String sentencia;
        sentencia = "UPDATE `vehiculo` SET fecha='" + this.fecha + "',hora='" + this.hora + "',Tipo_Vehiculo_idTipo_Vehiculo='" + this.Tipo_Vehiculo_idTipo_Vehiculo
                + "' WHERE veh_placa='" + this.veh_placa + "';";
        if (conexion.setAutoCommitBD(false)) {
            if (conexion.actualizarBD(sentencia)) {
                conexion.commitBD();
                conexion.closeConnection();
                return true;
            } else {
                conexion.rollbackBD();
                conexion.closeConnection();
                return false;
            }

        } else {
            conexion.closeConnection();
            return false;
        }

    }

}//cierre de la clase
