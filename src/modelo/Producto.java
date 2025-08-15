/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author 52558
 */
public class Producto extends ConexionBD implements CRUDInterface {
    //Atributos
    private int idProducto;
    private String Nombre;
    private String Codigo;
    private String Descripcion;
    private int Existencia;
    private String Precio;
    
    private CallableStatement cstmt;
    private ResultSet result;
    
    //Constructor

    public Producto() {
    }

    public Producto(int idProducto, String Nombre, String Codigo, int Existencia, String Precio) {
        this.idProducto = idProducto;
        this.Nombre = Nombre;
        this.Codigo = Codigo;
        this.Existencia = Existencia;
        this.Precio = Precio;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getCodigo() {
        return Codigo;
    }

    public void setCodigo(String Codigo) {
        this.Codigo = Codigo;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public int getExistencia() {
        return Existencia;
    }

    public void setExistencia(int Existencia) {
        this.Existencia = Existencia;
    }

    public String getPrecio() {
        return Precio;
    }

    public void setPrecio(String Precio) {
        this.Precio = Precio;
    }
    
    //Metodo toString

    @Override
    public String toString() {
        return "Producto{" + "idProducto=" + idProducto + ", Nombre=" + Nombre + ", Codigo=" + Codigo + ", Descripcion=" + Descripcion + ", Existencia=" + Existencia + ", Precio=" + Precio + ", cstmt=" + cstmt + ", result=" + result + '}';
    }
    

    @Override
    public boolean insertar() {
        if (super.openConectionBD()) {
            try {
              
                //Llamar al procedimiento almacenado
                this.cstmt=super.getConexion().prepareCall("call bd_sistema_login.sp_insertar_producto(?,?,?,?,?);");
                this.cstmt.setString(1, this.Codigo);
                this.cstmt.setString(2, this.Nombre);
                this.cstmt.setInt(3, this.Existencia);
                this.cstmt.setString(4, this.Precio);
                this.cstmt.setString(5, this.Descripcion);
                //Ejecutar el procedimiento almacenado
                this.cstmt.execute();
                //Cerrar conexion a la BD
                this.cstmt.close();
                super.getConexion().close();
                
             
                
                
                return true;
            } catch (SQLException ex) {
               super.setMensajes("Error sql: " + ex.getMessage());
            }
        } else {
        }  JOptionPane.showMessageDialog(null, super.getMensajes());
        return false;
    }//Fin del metodo  insertar
    
    @Override
    public ArrayList<Producto> buscar() {
       ArrayList<Producto> listaProductos = new ArrayList<Producto>();
        if (super.openConectionBD()) {
            try {
                //JOptionPane.showMessageDialog(null, super.getMensajes());
                //Llamar al procedimiento almacenado
                this.cstmt=super.getConexion().prepareCall("call bd_sistema_login.sp_buscar_productos();");
              
                //  EJECUTAR CONSULTA
                this.result=cstmt.executeQuery();

                while (this.result.next()) {
                
                Producto producto = new Producto();
                producto.idProducto = this.result.getInt(1);
                producto.Codigo = this.result.getString(2);
                producto.Nombre = this.result.getString(3);
                producto.Existencia = this.result.getInt(4);
                producto.Precio = this.result.getString(5);
                producto.Descripcion = this.result.getString(6);
                
                //Agregar el objeto usuario a la lista
                
                listaProductos.add(producto);


                    
                }
   
        
                //Cerrar conexion a la BD
                this.cstmt.close();
                super.getConexion().close();
                
             
                
          
            } catch (SQLException e) {
               JOptionPane.showMessageDialog(null, e.getMessage());
            }
        } else {
           JOptionPane.showMessageDialog(null, super.getMensajes());
        }  
        return listaProductos;
      
    }
    
    @Override
    public boolean BuscarPorId(int id) {
        this.idProducto = id;
         if (super.openConectionBD()) {
            try {
                //JOptionPane.showMessageDialog(null, super.getMensajes());
                //Llamar al procedimiento almacenado
                this.cstmt=super.getConexion().prepareCall("call bd_sistema_login.sp_buscar_idproducto(?);");
                this.cstmt.setInt(1, this.idProducto);
                this.result=cstmt.executeQuery();

                while (this.result.next()) {
                this.idProducto = this.result.getInt(1);
                this.Codigo = this.result.getString(2);
                this.Nombre = this.result.getString(3);
                this.Existencia = this.result.getInt(4);
                this.Precio = this.result.getString(5);
                this.Descripcion = this.result.getString(6);


                    
                }
   
        
                //Cerrar conexion a la BD
                this.cstmt.close();
                super.getConexion().close();
                
             
                
                
                return true;
            } catch (SQLException e) {
               JOptionPane.showMessageDialog(null, e.getMessage());
            }
        } else {
           JOptionPane.showMessageDialog(null, super.getMensajes());
        }  
        return false;
    }//Fin del metodo  buscar por id


    @Override
    public boolean modificar(int id) {
          this.idProducto=id;
        if (super.openConectionBD()) {
            try {
                //JOptionPane.showMessageDialog(null, super.getMensajes());
                //Llamar al procedimiento almacenado
                this.cstmt=super.getConexion().prepareCall("call bd_sistema_login.sp_editar_producto(?, ?, ?, ?, ?, ?);");
                this.cstmt.setInt(1, this.idProducto);
                this.cstmt.setString(2, this.Codigo);
                this.cstmt.setString(3, this.Nombre);
                this.cstmt.setInt(4, this.Existencia);
                this.cstmt.setString(5, this.Precio);
                this.cstmt.setString(6, this.Descripcion);
                //Ejecutar el procedimiento almacenado
                this.cstmt.execute();
                //Cerrar conexion a la BD
                this.cstmt.close();
                super.getConexion().close();
                
             
                
                
                return true;
            } catch (SQLException ex) {
               super.setMensajes("Error sql: " + ex.getMessage());
            }
        } else {
        }  JOptionPane.showMessageDialog(null, super.getMensajes());
        return false;
    }


    @Override
    public boolean eliminar(int id) {
  this.idProducto=id;
        if (super.openConectionBD()) {
            try {
                //JOptionPane.showMessageDialog(null, super.getMensajes());
                //Llamar al procedimiento almacenado
                this.cstmt=super.getConexion().prepareCall("call bd_sistema_login.sp_eliminar_producto(?);");
                this.cstmt.setInt(1, this.idProducto);
                //Ejecutar el procedimiento almacenado
                this.cstmt.execute();
                //Cerrar conexion a la BD
                this.cstmt.close();
                super.getConexion().close();
                
             
                
                
                return true;
            } catch (SQLException ex) {
               super.setMensajes("Error sql: " + ex.getMessage());
            }
        } else {
        }  JOptionPane.showMessageDialog(null, super.getMensajes());
        return false;
      
    }

}