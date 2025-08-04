/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import javax.swing.JOptionPane;
import modelo.RolUsuario;
import vista.PanelRolUsuario;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author J.A.R.R
 */
public class ControladorPanelRolUsuario {
    
    //Atributos
    private PanelRolUsuario vista;
    private RolUsuario modelo;
    
    //Constructor
    
    public ControladorPanelRolUsuario() {
        //Crear objetos modelo y vista
        this.vista=new PanelRolUsuario();
        this.modelo=new RolUsuario();
        //Llamar a eventos
        manejadordeEventos();
        
        //Llamar al metodo llenar tabla rol usuarios
        LlenarTablaUsuarios();
    
}
    //Metodos set y get

    public PanelRolUsuario getVista() {
        return vista;
    }

    public void setVista(PanelRolUsuario vista) {
        this.vista = vista;
    }

    public RolUsuario getModelo() {
        return modelo;
    }

    public void setModelo(RolUsuario modelo) {
        this.modelo = modelo;
    }
    
    //Metodo para manejar eventos
    public void manejadordeEventos(){
        
        //Evento para el boton registrar
        this.vista.btnRegistroRol.addActionListener(e->registrarRol());
        //evento para el boton editar
        this.vista.btnEditarRol.addActionListener(e->editarRol());
        //Evento para el boton de eliminar
        this.vista.btnEliminarRol.addActionListener(e->eliminarRol());
        //Evento para el boton de buscar
        this.vista.btnBuscarRol.addActionListener(e->buscarIdRol());
        //Evento para el boton nuevo
        this.vista.btnNuevoRol.addActionListener(e->nuevoRol());
        //Evento para boton salir
        this.vista.btnSalirRol.addActionListener(e->salirRol());
        
    }
    //Metodo para registrar rol
    public void registrarRol(){
        //JOptionPane.showMessageDialog(this.vista, "Registrar Rol usuario");
        
        //Validar cajas de texto
         if(validarCajasTexto()){
            
              
        //Obtener los datos de la vista y agregarlos al modelo
        this.modelo.setIdRolUsuario(Integer.parseInt(this.vista.paneIdRolUsuario.getText()));
        this.modelo.setTipoRolUsuario(this.vista.paneTipoRolUsuario.getText());
        this.modelo.setDescripcionRoLUsuario(this.vista.paneDesRolUsuario.getText());
    
        if (this.modelo.insertar()) {
            JOptionPane.showMessageDialog(this.vista, "Los datos del rol de usuario se guardaron correctamente");
            //Limpiar cajas de texto
            limpiarCajasTexto();
                    
            //LLamar al metodo llenar tabla usuarios
             LlenarTablaUsuarios();
        
        } else {
             JOptionPane.showMessageDialog(this.vista, "Los datos del rol usuario NO se guardaron...");
        }
        
        }else{
            JOptionPane.showMessageDialog(this.vista, "Faltan capturar algunos datos");
        }
    }//Fin del metodo registrar
    
    //Metodo para editar rolusuario
    public void editarRol(){
    //Validar cajas de texto
        if(validarCajasTexto()){
            
              
        //Obtener los datos de la vista y agregarlos al modelo
        this.modelo.setIdRolUsuario(Integer.parseInt(this.vista.paneIdRolUsuario.getText()));
        this.modelo.setTipoRolUsuario(this.vista.paneTipoRolUsuario.getText());
        this.modelo.setDescripcionRoLUsuario(this.vista.paneDesRolUsuario.getText());
        
        
        if (this.modelo.modificar(this.modelo.getIdRolUsuario())) {
            JOptionPane.showMessageDialog(this.vista, "Los datos del Rol usuario se modificaron correctamente");
            //Limpiar cajas de texto
            limpiarCajasTexto();
        } else {
             JOptionPane.showMessageDialog(this.vista, "Los datos del usuario NO se modificaron...");
        }
                
        //LLamar al metodo llenar tabla usuarios
        LlenarTablaUsuarios();
        
        }else{
            JOptionPane.showMessageDialog(this.vista, "Faltan capturar algunos datos");
        }
        
        
    }//Fin del metodo editar rol
    
    //Metodo para eliminar rol usuario
    public void eliminarRol(){
        
    if(!this.vista.paneIdRolUsuario.getText().trim().isEmpty()){
            
              
        //Obtener los datos de la vista y agregarlos al modelo
        this.modelo.setIdRolUsuario(Integer.parseInt(this.vista.paneIdRolUsuario.getText()));

        if (this.modelo.eliminar(this.modelo.getIdRolUsuario())) {
            JOptionPane.showMessageDialog(this.vista, "Los datos del Rol usuario se eliminaron correctamente");
            //Limpiar cajas de texto
            limpiarCajasTexto();
              //LLamar al metodo llenar tabla usuarios
               LlenarTablaUsuarios();
        
        } else {
             JOptionPane.showMessageDialog(this.vista, "Los datos del Rol usuario NO se eliminaron...");
        }
        
    }else{
        JOptionPane.showMessageDialog(this.vista, "SE DEBE DE CAPTURAR EL ID");
        this.vista.paneIdRolUsuario.requestFocus();
                
        //LLamar al metodo llenar tabla usuarios
        LlenarTablaUsuarios();
        
    }
        
        
    }//Fin del metodo eliminar rol
    
    public void buscarIdRol(){
         //Obtener los datos de la vista y agregarlos al modelo
         this.modelo.setIdRolUsuario(Integer.parseInt(this.vista.paneIdRolUsuario.getText()));
         
         if (this.modelo.BuscarPorId(this.modelo.getIdRolUsuario())) {
             //Agregar los datos a las cajas de texto
             this.vista.paneTipoRolUsuario.setText(this.modelo.getTipoRolUsuario());
             this.vista.paneDesRolUsuario.setText(this.modelo.getDescripcionRoLUsuario());
        
         } else {
             JOptionPane.showMessageDialog(this.vista, "No se encontraron los datos del Rol usuario");
                     
        //LLamar al metodo llenar tabla usuarios
        LlenarTablaUsuarios();
        
         }
         
     }
    
        
        
        
    
    
    
    
    //Metodo para llenar la tabla de usuarios
     public void LlenarTablaUsuarios(){
         this.vista.tablarolusuario.setModel(obtenerDatosUsuarios());
     }
     //Metodo para obtener los datos del usuario
     public DefaultTableModel obtenerDatosUsuarios(){
         String encabezadoTabla[]={"Id", "Tipo de Rol", "Descripcion"};
         DefaultTableModel modeloTabla = new DefaultTableModel(encabezadoTabla, 0);
         
         Object[] fila = new Object[modeloTabla.getColumnCount()];
         //Agregar los datos del objeto usuario del arraylist l modelo tabla
         for (RolUsuario rolusuario : this.modelo.buscar()){
             fila[0] = rolusuario.getIdRolUsuario();
             fila[1] = rolusuario.getTipoRolUsuario();
             fila[2] = rolusuario.getDescripcionRoLUsuario();
         
             modeloTabla.addRow(fila);
         }
         
         
         return modeloTabla;
     }
    //Metodo para validar las cajas de texto
    public boolean validarCajasTexto(){
        try {
            if (this.vista.paneTipoRolUsuario.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this.vista, "Debes de ingresar el tipo de rol de usuario");
                this.vista.paneTipoRolUsuario.requestFocus();
                return false;
            }if(this.vista.paneDesRolUsuario.getText().trim().isEmpty()){
                JOptionPane.showMessageDialog(this.vista, "Debes de ingresar la descripcion del rol usuario");
                this.vista.paneDesRolUsuario.requestFocus();
               
                
            }
            //Si los campos estan llenos
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this.vista, "Error: " + e.getMessage());
            return false;
        }
        
    }//Fin del metodo validar cajas de teexto
    //Metodo para limpiar cajas de texto
    public void limpiarCajasTexto(){
        this.vista.paneIdRolUsuario.setText("");
        this.vista.paneTipoRolUsuario.setText("");
        this.vista.paneDesRolUsuario.setText("");
    
    }
    //Metodo para nuevo
    public void nuevoRol(){
        //Llamar el metodo limpiar cajas de texto
        limpiarCajasTexto();
        this.vista.paneIdRolUsuario.requestFocus();
        
    }
    //Metodo para salir
    public void salirRol(){
        //Ocultar el panel usuario
        this.vista.setVisible(false);
    }
        
    }//Fin controladot

