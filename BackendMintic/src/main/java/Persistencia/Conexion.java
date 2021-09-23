/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 *
 * @author neisa
 */
public class Conexion {
    //Atributos
    public Connection conn;
    private final String driver = "com.mysql.jdbc.Driver";
    private final String user = "root";
    private final String pass = "";
    private final String url = "jdbc:mysql://localhost:3306/mintic?zeroDateTimeBehavior=CONVERT_TO_NULL";
      
    
    //Metodos
    public void conectar(){
        try{
            Class.forName(driver);
            // Nos conectamos a la bd
            conn= (Connection) DriverManager.getConnection(url, user, pass);
            System.out.println("Conectado");
            // Si la conexion fue exitosa mostramos un mensaje de conexion exitosa
            if (conn!=null){
                JOptionPane.showMessageDialog(null,"Conexion Correcta");
            }
        }
        // Si la conexion NO fue exitosa mostramos un mensaje de error
        catch(Exception error){
            JOptionPane.showMessageDialog(null,"Conexion Incorrecta"+error);
        }
    }
    
    public void desconectar(){
        conn = null;
    }

}
