/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

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
    //private String tipoVehiculo;
    
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

    /*public String getTipoVehiculo() {
        return tipoVehiculo;
    }

    public void setTipoVehiculo(String tipoVehiculo) {
        this.tipoVehiculo = tipoVehiculo;
    }*/
    
            
    //Metodos
    public boolean registarVehiculo(){
        Conexion objConector = new Conexion();
        //objConector.conectar();
        try{
            String sql = "INSERT INTO vehiculo "
                    + "(veh_placa, veh_color, veh_modelo, Tipo_Vehiculo_idTipo_Vehiculo )"
                    + "VALUES('" + this.placa + "','" + this.color + "','" 
                    + this.modelo + "');  ";

            if(objConector.setAutoCommitBD(false)){
                if(objConector.insertarBD(sql)){
                    objConector.commitBD();
                    objConector.desconectar();
                    return true;
                } else{ //si no logro insertar en la BD
                    objConector.rollbackBD();
                    objConector.desconectar();
                    return false;
                }
            } else{
                objConector.desconectar();
                return false;
            }
        }
        catch(Exception error){
            JOptionPane.showMessageDialog(null, "Error: "+error);
        }
        objConector.desconectar();
        return false;
    }
        

    
    /*public List<Vehiculo> listarVehiculo(){
     
        return null;
    }
    
    public void actualizarVehiculo(){
        
    }
    
    public void eliminarVehiculo(){
        
    }*/
    
}
