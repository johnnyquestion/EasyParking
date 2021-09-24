/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Persistencia;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author neisa
 */
public class Conexion {
    //Atributos
    public Connection conexion;
    private final String driver = "com.mysql.jdbc.Driver";
    private final String user = "root";
    private final String pass = "";
    private final String url = "jdbc:mysql://localhost:3306/mintic?zeroDateTimeBehavior=CONVERT_TO_NULL";
    private ResultSet rs = null; //atributo que retorne la consulta de la DB
    private Statement stmt = null; //para ejecutar queries (sentencias)
      
    
    //Metodos
    public void conectar(){
        try{
            Class.forName(driver);
            // Nos conectamos a la bd
            conexion= (Connection) DriverManager.getConnection(url, user, pass);
            System.out.println("Conectado");
            // Si la conexion fue exitosa mostramos un mensaje de conexion exitosa
            if (conexion!=null){
                JOptionPane.showMessageDialog(null,"Conexion Correcta");
            }
        }
        // Si la conexion NO fue exitosa mostramos un mensaje de error
        catch(Exception error){
            JOptionPane.showMessageDialog(null,"Conexion Incorrecta"+error);
        }
    }
    
    public void desconectar(){
        conexion = null;
    }
    
    public ResultSet consultarBD(String sentencia){
        //en sentencia se envia el codigo SQL
        try {
            stmt = conexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY); 
            rs = stmt.executeQuery(sentencia);
        } catch (SQLException | RuntimeException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error al hacer una consulta");
        }
        return rs;
    }
    
    //Insertar
    public boolean insertarBD(String sentencia){
        try {
            stmt = conexion.createStatement();
            stmt.execute(sentencia);
            return true;
        } catch (SQLException | RuntimeException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error al insertar en la BD");
            return false;
        }
    }
    
    //Borrar
    public boolean borrarBD(String sentencia){
        try {
            stmt = conexion.createStatement();
            stmt.execute(sentencia);
            return true;
        } catch (SQLException | RuntimeException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error al borrar en la BD");
            return false;
        }
    }
    
    //Actualizar
    public boolean actualizarBD(String sentencia){
            try {
            stmt = conexion.createStatement();
            stmt.execute(sentencia);
            return true;
        } catch (SQLException | RuntimeException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error al actualizar en la BD");
            return false;
        }
    }

    public boolean setAutoCommitBD(boolean commit){
        try {
            conexion.setAutoCommit(commit);
            return true;
        } catch (SQLException | RuntimeException ex) {
            System.out.println("Error en set Autocommit");
            return false;
        }
    }
    
    public boolean commitBD(){
        try {
            conexion.commit();
            return true;
        } catch (SQLException | RuntimeException ex) {
            System.out.println("Error en commit a la BD");
            return false;
        }
    }
    
    public boolean rollbackBD(){
            try {
            conexion.rollback();
            return true;
        } catch (SQLException | RuntimeException ex) {
            System.out.println("Error en rollback a la BD");
           return false;
        }
    }

}
