/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author 52558
 */
public class Usuario extends ConexionBD implements CRUDInterface {
    //Atributos
    private int idUsuario;
    private String nombreUsuario;
    private String apPaternoUsuario;
    private String apMaternoUsuario;
    private String emailUsuario;
    private String telefonoCelular;

    //Constructor
    public Usuario() {
    }

    public Usuario(int idUsuario, String nombreUsuario, String apPaternoUsuario, String apMaternoUsuario, String emailUsuario, String telefonoCelular) {
        this.idUsuario = idUsuario;
        this.nombreUsuario = nombreUsuario;
        this.apPaternoUsuario = apPaternoUsuario;
        this.apMaternoUsuario = apMaternoUsuario;
        this.emailUsuario = emailUsuario;
        this.telefonoCelular = telefonoCelular;
    }

    //Métodos set y get
    public int getIdUsuario() { return idUsuario; }
    public void setIdUsuario(int idUsuario) { this.idUsuario = idUsuario; }
    public String getNombreUsuario() { return nombreUsuario; }
    public void setNombreUsuario(String nombreUsuario) { this.nombreUsuario = nombreUsuario; }
    public String getApPaternoUsuario() { return apPaternoUsuario; }
    public void setApPaternoUsuario(String apPaternoUsuario) { this.apPaternoUsuario = apPaternoUsuario; }
    public String getApMaternoUsuario() { return apMaternoUsuario; }
    public void setApMaternoUsuario(String apMaternoUsuario) { this.apMaternoUsuario = apMaternoUsuario; }
    public String getEmailUsuario() { return emailUsuario; }
    public void setEmailUsuario(String emailUsuario) { this.emailUsuario = emailUsuario; }
    public String getTelefonoCelular() { return telefonoCelular; }
    public void setTelefonoCelular(String telefonoCelular) { this.telefonoCelular = telefonoCelular; }

    //Método toString
    @Override
    public String toString() {
        return "Usuario{" + "idUsuario=" + idUsuario + ", nombreUsuario=" + nombreUsuario + ", apPaternoUsuario=" + apPaternoUsuario +
                ", apMaternoUsuario=" + apMaternoUsuario + ", emailUsuario=" + emailUsuario + ", telefonoCelular=" + telefonoCelular + '}';
    }

    @Override
    public boolean insertar() {
        if (!super.openConectionBD()) {
            JOptionPane.showMessageDialog(null, super.getMensajes());
            return false;
        }

        String sql = "call bd_sistema_login.sp_insertar_usuario(?,?,?,?,?)";

        try (CallableStatement cstmt = super.getConexion().prepareCall(sql)) {
            cstmt.setString(1, this.nombreUsuario);
            cstmt.setString(2, this.apPaternoUsuario);
            cstmt.setString(3, this.apMaternoUsuario);
            cstmt.setString(4, this.emailUsuario);
            cstmt.setString(5, this.telefonoCelular);

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
    public ArrayList<Usuario> buscar() {
        ArrayList<Usuario> listaUsuarios = new ArrayList<>();

        if (!super.openConectionBD()) {
            JOptionPane.showMessageDialog(null, super.getMensajes());
            return listaUsuarios;
        }

        String sql = "call bd_sistema_login.sp_buscar_usuario()";

        try (CallableStatement cstmt = super.getConexion().prepareCall(sql);
             ResultSet result = cstmt.executeQuery()) {

            while (result.next()) {
                Usuario usuario = new Usuario(
                        result.getInt(1),
                        result.getString(2),
                        result.getString(3),
                        result.getString(4),
                        result.getString(5),
                        result.getString(6)
                );
                listaUsuarios.add(usuario);
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

        return listaUsuarios;
    }

    @Override
    public boolean BuscarPorId(int id) {
        this.idUsuario = id;

        if (!super.openConectionBD()) {
            JOptionPane.showMessageDialog(null, super.getMensajes());
            return false;
        }

        String sql = "call bd_sistema_login.sp_buscar_usuario_id(?)";

        try (CallableStatement cstmt = super.getConexion().prepareCall(sql)) {
            cstmt.setInt(1, this.idUsuario);

            try (ResultSet result = cstmt.executeQuery()) {
                if (result.next()) {
                    this.idUsuario = result.getInt(1);
                    this.nombreUsuario = result.getString(2);
                    this.apPaternoUsuario = result.getString(3);
                    this.apMaternoUsuario = result.getString(4);
                    this.emailUsuario = result.getString(5);
                    this.telefonoCelular = result.getString(6);
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
        this.idUsuario = id;

        if (!super.openConectionBD()) {
            JOptionPane.showMessageDialog(null, super.getMensajes());
            return false;
        }

        String sql = "call bd_sistema_login.sp_actualizar_usuario(?,?,?,?,?,?)";

        try (CallableStatement cstmt = super.getConexion().prepareCall(sql)) {
            cstmt.setInt(1, this.idUsuario);
            cstmt.setString(2, this.nombreUsuario);
            cstmt.setString(3, this.apPaternoUsuario);
            cstmt.setString(4, this.apMaternoUsuario);
            cstmt.setString(5, this.emailUsuario);
            cstmt.setString(6, this.telefonoCelular);

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
        this.idUsuario = id;

        if (!super.openConectionBD()) {
            JOptionPane.showMessageDialog(null, super.getMensajes());
            return false;
        }

        String sql = "call bd_sistema_login.sp_eliminar_usuario(?)";

        try (CallableStatement cstmt = super.getConexion().prepareCall(sql)) {
            cstmt.setInt(1, this.idUsuario);
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
