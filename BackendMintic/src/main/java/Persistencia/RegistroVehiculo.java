/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import java.sql.PreparedStatement;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Abraham Cañon
 */
public class RegistroVehiculo {
    //Atributos
    private double precioServicio;
    
    //Construcctor
    public RegistroVehiculo() {
    }
    
    //Encapsular "Getters y Setters"
    public double getPrecioServicio() {
        return precioServicio;
    }

    public void setPrecioServicio(double precioServicio) {
        this.precioServicio = precioServicio;
    }
        
    
    //Metodos
    public void registarVehiculo(){
        Conexion objConector = new Conexion();
        objConector.conectar();
        try{
            String sql = "INSERT INTO vehiculo "
                    + "(veh_placa, veh_color, veh_modelo )"
                    + "VALUES(?,?,?);";
            PreparedStatement declaracion;
            declaracion = objConector.conn.prepareStatement(sql);
            declaracion.setDouble(1, this.precioServicio);

            declaracion.execute();
        }
        catch(Exception error){
            JOptionPane.showMessageDialog(null, "Error: "+error);
        }
        objConector.desconectar();
    }
        

    
    /*public List<Vehiculo> listarVehiculo(){
     
        return null;
    }
    
    public void actualizarVehiculo(){
        
    }
    
    public void eliminarVehiculo(){
        
    }*/
    
}
