/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.Usuario;
import vista.PanelUsuarios;

/**
 *
 * @author J.A.R.R
 */
public class ControladorPanelUsuario {
    //Atributos
    private PanelUsuarios vista;
    private Usuario modelo;
    
    //Constructor

    public ControladorPanelUsuario() {
        //Crear objetos modelo y vista
        this.vista=new PanelUsuarios();
        this.modelo=new Usuario();
        //Llamar a eventos
        manejadorEventos();
        
        //LLamar al metodo llenar tabla usuarios
        LlenarTablaUsuarios();
        
    }
    
    //Metodos set y get vista

    public PanelUsuarios getVista() {
        return vista;
    }

    public void setVista(PanelUsuarios vista) {
        this.vista = vista;
    }

    public Usuario getModelo() {
        return modelo;
    }

    public void setModelo(Usuario modelo) {
        this.modelo = modelo;
    }
    
    
    //Metodo para manejar los eventos
    public void manejadorEventos(){
        
        //evento para el boton registrar
        this.vista.btnRegistro.addActionListener(e->registrar());
        //evento para el boton editar
        this.vista.btnEditar.addActionListener(e->editar());
        //Evento para el boton de eliminar
        this.vista.btnEliminar.addActionListener(e->eliminar());
        //Evento para el boton de buscar
        this.vista.btnBuscar.addActionListener(e->buscarId());
        //Evento para el boton nuevo
        this.vista.btnNuevo.addActionListener(e->nuevo());
        //Evento para boton salir
        this.vista.btnSaliir.addActionListener(e->salir());
    
    }
    //Metodo para registrar
    public void registrar(){
        //JOptionPane.showMessageDialog(this.vista, "Registrar Usuario");
        
        //Validar cajas de texto
        if(validarCajasTexto()){
            
              
        //Obtener los datos de la vista y agregarlos al modelo
        this.modelo.setNombreUsuario(this.vista.paneNombre.getText());
        this.modelo.setApPaternoUsuario(this.vista.paneApellidoPaterno.getText());
        this.modelo.setApMaternoUsuario(this.vista.paneApellidoMaterno.getText());
        this.modelo.setEmailUsuario(this.vista.paneEmail.getText());
        this.modelo.setTelefonoCelular(this.vista.paneTelefono.getText());
        
        if (this.modelo.insertar()) {
            JOptionPane.showMessageDialog(this.vista, "Los datos del usuario se guardaron correctamente");
            //Limpiar cajas de texto
            limpiarCajasTexto();
                    
            //LLamar al metodo llenar tabla usuarios
             LlenarTablaUsuarios();
        
        } else {
             JOptionPane.showMessageDialog(this.vista, "Los datos del usuario NO se guardaron...");
        }
        
        }else{
            JOptionPane.showMessageDialog(this.vista, "Faltan capturar algunos datos");
        }
    }//Fin del metodo registrar
    
    //Mettodo para editar usuario
    public void editar(){
          //Validar cajas de texto
        if(validarCajasTexto()){
            
           
        //Obtener los datos de la vista y agregarlos al modelo
        this.modelo.setIdUsuario(Integer.parseInt(this.vista.paneIdusuario.getText()));
        this.modelo.setNombreUsuario(this.vista.paneNombre.getText());
        this.modelo.setApPaternoUsuario(this.vista.paneApellidoPaterno.getText());
        this.modelo.setApMaternoUsuario(this.vista.paneApellidoMaterno.getText());
        this.modelo.setEmailUsuario(this.vista.paneEmail.getText());
        this.modelo.setTelefonoCelular(this.vista.paneTelefono.getText());
        
        if (this.modelo.modificar(this.modelo.getIdUsuario())) {
            JOptionPane.showMessageDialog(this.vista, "Los datos del usuario se modificaron correctamente");
            //Limpiar cajas de texto
            limpiarCajasTexto();
        } else {
             JOptionPane.showMessageDialog(this.vista, "Los datos del Rol usuario NO se modificaron...");
        }
                
        //LLamar al metodo llenar tabla usuarios
        LlenarTablaUsuarios();
        
        }else{
            JOptionPane.showMessageDialog(this.vista, "Faltan capturar algunos datos");
        }
        
        
    }//Fin del metodo editar
    
    //Metodo para eliminar usuario
     public void eliminar(){
          //Validar cajas de texto
        if(!this.vista.paneIdusuario.getText().trim().isEmpty()){
            
              
        //Obtener los datos de la vista y agregarlos al modelo
        this.modelo.setIdUsuario(Integer.parseInt(this.vista.paneIdusuario.getText()));

        if (this.modelo.eliminar(this.modelo.getIdUsuario())) {
            JOptionPane.showMessageDialog(this.vista, "Los datos del usuario se eliminaron correctamente");
            //Limpiar cajas de texto
            limpiarCajasTexto();
              //LLamar al metodo llenar tabla usuarios
               LlenarTablaUsuarios();
        
        } else {
             JOptionPane.showMessageDialog(this.vista, "Los datos del usuario NO se eliminaron...");
        }
        
    }else{
        JOptionPane.showMessageDialog(this.vista, "SE DEBE DE CAPTURAR EL ID");
        this.vista.paneIdusuario.requestFocus();
                
        //LLamar al metodo llenar tabla usuarios
        LlenarTablaUsuarios();
        
    }
        
        
    }//Fin del metodo eliminar
     
     //Metodo para buscar por id
     public void buscarId(){
         //Obtener los datos de la vista y agregarlos al modelo
         this.modelo.setIdUsuario(Integer.parseInt(this.vista.paneIdusuario.getText()));
         
         if (this.modelo.BuscarPorId(this.modelo.getIdUsuario())) {
             //Agregar los datos a las cajas de texto
             this.vista.paneNombre.setText(this.modelo.getNombreUsuario());
             this.vista.paneApellidoPaterno.setText(this.modelo.getApPaternoUsuario());
             this.vista.paneApellidoMaterno.setText(this.modelo.getApMaternoUsuario());
             this.vista.paneEmail.setText(this.modelo.getEmailUsuario());
             this.vista.paneTelefono.setText(this.modelo.getTelefonoCelular());
         } else {
             JOptionPane.showMessageDialog(this.vista, "No se encontraron los datos del usuario");
                     
        //LLamar al metodo llenar tabla usuarios
        LlenarTablaUsuarios();
        
         }
         
     }
    //Metodo para llenar la tabla de usuarios
     public void LlenarTablaUsuarios(){
         this.vista.tablausuario.setModel(obtenerDatosUsuarios());
     }
     //Metodo para obtener los datos del usuario
     public DefaultTableModel obtenerDatosUsuarios(){
         String encabezadoTabla[]={"Id", "Nombre", "Apellido Paterno", "Apellido Materno", "Email", "Telefono"};
         DefaultTableModel modeloTabla = new DefaultTableModel(encabezadoTabla, 0);
         
         Object[] fila = new Object[modeloTabla.getColumnCount()];
         //Agregar los datos del objeto usuario del arraylist l modelo tabla
         for (Usuario usuario : this.modelo.buscar()){
             fila[0] = usuario.getIdUsuario();
             fila[1] = usuario.getNombreUsuario();
             fila[2] = usuario.getApPaternoUsuario();
             fila[3] = usuario.getApMaternoUsuario();
             fila[4] = usuario.getEmailUsuario();
             fila[5] = usuario.getTelefonoCelular();
             
             modeloTabla.addRow(fila);
         }
         
         
         return modeloTabla;
     }
    //Metodo para validar las cajas de texto
    public boolean validarCajasTexto(){
        try {
            if (this.vista.paneNombre.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this.vista, "Debes de ingresar el nombre de usuario");
                this.vista.paneNombre.requestFocus();
                return false;
            }if(this.vista.paneApellidoPaterno.getText().trim().isEmpty()){
                JOptionPane.showMessageDialog(this.vista, "Debes de ingresar el apellido paterno del usuario");
                this.vista.paneApellidoPaterno.requestFocus();
                return false;
            }if(this.vista.paneApellidoMaterno.getText().trim().isEmpty()){
                JOptionPane.showMessageDialog(this.vista, "Debes de ingresar el apellidomaterno del usuario");
                this.vista.paneApellidoMaterno.requestFocus();
                return false;
            }if(this.vista.paneEmail.getText().trim().isEmpty()){
                JOptionPane.showMessageDialog(this.vista, "Debes de ingresar el email del usuario");
                this.vista.paneEmail.requestFocus();
                return false;
            }if(this.vista.paneTelefono.getText().trim().isEmpty()){
                JOptionPane.showMessageDialog(this.vista, "Debes de ingresar el telefono celular del usuario");
                this.vista.paneTelefono.requestFocus();
                return false;
                
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
        this.vista.paneIdusuario.setText("");
        this.vista.paneNombre.setText("");
        this.vista.paneApellidoPaterno.setText("");
        this.vista.paneApellidoMaterno.setText("");
        this.vista.paneEmail.setText("");
        this.vista.paneTelefono.setText("");
    }
    //Metodo para nuevo
    public void nuevo(){
        //LLamar el metodo limpiar cajas de texto
        limpiarCajasTexto();
        this.vista.paneIdusuario.requestFocus();
        
        
        
    }
    //Metoddo para salir
    public void salir(){
        //Ocultar el panel usuario
        this.vista.setVisible(false);
        
    }
    
}//fin de la clase controlador panel usuario

