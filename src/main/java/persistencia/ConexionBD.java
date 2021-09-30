/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;
import java.sql.*;

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
        db = "mintic"; // nombre de bd
        url = "jdbc:mysql://" + host + "/" + db; // dato que se crea concatenado
        username = "root";
        password = "";
        DB_driver = "com.mysql.jdbc.Driver";// de acuerdo a la dependencia que estamos usando
        try {
            Class.forName(DB_driver); //Se asigna el driver
        } catch (ClassNotFoundException ex) {
            //Logger.getLogger(ConexionBD.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error al asignar driver");
        }

        //Conectar a la BD
        try {
            conexion = DriverManager.getConnection(url, username, password); // al atributo conexion se le envia
            System.out.println("Conexion exitosa");

        } catch (SQLException ex) {
            //Logger.getLogger(ConexionBD.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error al conectar la BD");
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
                System.out.println("Error al cerrar la conexion");
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
            System.out.println("Error al hacer una consulta");
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
            //Logger.getLogger(ConexionBD.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error al insertar en la BD");
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
            //Logger.getLogger(ConexionBD.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error al borrar en la BD");
            return false;
        }
    }
    //Actualizar
    public boolean actualizarBD(String sentencia) {
        try {
            stmt = conexion.createStatement();
            stmt.execute(sentencia);
            return true;
        } catch (SQLException | RuntimeException ex) {
            //Logger.getLogger(ConexionBD.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error al actualizar en la BD");
            return false;
        }
    }
    //autocommit
    public boolean setAutoCommitBD(boolean commit) {
        try {
            conexion.setAutoCommit(commit); // este metodo es del obj conexion, va a ir en false
            return true;
        } catch (SQLException | RuntimeException ex) {
            System.out.println("Error en set Autocommit");
            return false;
        }
    }
    //commit
    public boolean commitBD() {
        try {
            conexion.commit();
            return true;
        } catch (SQLException | RuntimeException ex) {
            System.out.println("Error en commit a la BD");
            return false;
        }
    }
    //rollback
    public boolean rollbackBD() {
        try {
            conexion.rollback();
            return true;
        } catch (SQLException | RuntimeException ex) {
            System.out.println("Error en rollback a la BD");
           return false;
        }
    }

}//cierre de la clase
