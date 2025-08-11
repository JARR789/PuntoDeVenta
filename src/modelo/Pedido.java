/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.ResultSet;
/**
 *
 * @author aurit
 */
public class Pedido extends ConexionBD implements CRUDInterface  {
    //Atributos
    private int idProducto;
    private String CodigoProducto;
    private String  NombreProducto;
    private String ExistenciaProducto;
    private String PrecioProducto;
    private String DescripcionProducto;
      private CallableStatement cstmt;
    private ResultSet result;

    public Pedido() {
    }

    public Pedido(int idProducto, String CodigoProducto, String NombreProducto, String ExistenciaProducto, String PrecioProducto, String DescripcionProducto) {
        this.idProducto = idProducto;
        this.CodigoProducto = CodigoProducto;
        this.NombreProducto = NombreProducto;
        this.ExistenciaProducto = ExistenciaProducto;
        this.PrecioProducto = PrecioProducto;
        this.DescripcionProducto = DescripcionProducto;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getCodigoProducto() {
        return CodigoProducto;
    }

    public void setCodigoProducto(String CodigoProducto) {
        this.CodigoProducto = CodigoProducto;
    }

    public String getNombreProducto() {
        return NombreProducto;
    }

    public void setNombreProducto(String NombreProducto) {
        this.NombreProducto = NombreProducto;
    }

    public String getExistenciaProducto() {
        return ExistenciaProducto;
    }

    public void setExistenciaProducto(String ExistenciaProducto) {
        this.ExistenciaProducto = ExistenciaProducto;
    }

    public String getPrecioProducto() {
        return PrecioProducto;
    }

    public void setPrecioProducto(String PrecioProducto) {
        this.PrecioProducto = PrecioProducto;
    }

    public String getDescripcionProducto() {
        return DescripcionProducto;
    }

    public void setDescripcionProducto(String DescripcionProducto) {
        this.DescripcionProducto = DescripcionProducto;
    }

    @Override
    public String toString() {
        return "Pedido{" + "idProducto=" + idProducto + ", CodigoProducto=" + CodigoProducto + ", NombreProducto=" + NombreProducto + ", ExistenciaProducto=" + ExistenciaProducto + ", PrecioProducto=" + PrecioProducto + ", DescripcionProducto=" + DescripcionProducto + '}';
    }
    
    

    @Override
    public boolean insertar() {
         if (super.openConectionBD()) {
            try {
              
                //Llamar al procedimiento almacenado
                this.cstmt=super.getConexion().prepareCall("");
                this.cstmt.setString(1, this.CodigoProducto);
                this.cstmt.setString(2, this.DescripcionProducto);
                this.cstmt.setString(3, this.ExistenciaProducto);
                this.cstmt.setString(4, this.NombreProducto);
                this.cstmt.setString(5, this.PrecioProducto);
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
      
     
    }//fin del metodo insertar

    @Override
    public ArrayList<Pedido> buscar() {
        ArrayList<Pedido> listaPedido = new ArrayList<Pedido>();
        if (super.openConectionBD()) {
            try {
                //JOptionPane.showMessageDialog(null, super.getMensajes());
                //Llamar al procedimiento almacenado
                this.cstmt=super.getConexion().prepareCall("");
              
                //  EJECUTAR CONSULTA
                this.result=cstmt.executeQuery();

                while (this.result.next()) {
                
                Pedido pedido = new Pedido();
                pedido.idProducto = this.result.getInt(1);
                pedido.CodigoProducto = this.result.getString(2);
                pedido.DescripcionProducto = this.result.getString(3);
                pedido.ExistenciaProducto = this.result.getString(4);
                pedido.NombreProducto = this.result.getString(5);
                pedido.PrecioProducto = this.result.getString(6);
                
                //Agregar el objeto usuario a la lista
                
                listaPedido.add(pedido);


                    
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
        return listaPedido;
        
    }//fin del metodo buscar
        
    @Override
    public boolean BuscarPorId(int id) {
        this.idProducto = id;
         if (super.openConectionBD()) {
            try {
                //JOptionPane.showMessageDialog(null, super.getMensajes());
                //Llamar al procedimiento almacenado
                this.cstmt=super.getConexion().prepareCall("");
                this.cstmt.setInt(1, this.idProducto);
                this.result=cstmt.executeQuery();

                while (this.result.next()) {
                this.idProducto = this.result.getInt(1);
                this.CodigoProducto = this.result.getString(2);
                this.DescripcionProducto = this.result.getString(3);
                this.ExistenciaProducto = this.result.getString(4);
                this.NombreProducto = this.result.getString(5);
                this.PrecioProducto = this.result.getString(6);


                    
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
    }//fin del metodo buscar por id
    @Override
    public boolean modificar(int id) {
        this.idProducto=id;
        if (super.openConectionBD()) {
            try {
                //JOptionPane.showMessageDialog(null, super.getMensajes());
                //Llamar al procedimiento almacenado
                this.cstmt=super.getConexion().prepareCall("");
                this.cstmt.setInt(1, this.idProducto);
                this.cstmt.setString(2, this.CodigoProducto);
                this.cstmt.setString(3, this.DescripcionProducto);
                this.cstmt.setString(4, this.ExistenciaProducto);
                this.cstmt.setString(5, this.NombreProducto);
                this.cstmt.setString(6, this.PrecioProducto);
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
        
    }//fin del metodo modificar

    @Override
    public boolean eliminar(int id) {
        this.idProducto=id;
        if (super.openConectionBD()) {
            try {
                //JOptionPane.showMessageDialog(null, super.getMensajes());
                //Llamar al procedimiento almacenado
                this.cstmt=super.getConexion().prepareCall("");
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
        
    }//fin del metodo eliminar    
    
    
    
    
    

