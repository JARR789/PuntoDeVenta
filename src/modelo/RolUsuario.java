/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;


import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.sql.CallableStatement;
import java.sql.ResultSet;

/**
 *
 * @author J.A.R.R
 */
public class RolUsuario extends ConexionBD implements CRUDInterface {
    //Atributos
    private int idRolUsuario;
    private String tipoRolUsuario;
    private String descripcionRoLUsuario;
    private CallableStatement cstmt;
    private ResultSet result;
    
   //Constructor

    public RolUsuario() {
    }

    public RolUsuario(int idRolUsuario, String tipoRolUsuario, String descripcionRoLUsuario) {
        this.idRolUsuario = idRolUsuario;
        this.tipoRolUsuario = tipoRolUsuario;
        this.descripcionRoLUsuario = descripcionRoLUsuario;
    }
    
    //Metodos set y get

    public int getIdRolUsuario() {
        return idRolUsuario;
    }

    public void setIdRolUsuario(int idRolUsuario) {
        this.idRolUsuario = idRolUsuario;
    }

    public String getTipoRolUsuario() {
        return tipoRolUsuario;
    }

    public void setTipoRolUsuario(String tipoRolUsuario) {
        this.tipoRolUsuario = tipoRolUsuario;
    }

    public String getDescripcionRoLUsuario() {
        return descripcionRoLUsuario;
    }

    public void setDescripcionRoLUsuario(String descripcionRoLUsuario) {
        this.descripcionRoLUsuario = descripcionRoLUsuario;
    }
    //Metodo toString

    @Override
    public String toString() {
        return "RolUsuario{" + "idRolUsuario=" + idRolUsuario + ", \n tipoRolUsuario=" + tipoRolUsuario + ", \n descripcionRoLUsuario=" + descripcionRoLUsuario + '}';
    }

    @Override
    public boolean insertar() {
        if (super.openConectionBD()) {
            try {
              
                //Llamar al procedimiento almacenado
                this.cstmt=super.getConexion().prepareCall("call bd_sistema_login.sp_insertar_rol_usuario(?,?,?);");
                this.cstmt.setInt(1, this.idRolUsuario);
                this.cstmt.setString(2, this.tipoRolUsuario);
                this.cstmt.setString(3, this.descripcionRoLUsuario);
               
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
    public ArrayList<RolUsuario> buscar() {
        ArrayList<RolUsuario> listaRolUsuarios = new ArrayList<RolUsuario>();
        if (super.openConectionBD()) {
            try {
                //JOptionPane.showMessageDialog(null, super.getMensajes());
                //Llamar al procedimiento almacenado
                this.cstmt=super.getConexion().prepareCall("call bd_sistema_login.sp_buscar_rolsuario();");
              
                //  EJECUTAR CONSULTA
                this.result=cstmt.executeQuery();

                while (this.result.next()) {
                
                RolUsuario rolusuario = new RolUsuario();
                rolusuario.idRolUsuario = this.result.getInt(1);
                rolusuario.tipoRolUsuario = this.result.getString(2);
                rolusuario.descripcionRoLUsuario = this.result.getString(3);

                //Agregar el objeto usuario a la lista
                
                listaRolUsuarios.add(rolusuario);


                    
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
        return listaRolUsuarios;
      
    }

    
        
       
    

    @Override
    public boolean BuscarPorId (int id) {
         this.idRolUsuario = id;
         if (super.openConectionBD()) {
            try {
                //JOptionPane.showMessageDialog(null, super.getMensajes());
                //Llamar al procedimiento almacenado
                this.cstmt=super.getConexion().prepareCall("call bd_sistema_login.sp_buscar_rolusuario_id(?);");
                this.cstmt.setInt(1, this.idRolUsuario);
                this.result=cstmt.executeQuery();

                while (this.result.next()) {
                this.idRolUsuario = this.result.getInt(1);
                this.tipoRolUsuario = this.result.getString(2);
                this.descripcionRoLUsuario = this.result.getString(3);
          


                    
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
    }//Fin del metodo  buscar id

        
    

    @Override
    public boolean modificar(int id) {
        this.idRolUsuario=id;
        if (super.openConectionBD()) {
            try {
                //JOptionPane.showMessageDialog(null, super.getMensajes());
                //Llamar al procedimiento almacenado
                this.cstmt=super.getConexion().prepareCall("call bd_sistema_login.sp_actualizar_RolUsuario(?,?,?);");
                this.cstmt.setInt(1, this.idRolUsuario);
                this.cstmt.setString(2, this.tipoRolUsuario);
                this.cstmt.setString(3, this.descripcionRoLUsuario);
       
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
         this.idRolUsuario=id;
        if (super.openConectionBD()) {
            try {
                //JOptionPane.showMessageDialog(null, super.getMensajes());
                //Llamar al procedimiento almacenado
                this.cstmt=super.getConexion().prepareCall("call bd_sistema_login.sp_eliminar_rolusuario(?);");
                this.cstmt.setInt(1, this.idRolUsuario);
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

