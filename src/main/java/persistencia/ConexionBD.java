/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author USUARIO
 */
public class ConexionBD {
    private String DB_driver = "";
    private String url = "";
    private String db = "";
    private String host = "";
    private String username = "";
    private String password = "";
    private ResultSet rs = null; //atributo que retorne la consulta de la DB
    private Statement stmt = null; //para ejecutar queries (sentencias)
    public Connection conexion = null;
    

    public ConexionBD() {
        host = "localhost:3306"; //BD local
        db = "c3s45grupo5"; // nombre de bd
        url = "jdbc:mysql://" + host + "/" + db; // dato que se crea concatenado
        username = "c3s45grupo5";
        password = "W4qYNMDd";
        DB_driver = "com.mysql.cj.jdbc.Driver";// de acuerdo a la dependencia que estamos usando
        try {
            Class.forName(DB_driver); //Se asigna el driver
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConexionBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Conectar a la BD
        try {
            conexion = DriverManager.getConnection(url, username, password); // al atributo conexion se le envia

        } catch (SQLException ex) {
            //Logger.getLogger(ConexionBD.class.getName()).log(Level.SEVERE, null, ex);

        }
    }// cierre del constructor
    // Retornar conexión a BD, metodo para devolver la conexion, abro conexion par hacer transacciones
    public Connection getConnection() {
        return conexion;
    }
    //Cierra conexión a BD, metodo que permite cerrar la conexion  quea abrimos

    public void closeConnection() {// valido si existe la conexion
        if (conexion != null) { //se valida si hay una conexión, si es nulo da un null pointer exception
            try {
                conexion.close(); // este metodo es del obt conexion
            } catch (SQLException ex) {
                //Logger.getLogger(ConexionBD.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    //Vamos a hacer un CRUD, se necesita un método para C/u de las operaciones, metodos para la conexion bd
    //Consultar datos en BD, como vamos a obtener inf de la bd, por norma se debe guardar en un tipo ResultSet
    public ResultSet consultarBD(String sentencia) {
        //en sentencia se envia el codigo SQL
        try {
            stmt = conexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY); // con parametros, solo leer
            rs = stmt.executeQuery(sentencia);
        } catch (SQLException | RuntimeException ex) {
            //Logger.getLogger(ConexionBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;        
    }
    //Insertar
    public boolean insertarBD(String sentencia) {
        try {
            stmt = conexion.createStatement();
            stmt.execute(sentencia);
            return true;
        } catch (SQLException | RuntimeException ex) {
            Logger.getLogger(ConexionBD.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    //Borrar
    public boolean borrarBD(String sentencia) {
        try {
            stmt = conexion.createStatement();
            stmt.execute(sentencia);
            return true;
        } catch (SQLException | RuntimeException ex) {
            Logger.getLogger(ConexionBD.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    //Actualizar
    public boolean actualizarBD(String sentencia) {
        try {
            stmt = conexion.createStatement();
            stmt.execute(sentencia);
            System.out.println(sentencia);
            return true;
        } catch (SQLException | RuntimeException ex) {
            Logger.getLogger(ConexionBD.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    //autocommit
    public boolean setAutoCommitBD(boolean commit) {
        try {
            conexion.setAutoCommit(commit); // este metodo es del obj conexion, va a ir en false
            return true;
        } catch (SQLException | RuntimeException ex) {
            Logger.getLogger(ConexionBD.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    //commit
    public boolean commitBD() {
        try {
            conexion.commit();
            return true;
        } catch (SQLException | RuntimeException ex) {
            Logger.getLogger(ConexionBD.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    //rollback
    public boolean rollbackBD() {
        try {
            conexion.rollback();
            return true;
        } catch (SQLException | RuntimeException ex) {
           Logger.getLogger(ConexionBD.class.getName()).log(Level.SEVERE, null, ex);
           return false;
        }
    }
}//cierre de la clase
