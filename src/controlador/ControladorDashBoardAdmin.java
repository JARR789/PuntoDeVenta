/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import vista.VistaDashBoardAdmin;

/**
 *
 * @author J.A.R.R
 */
public class ControladorDashBoardAdmin {
    //Atributos 
    private VistaDashBoardAdmin vista;
    
    //Contructor

    public ControladorDashBoardAdmin() {
        //Crear objeto vista
        this.vista=new VistaDashBoardAdmin();
        
        //Metodo manejador de eventos
        manejadorEventos();
    }
    //Metodo set y get

    public VistaDashBoardAdmin getVista() {
        return vista;
    }

    public void setVista(VistaDashBoardAdmin vista) {
        this.vista = vista;
    }
    
    //Manejador eventos
    public void manejadorEventos(){
        this.vista.btnUsuarios.addActionListener(e->mostrarPanelUsuarios());
        this.vista.btnProductos.addActionListener(e->mostrarPanelRolUsuario());
    }
    //Metodo para mostrar el panel usuarios
    public void mostrarPanelUsuarios(){
        //Crear el objeto del controlador del panel usuarios
        ControladorPanelUsuario controladorPanelUsuario=new ControladorPanelUsuario();
        
        controladorPanelUsuario.getVista().setSize(800,560);
        controladorPanelUsuario.getVista().setLocation(0,0);
        
        //Agregar el panel usuario al panel controlador
        this.vista.panelContenedor.removeAll();
        this.vista.panelContenedor.add(controladorPanelUsuario.getVista());
        this.vista.panelContenedor.revalidate();
        this.vista.panelContenedor.repaint();
    }
    //Metodo para mostrar el panel rol usuario
    public void mostrarPanelRolUsuario(){
        ControladorPanelRolUsuario controladorPanelRolUsuario=new ControladorPanelRolUsuario();
        
        controladorPanelRolUsuario.getVista().setSize(800,560);
        controladorPanelRolUsuario.getVista().setLocation(0,0);
        
        //Agreggar el panel usuario al panel controlador 
        this.vista.panelContenedor.removeAll();
        this.vista.panelContenedor.add(controladorPanelRolUsuario.getVista());
        this.vista.panelContenedor.revalidate();
        this.vista.panelContenedor.repaint();
    
    }

    //Metodo Main
    
    public static void main(String[] args) {
        //Crear objeto controlador
        ControladorDashBoardAdmin controlador=new ControladorDashBoardAdmin();
        controlador.vista.setVisible(true);
        controlador.vista.setLocationRelativeTo(null);
    }
    
}
