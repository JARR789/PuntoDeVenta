/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.Date;

/**
 *
 * @author J.A.R.R
 */
public class Login {
    //Atruibutos
    private int idLogin;
    private String nombreLogin;
    private String passwordLogin;
    private Date fechaCreacionLogin;
    private boolean estatusLogin;
    
    //declaracion  de los objetos RolUsuario y Usuario
    private Usuario usuario;
    private RolUsuario rolUsuario;
    
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
    
    //Metodo para validar el inicio de sesion
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
        }
        
        
    }
        
    
    
}