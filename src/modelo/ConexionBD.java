/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author J.A.R.R
 */
public class ConexionBD {
    private String driverBD;
    private String UserBD;
    private String passwordBD;
    private String urlBD;
    
    private Connection conexion;
    private String mensajes;
    
    //Constructor
    public ConexionBD() {
        this.driverBD="com.mysql.cj.jdbc.Driver";
        this.UserBD="root";
        this.passwordBD="123456";
        this.urlBD="jdbc:mysql://localhost:3306/bd_sistema_login";
    }
//    public ConexionBD() {
//           this.driverBD="com.mysql.cj.jdbc.Driver";
//        this.UserBD="administrador";
//        this.passwordBD="1234567+";
//        this.urlBD="jdbc:mysql://192.168.137.1:3306/bd_sistema_login";
//    }

    public ConexionBD(String driverBD, String UserBD, String passwordBD, String urlBD) {
        this.driverBD = driverBD;
        this.UserBD = UserBD;
        this.passwordBD = passwordBD;
        this.urlBD = urlBD;
    }
    
    //Metodos set y get

    public String getDriverBD() {
        return driverBD;
    }

    public void setDriverBD(String driverBD) {
        this.driverBD = driverBD;
    }

    public String getUserBD() {
        return UserBD;
    }

    public void setUserBD(String UserBD) {
        this.UserBD = UserBD;
    }

    public String getPasswordBD() {
        return passwordBD;
    }

    public void setPasswordBD(String passwordBD) {
        this.passwordBD = passwordBD;
    }

    public String getUrlBD() {
        return urlBD;
    }

    public void setUrlBD(String urlBD) {
        this.urlBD = urlBD;
    }

    public Connection getConexion() {
        return conexion;
    }

    public void setConexion(Connection conexion) {
        this.conexion = conexion;
    }

    public String getMensajes() {
        return mensajes;
    }

    public void setMensajes(String mensajes) {
        this.mensajes = mensajes;
    }
    //Metodo para establecer la conexion al servidor de BD
    public boolean openConectionBD(){
        try {
            //1.- Registrar el driver
            Class.forName(this.driverBD);
            
            //2.- Establecer la conexion
            this.conexion=DriverManager.getConnection(urlBD, UserBD, passwordBD);
            this.mensajes="se establecio la conexion...";
            return true;
        } catch (Exception e) {
            this.mensajes="Error de conexion: " + e.getMessage();
        }
        return false;
    }
    
    //Metodo para cerrar la conexion al server
    public boolean closeConexionBD(){
        try {
            if (this.conexion!=null) {
                this.conexion.close();
                this.mensajes="se cerro la conexion...";
                return true;
            }
        } catch (Exception e) {
            this.mensajes="Error: " + e.getMessage();
        }
        return false;
    }
}
