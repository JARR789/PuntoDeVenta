/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import javax.swing.JOptionPane;
import modelo.Login;
import vista.VistaDashBoardAdmin;
import vista.Vistalogin;
import vista.panelDashBoardPuntoVenta;

/**
 *
 * @author J.A.R.R
 */
public class ControladorVistaLogin {
    //Atributos
    
    private Vistalogin vista;
    private Login modelo;
    
    //Constructor

    public ControladorVistaLogin() {
        //Crear objeto vista modelo
        this.vista=new Vistalogin();
        this.modelo=new Login();
        
        //Llamar el metodo manejadorEventos
        manejadorEventos();
       
    }
    //Metodo para el manejador de eventos
    public void manejadorEventos(){
        this.vista.btnIniciar.addActionListener(e->iniciarSesion());
        this.vista.btnCancelar.addActionListener(e->cancelar());
    }
    
    // Metodo para iniciar sesion
public void iniciarSesion() {
    String user = this.vista.txtUsuario.getText();
    String password = String.valueOf(this.vista.txtPassword.getPassword());

    // Validar campos vacíos
    if (user.isEmpty() || password.isEmpty()) {
        JOptionPane.showMessageDialog(this.vista, "Por favor, ingrese usuario y contraseña");
        return;
    }

    // Pasar datos al modelo
    this.modelo.getUsuario().setNombreUsuario(user);
    this.modelo.setPasswordLogin(password);

    // Validar login contra BD
    if (this.modelo.validarLogin()) {
        String rol = this.modelo.getRolUsuario().getTipoRolUsuario();

        // Abrir ventana según el rol
        if ("cliente".equalsIgnoreCase(rol)) {
            panelDashBoardPuntoVenta vistaCliente = new panelDashBoardPuntoVenta();
            vistaCliente.setVisible(true);
        } else if ("admin".equalsIgnoreCase(rol) || "administrador".equalsIgnoreCase(rol)) {
            ControladorDashBoardAdmin vistaDashBoardAdmin = new ControladorDashBoardAdmin();
            vistaDashBoardAdmin.getVista().setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this.vista, "Rol no reconocido: " + rol);
            return;
        }

        // Cerrar vista login
        this.vista.dispose();
    } else {
        JOptionPane.showMessageDialog(this.vista, "Usuario y/o Password incorrecto");
    }
}

    
    //Metodo para cancelar
    public void cancelar(){
        
    }
    
    //metodo principal main
    
    public static void main(String[] args) {
        ControladorVistaLogin controlador=new ControladorVistaLogin();
        controlador.vista.setVisible(true);
       
    }
    
    
}

