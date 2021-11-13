/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import persistencia.ConexionBD;

/**
 *
 * @author Alejandro
 */
public class UsuarioData {
    private String usuario;
    private String contrasena;

    public UsuarioData(String usuario, String contrasena) {
        this.usuario = usuario;
        this.contrasena = contrasena;
    }
    
    public boolean validarlogin(String usuario_val, String contrasena_val) throws SQLException {
        ConexionBD conexion = new ConexionBD();
        String sentencia = "SELECT * FROM login;";
        List<UsuarioData> listausuario = new ArrayList<>(); // creamos una lista para los contactos
        ResultSet rs = conexion.consultarBD(sentencia);// rs es de tipo ResultSet porque obtenemos datos de la bd        
        while (rs.next()) {
            UsuarioData ud = new UsuarioData(rs.getString("usuario"), rs.getString("contrasena"));//obj tipo usuario, para tener los set y get
            listausuario.add(ud);
        }
        conexion.closeConnection();// cierro conexion
        
        //for (int i = 0; i <= listausuario.size(); i++){
            //if (listausuario.get(i).usuario.equals(usuario_val) && listausuario.get(i).contrasena.equals(contrasena_val)){
                return true;
            //}
        //}
        //return false;
    }
}
