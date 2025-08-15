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
public class Pedido extends ConexionBD {
    //Atributos
    private int idProducto;
    private String CodigoProducto;
    private String  NombreProducto;
    private String CantidadProducto;
    private String PrecioProducto;
    private String PreFinProducto;
      private CallableStatement cstmt;
    private ResultSet result;

    public Pedido() {
    }

    public Pedido(int idProducto, String CodigoProducto, String NombreProducto, String CantidadProducto, String PrecioProducto, String PreFinProducto) {
        this.idProducto = idProducto;
        this.CodigoProducto = CodigoProducto;
        this.NombreProducto = NombreProducto;
        this.CantidadProducto = CantidadProducto;
        this.PrecioProducto = PrecioProducto;
        this.PreFinProducto = PreFinProducto;
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

    public String getCantidadProducto() {
        return CantidadProducto;
    }

    public void setCantidadProducto(String CantidadProducto) {
        this.CantidadProducto = CantidadProducto;
    }

    public String getPrecioProducto() {
        return PrecioProducto;
    }

    public void setPrecioProducto(String PrecioProducto) {
        this.PrecioProducto = PrecioProducto;
    }

    public String getPreFinProducto() {
        return PreFinProducto;
    }

    public void setDescripcionProducto(String DescripcionProducto) {
        this.PreFinProducto = DescripcionProducto;
    }

    @Override
    public String toString() {
        return "Pedido{" + "idProducto=" + idProducto + ", CodigoProducto=" + CodigoProducto + ", NombreProducto=" + NombreProducto + ", CantidadProducto=" + CantidadProducto + ", PrecioProducto=" + PrecioProducto + ", PreFinProducto=" + PreFinProducto + '}';
    }
    
    

public boolean vaciarTabla () {
    if (!super.openConectionBD()) {
        JOptionPane.showMessageDialog(null, super.getMensajes());
        return false;
    }

    String sql = "call bd_sistema_login.VaciarPedido();"; // ajusta con tu procedimiento

    try (CallableStatement cstmt = super.getConexion().prepareCall(sql)) {
        cstmt.execute();
        return true;
    } catch (SQLException ex) {
        super.setMensajes("Error de base de datos: " + ex.getMessage());
    } catch (Exception ex) {
        super.setMensajes("Error inesperado: " + ex.getMessage());
    } finally {
        try { super.getConexion().close(); } catch (SQLException e) { /* ignorar */ }
    }

    JOptionPane.showMessageDialog(null, super.getMensajes());
    return false;
}

public ArrayList<Pedido> buscar() {
    ArrayList<Pedido> listaPedido = new ArrayList<>();

        if (!super.openConectionBD()) {
            JOptionPane.showMessageDialog(null, super.getMensajes());
            return listaPedido;
        }

        String sql = "call bd_sistema_login.sp_buscar_pedido();";

    try (CallableStatement cstmt = super.getConexion().prepareCall(sql);
         ResultSet result = cstmt.executeQuery()) {

        while (result.next()) {
            Pedido pedido = new Pedido(
                result.getInt(1),
                result.getString(2),
                result.getString(3),
                result.getString(4),
                result.getString(5),
                result.getString(6)
            );
            listaPedido.add(pedido);
        }

    } catch (SQLException ex) {
        super.setMensajes("Error de base de datos: " + ex.getMessage());
        JOptionPane.showMessageDialog(null, super.getMensajes());
    } catch (Exception ex) {
        super.setMensajes("Error inesperado: " + ex.getMessage());
        JOptionPane.showMessageDialog(null, super.getMensajes());
    } finally {
        try { super.getConexion().close(); } catch (SQLException e) { /* ignorar */ }
    }

    return listaPedido;
}
public boolean Agregar () {
    if (!super.openConectionBD()) {
        JOptionPane.showMessageDialog(null, super.getMensajes());
        return false;
    }

    String sql = "call bd_sistema_login.agregar_pedido(?,?);"; // ajusta con tu procedimiento

    try (CallableStatement cstmt = super.getConexion().prepareCall(sql)) {
        cstmt.setString(1, this.CodigoProducto);
        cstmt.setString(2, this.CantidadProducto);
        
        cstmt.execute();
        return true;
    } catch (SQLException ex) {
        super.setMensajes("Error de base de datos: " + ex.getMessage());
    } catch (Exception ex) {
        super.setMensajes("Error inesperado: " + ex.getMessage());
    } finally {
        try { super.getConexion().close(); } catch (SQLException e) { /* ignorar */ }
    }

    JOptionPane.showMessageDialog(null, super.getMensajes());
    return false;
}

public boolean BuscarPorId(int id) {
this.idProducto = id;

    if (!super.openConectionBD()) {
        JOptionPane.showMessageDialog(null, super.getMensajes());
        return false;
    }

    String sql = "call bd_sistema_login.sp_buscarProdId(?);"; // ajusta con tu procedimiento

    try (CallableStatement cstmt = super.getConexion().prepareCall(sql)) {
        cstmt.setInt(1, this.idProducto);   
        cstmt.execute();
        try (ResultSet result = cstmt.executeQuery()) {
            if (result.next()) {
                // Obtener los valores del ResultSet
                this.CodigoProducto = result.getString("codigoProducto"); // nombre exacto de la columna en la BD
                this.CantidadProducto = result.getString("cantidad"); // nombre exacto de la columna en la BD
                return true;
            } else {
                return false; // no se encontró el registro
            }
        }

    } catch (SQLException ex) {
        super.setMensajes("Error de base de datos: " + ex.getMessage());
    } catch (Exception ex) {
        super.setMensajes("Error inesperado: " + ex.getMessage());
    } finally {
        try { super.getConexion().close(); } catch (SQLException e) { /* ignorar */ }
    }

    JOptionPane.showMessageDialog(null, super.getMensajes());
    return false;
}

public boolean eliminarCod () {
    if (!super.openConectionBD()) {
        JOptionPane.showMessageDialog(null, super.getMensajes());
        return false;
    }

    String sql = "call bd_sistema_login.sp_quitar_a_pedido(?,?);"; // ajusta con tu procedimiento

    try (CallableStatement cstmt = super.getConexion().prepareCall(sql)) {
        cstmt.setString(1, this.CodigoProducto);
        cstmt.setString(2, this.CantidadProducto);
        
        cstmt.execute();
        return true;
    } catch (SQLException ex) {
        super.setMensajes("Error de base de datos: " + ex.getMessage());
    } catch (Exception ex) {
        super.setMensajes("Error inesperado: " + ex.getMessage());
    } finally {
        try { super.getConexion().close(); } catch (SQLException e) { /* ignorar */ }
    }

    JOptionPane.showMessageDialog(null, super.getMensajes());
    return false;
}
public boolean eliminarid(int id) {
    this.idProducto = id;

    if (!super.openConectionBD()) {
        JOptionPane.showMessageDialog(null, super.getMensajes());
        return false;
    }

    String sql = "call bd_sistema_login.sp_eliminar_pedidoid(?);"; // ajusta con tu procedimiento

    try (CallableStatement cstmt = super.getConexion().prepareCall(sql)) {
        cstmt.setInt(1, this.idProducto);
        cstmt.execute();
        return true;

    } catch (SQLException ex) {
        super.setMensajes("Error de base de datos: " + ex.getMessage());
    } catch (Exception ex) {
        super.setMensajes("Error inesperado: " + ex.getMessage());
    } finally {
        try { super.getConexion().close(); } catch (SQLException e) { /* ignorar */ }
    }

    JOptionPane.showMessageDialog(null, super.getMensajes());
    return false;
}
}
    
    
    
    

