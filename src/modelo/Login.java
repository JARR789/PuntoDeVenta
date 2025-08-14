/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import com.mysql.cj.jdbc.CallableStatement;
import java.util.Date;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 *
 * @author J.A.R.R
 */
public class Login extends ConexionBD {
    //Atruibutos
    private int idLogin;
    private String nombreLogin;
    private String passwordLogin;
    private Date fechaCreacionLogin;
    private boolean estatusLogin;
    
    //declaracion  de los objetos RolUsuario y Usuario
    private Usuario usuario;
    private RolUsuario rolUsuario;
    
    CallableStatement cstm;
    ResultSet result;
    //Constructor
    public Login() {
        //Crear objetos usuario y rol usuario
        
        this.usuario=new Usuario();
        this.rolUsuario=new RolUsuario();
    }
    public Login(int idLogin, String nombreLogin, String passwordLogin) {
        this.idLogin = idLogin;
        this.nombreLogin = nombreLogin;
        this.passwordLogin = passwordLogin;
        
         //Crear objetos usuario y rol usuario
        
        this.usuario=new Usuario();
        this.rolUsuario=new RolUsuario();
    }
    //Metodos Set y Get

    public int getIdLogin() {
        return idLogin;
    }

    public void setIdLogin(int idLogin) {
        this.idLogin = idLogin;
    }

    public String getNombreLogin() {
        return nombreLogin;
    }

    public void setNombreLogin(String nombreLogin) {
        this.nombreLogin = nombreLogin;
    }

    public String getPasswordLogin() {
        return passwordLogin;
    }

    public void setPasswordLogin(String passwordLogin) {
        this.passwordLogin = passwordLogin;
    }

    public Date getFechaCreacionLogin() {
        return fechaCreacionLogin;
    }

    public void setFechaCreacionLogin(Date fechaCreacionLogin) {
        this.fechaCreacionLogin = fechaCreacionLogin;
    }

    public boolean isEstatusLogin() {
        return estatusLogin;
    }

    public void setEstatusLogin(boolean estatusLogin) {
        this.estatusLogin = estatusLogin;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public RolUsuario getRolUsuario() {
        return rolUsuario;
    }

    public void setRolUsuario(RolUsuario rolUsuario) {
        this.rolUsuario = rolUsuario;
    }
    
    //Metodo to String

    @Override
    public String toString() {
        return "Login{" + "idLogin=" + idLogin + ", \n nombreLogin=" + nombreLogin + ", \n passwordLogin=" + passwordLogin + ", \n fechaCreacionLogin=" + fechaCreacionLogin + ", \n estatusLogin=" + estatusLogin + ", \n usuario=" + usuario.getNombreUsuario() + ", \n rolUsuario=" + rolUsuario.getTipoRolUsuario() + '}';
        
    }
    
    //Metodo para validar el inicio de sesion con DB
    public boolean validarLogin(){
        if (super.openConectionBD()) {
            try{
                //llamar al procedimiento almacenado
                this.cstm=(CallableStatement) super.getConexion().prepareCall("call bd_sistema_login.sp_validar_login(?, ?);");
                this.cstm.setString(1,this.getUsuario().getNombreUsuario());
                this.cstm.setString(2, this.getPasswordLogin());
                //ejecuta el procedimiento
                this.result=this.cstm.executeQuery();
                
                boolean existeUsuario=false;
                
                //Recorrer la consulta
                while (this.result.next()){
                    existeUsuario=true;
                    //agregar los datos de la consulta a los atributos del RolUsuario
                    this.getRolUsuario().setTipoRolUsuario(this.result.getString("tipoRolUsuario"));
                }
                //cerrar conexion
                this.cstm.close();
                super.getConexion().close();
                
                if(existeUsuario){
                    super.setMensajes("Si existe el usuario");
                    return true;
                }else{
                    super.setMensajes("No existe el usuario");
                    return false;
                }
            }catch (SQLException e){
                super.setMensajes("Error de SQL"+e.getMessage());  
            } 
        }else{
            JOptionPane.showMessageDialog(null,super.getMensajes());
        }
        return false;
    }
    
    /*Metodo para validar el inicio de sesion
    public boolean validarLogin(){
        //VAriables para el usuario,password y tipo usuario
        String nameUser="Jose";
        String passwordUser="1234";
        String tipeUser="admin";
        
        if ((nameUser.equals(this.usuario.getNombreUsuario())&&(passwordUser.equals(this.passwordLogin))&&(tipeUser.equals(this.rolUsuario.getTipoRolUsuario())))) 
        {
            return true;
        } else {
            return false;
        }*/
        
        
    }
        
    