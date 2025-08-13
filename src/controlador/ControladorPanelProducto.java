/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import modelo.Producto;
import vista.PanelProducto;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author 52558
 */
public class ControladorPanelProducto {
     //Atributos
    private PanelProducto vista;
    private Producto modelo;
    
    //Constructor

    public ControladorPanelProducto() {
        //Crear objetos modelo y vista
        this.vista=new PanelProducto();
        this.modelo=new Producto();
        //Llamar a eventos
        manejadorEventos();
        
        //LLamar al metodo llenar tabla usuarios
        LlenarTablaProductos();
        
    }
    
    //Metodos set y get vista

    public PanelProducto getVista() {
        return vista;
    }

    public void setVista(PanelProducto vista) {
        this.vista = vista;
    }

    public Producto getModelo() {
        return modelo;
    }

    public void setModelo(Producto modelo) {
        this.modelo = modelo;
    }
    
    
    //Metodo para manejar los eventos
    public void manejadorEventos(){
        
        //evento para el boton registrar
        this.vista.btnRegistro.addActionListener(e->registrar());
        //evento para el boton editar
        this.vista.btnEditarProducto.addActionListener(e->editar());
        //Evento para el boton de eliminar
        this.vista.btnEliminarProducto.addActionListener(e->eliminar());
        //Evento para el boton de buscar
        this.vista.btnBuscarProducto.addActionListener(e->buscarId());
        //Evento para el boton nuevo
        this.vista.btnNuevoProducto.addActionListener(e->nuevo());
        //Evento para boton salir
        this.vista.btnSaliirProducto.addActionListener(e->salir());
    
    }
    //Metodo para registrar
    public void registrar(){
        //JOptionPane.showMessageDialog(this.vista, "Registrar Usuario");
        
        //Validar cajas de texto
        if(validarCajasTexto()){
            
              
        //Obtener los datos de la vista y agregarlos al modelo
        this.modelo.setCodigo(this.vista.PanelCodigo.getText());
        this.modelo.setNombre(this.vista.panelNombreProducto.getText());
        this.modelo.setExistencia(Integer.parseInt(this.vista.panelExistencia.getText()));
        this.modelo.setPrecio(this.vista.panelPrecio.getText());
        this.modelo.setDescripcion(this.vista.panelDescripcion.getText());
        
        if (this.modelo.insertar()) {
            JOptionPane.showMessageDialog(this.vista, "Los datos del producto se guardaron correctamente");
            //Limpiar cajas de texto
            limpiarCajasTexto();
                    
            //LLamar al metodo llenar tabla usuarios
             LlenarTablaProductos();
        
        } else {
             JOptionPane.showMessageDialog(this.vista, "Los datos del producto NO se guardaron...");
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
        this.modelo.setIdProducto(Integer.parseInt(this.vista.panelIdProducto.getText()));
        this.modelo.setCodigo(this.vista.PanelCodigo.getText());
        this.modelo.setNombre(this.vista.panelNombreProducto.getText());
        this.modelo.setExistencia(Integer.parseInt(this.vista.panelExistencia.getText()));
        this.modelo.setPrecio(this.vista.panelPrecio.getText());
        this.modelo.setDescripcion(this.vista.panelDescripcion.getText());
        
        if (this.modelo.modificar(this.modelo.getIdProducto())) {
            JOptionPane.showMessageDialog(this.vista, "Los datos del producto se modificaron correctamente");
            //Limpiar cajas de texto
            limpiarCajasTexto();
        } else {
             JOptionPane.showMessageDialog(this.vista, "Los datos del producto NO se modificaron...");
        }
                
        //LLamar al metodo llenar tabla usuarios
        LlenarTablaProductos();
        
        }else{
            JOptionPane.showMessageDialog(this.vista, "Faltan capturar algunos datos");
        }
        
        
    }//Fin del metodo editar
    
    //Metodo para eliminar usuario
     public void eliminar(){
          //Validar cajas de texto
        if(!this.vista.panelIdProducto.getText().trim().isEmpty()){
            
              
        //Obtener los datos de la vista y agregarlos al modelo
        this.modelo.setIdProducto(Integer.parseInt(this.vista.panelIdProducto.getText()));

        if (this.modelo.eliminar(this.modelo.getIdProducto())) {
            JOptionPane.showMessageDialog(this.vista, "Los datos del producto se eliminaron correctamente");
            //Limpiar cajas de texto
            limpiarCajasTexto();
              //LLamar al metodo llenar tabla usuarios
               LlenarTablaProductos();
        
        } else {
             JOptionPane.showMessageDialog(this.vista, "Los datos del producto NO se eliminaron...");
        }
        
    }else{
        JOptionPane.showMessageDialog(this.vista, "SE DEBE DE CAPTURAR EL ID");
        this.vista.panelIdProducto.requestFocus();
                
        //LLamar al metodo llenar tabla usuarios
        LlenarTablaProductos();
        
    }
        
        
    }//Fin del metodo eliminar
     
     //Metodo para buscar por id
     public void buscarId(){
         //Obtener los datos de la vista y agregarlos al modelo
         this.modelo.setIdProducto(Integer.parseInt(this.vista.panelIdProducto.getText()));
         
         if (this.modelo.BuscarPorId(this.modelo.getIdProducto())) {
             //Agregar los datos a las cajas de texto
             this.vista.PanelCodigo.setText(this.modelo.getCodigo());
             this.vista.panelNombreProducto.setText(this.modelo.getNombre());
             this.vista.panelExistencia.setText(Integer.toString(this.modelo.getExistencia()));
             this.vista.panelPrecio.setText(this.modelo.getPrecio());
             this.vista.panelDescripcion.setText(this.modelo.getDescripcion());
         } else {
             JOptionPane.showMessageDialog(this.vista, "No se encontraron los datos del Producto");
                     
        //LLamar al metodo llenar tabla usuarios
        LlenarTablaProductos();
        
         }
         
     }
    //Metodo para llenar la tabla de usuarios
     public void LlenarTablaProductos(){
         this.vista.tablaProductos.setModel(obtenerDatosProductos());
     }
     //Metodo para obtener los datos del usuario
     public DefaultTableModel obtenerDatosProductos(){
         String encabezadoTabla[]={"Id", "Codigo", "Nombre", "Existencia", "Precio", "Descripcion"};
         DefaultTableModel modeloTabla = new DefaultTableModel(encabezadoTabla, 0);
         
         Object[] fila = new Object[modeloTabla.getColumnCount()];
         //Agregar los datos del objeto usuario del arraylist l modelo tabla
         for (Producto producto : this.modelo.buscar()){
             fila[0] = producto.getIdProducto();
             fila[1] = producto.getCodigo();
             fila[2] = producto.getNombre();
             fila[3] = producto.getExistencia();
             fila[4] = producto.getPrecio();
             fila[5] = producto.getDescripcion();
             
             modeloTabla.addRow(fila);
         }
         
         
         return modeloTabla;
     }
    //Metodo para validar las cajas de texto
    public boolean validarCajasTexto(){
        try {
            if (this.vista.PanelCodigo.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this.vista, "Debes de ingresar el codigo del producto");
                this.vista.PanelCodigo.requestFocus();
                return false;
            }if(this.vista.panelNombreProducto.getText().trim().isEmpty()){
                JOptionPane.showMessageDialog(this.vista, "Debes de ingresar el nombre del producto ");
                this.vista.panelNombreProducto.requestFocus();
                return false;
            }if(this.vista.panelExistencia.getText().trim().isEmpty()){
                JOptionPane.showMessageDialog(this.vista, "Debes de ingresar la existencia del producto");
                this.vista.panelExistencia.requestFocus();
                return false;
            }if(this.vista.panelPrecio.getText().trim().isEmpty()){
                JOptionPane.showMessageDialog(this.vista, "Debes de ingresar el precio del producto");
                this.vista.panelPrecio.requestFocus();
                return false;
            }if(this.vista.panelDescripcion.getText().trim().isEmpty()){
                JOptionPane.showMessageDialog(this.vista, "Debes de ingresar la descripciion del producto");
                this.vista.panelDescripcion.requestFocus();
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
        this.vista.panelIdProducto.setText("");
        this.vista.PanelCodigo.setText("");
        this.vista.panelNombreProducto.setText("");
        this.vista.panelExistencia.setText("");
        this.vista.panelPrecio.setText("");
        this.vista.panelDescripcion.setText("");
    }
    //Metodo para nuevo
    public void nuevo(){
        //LLamar el metodo limpiar cajas de texto
        limpiarCajasTexto();
        this.vista.panelIdProducto.requestFocus();
        
        
        
    }
    //Metoddo para salir
    public void salir(){
        //Ocultar el panel usuario
        this.vista.setVisible(false);
        
    }
    
}//fin de la clase controlador panel usuario


    

