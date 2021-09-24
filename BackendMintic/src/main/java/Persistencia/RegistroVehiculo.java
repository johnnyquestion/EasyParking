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
    private String placa;
    private String color;
    private String modelo;
    
    //Construcctor
    public RegistroVehiculo() {
    }
    
    //Encapsular "Getters y Setters"

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
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
            declaracion.setString(1, this.placa);
            declaracion.setString(2, this.color);
            declaracion.setString(1, this.modelo);

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
