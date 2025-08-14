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
    
    //Constructor
    public RolUsuario() {
    }

    public RolUsuario(int idRolUsuario, String tipoRolUsuario, String descripcionRoLUsuario) {
        this.idRolUsuario = idRolUsuario;
        this.tipoRolUsuario = tipoRolUsuario;
        this.descripcionRoLUsuario = descripcionRoLUsuario;
    }
    
    //Métodos set y get
    public int getIdRolUsuario() { return idRolUsuario; }
    public void setIdRolUsuario(int idRolUsuario) { this.idRolUsuario = idRolUsuario; }
    public String getTipoRolUsuario() { return tipoRolUsuario; }
    public void setTipoRolUsuario(String tipoRolUsuario) { this.tipoRolUsuario = tipoRolUsuario; }
    public String getDescripcionRoLUsuario() { return descripcionRoLUsuario; }
    public void setDescripcionRoLUsuario(String descripcionRoLUsuario) { this.descripcionRoLUsuario = descripcionRoLUsuario; }

    //Método toString
    @Override
    public String toString() {
        return "RolUsuario{" + "idRolUsuario=" + idRolUsuario + ", tipoRolUsuario=" + tipoRolUsuario + ", descripcionRoLUsuario=" + descripcionRoLUsuario + '}';
    }

    @Override
    public boolean insertar() {
        if (!super.openConectionBD()) {
            JOptionPane.showMessageDialog(null, super.getMensajes());
            return false;
        }

        String sql = "call bd_sistema_login.sp_insertar_rol_usuario(?,?,?)";

        try (CallableStatement cstmt = super.getConexion().prepareCall(sql)) {
            cstmt.setInt(1, this.idRolUsuario);
            cstmt.setString(2, this.tipoRolUsuario);
            cstmt.setString(3, this.descripcionRoLUsuario);

            cstmt.execute();
            return true;

        } catch (SQLException ex) {
            super.setMensajes("Error sql: " + ex.getMessage());
        } catch (Exception ex) {
            super.setMensajes("Error inesperado: " + ex.getMessage());
        } finally {
            try { super.getConexion().close(); } catch (SQLException e) { /* ignorar */ }
        }

        JOptionPane.showMessageDialog(null, super.getMensajes());
        return false;
    }

    @Override
    public ArrayList<RolUsuario> buscar() {
        ArrayList<RolUsuario> listaRolUsuarios = new ArrayList<>();

        if (!super.openConectionBD()) {
            JOptionPane.showMessageDialog(null, super.getMensajes());
            return listaRolUsuarios;
        }

        String sql = "call bd_sistema_login.sp_buscar_rolusuario()";

        try (CallableStatement cstmt = super.getConexion().prepareCall(sql);
             ResultSet result = cstmt.executeQuery()) {

            while (result.next()) {
                RolUsuario rolusuario = new RolUsuario(
                        result.getInt(1),
                        result.getString(2),
                        result.getString(3)
                );
                listaRolUsuarios.add(rolusuario);
            }

        } catch (SQLException ex) {
            super.setMensajes("Error sql: " + ex.getMessage());
            JOptionPane.showMessageDialog(null, super.getMensajes());
        } catch (Exception ex) {
            super.setMensajes("Error inesperado: " + ex.getMessage());
            JOptionPane.showMessageDialog(null, super.getMensajes());
        } finally {
            try { super.getConexion().close(); } catch (SQLException e) { /* ignorar */ }
        }

        return listaRolUsuarios;
    }

    @Override
    public boolean BuscarPorId(int id) {
        this.idRolUsuario = id;

        if (!super.openConectionBD()) {
            JOptionPane.showMessageDialog(null, super.getMensajes());
            return false;
        }

        String sql = "call bd_sistema_login.sp_buscar_rolusuario_id(?)";

        try (CallableStatement cstmt = super.getConexion().prepareCall(sql)) {
            cstmt.setInt(1, this.idRolUsuario);

            try (ResultSet result = cstmt.executeQuery()) {
                if (result.next()) {
                    this.idRolUsuario = result.getInt(1);
                    this.tipoRolUsuario = result.getString(2);
                    this.descripcionRoLUsuario = result.getString(3);
                    return true;
                }
            }

        } catch (SQLException ex) {
            super.setMensajes("Error sql: " + ex.getMessage());
        } catch (Exception ex) {
            super.setMensajes("Error inesperado: " + ex.getMessage());
        } finally {
            try { super.getConexion().close(); } catch (SQLException e) { /* ignorar */ }
        }

        JOptionPane.showMessageDialog(null, super.getMensajes());
        return false;
    }

    @Override
    public boolean modificar(int id) {
        this.idRolUsuario = id;

        if (!super.openConectionBD()) {
            JOptionPane.showMessageDialog(null, super.getMensajes());
            return false;
        }

        String sql = "call bd_sistema_login.sp_actualizar_RolUsuario(?,?,?)";

        try (CallableStatement cstmt = super.getConexion().prepareCall(sql)) {
            cstmt.setInt(1, this.idRolUsuario);
            cstmt.setString(2, this.tipoRolUsuario);
            cstmt.setString(3, this.descripcionRoLUsuario);

            cstmt.execute();
            return true;

        } catch (SQLException ex) {
            super.setMensajes("Error sql: " + ex.getMessage());
        } catch (Exception ex) {
            super.setMensajes("Error inesperado: " + ex.getMessage());
        } finally {
            try { super.getConexion().close(); } catch (SQLException e) { /* ignorar */ }
        }

        JOptionPane.showMessageDialog(null, super.getMensajes());
        return false;
    }

    @Override
    public boolean eliminar(int id) {
        this.idRolUsuario = id;

        if (!super.openConectionBD()) {
            JOptionPane.showMessageDialog(null, super.getMensajes());
            return false;
        }

        String sql = "call bd_sistema_login.sp_eliminar_rolusuario(?)";

        try (CallableStatement cstmt = super.getConexion().prepareCall(sql)) {
            cstmt.setInt(1, this.idRolUsuario);
            cstmt.execute();
            return true;

        } catch (SQLException ex) {
            super.setMensajes("Error sql: " + ex.getMessage());
        } catch (Exception ex) {
            super.setMensajes("Error inesperado: " + ex.getMessage());
        } finally {
            try { super.getConexion().close(); } catch (SQLException e) { /* ignorar */ }
        }

        JOptionPane.showMessageDialog(null, super.getMensajes());
        return false;
    }
}
