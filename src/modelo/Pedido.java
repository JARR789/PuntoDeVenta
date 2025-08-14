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
    if (!super.openConectionBD()) {
        JOptionPane.showMessageDialog(null, super.getMensajes());
        return false;
    }

    String sql = "{call sp_insertar_producto(?, ?, ?, ?, ?)}"; // ajusta con tu procedimiento

    try (CallableStatement cstmt = super.getConexion().prepareCall(sql)) {
        cstmt.setString(1, this.CodigoProducto);
        cstmt.setString(2, this.DescripcionProducto);
        cstmt.setString(3, this.ExistenciaProducto);
        cstmt.setString(4, this.NombreProducto);
        cstmt.setString(5, this.PrecioProducto);

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

@Override
public ArrayList<Pedido> buscar() {
    ArrayList<Pedido> listaPedido = new ArrayList<>();

    if (!super.openConectionBD()) {
        JOptionPane.showMessageDialog(null, super.getMensajes());
        return listaPedido;
    }

    String sql = "{call sp_buscar_productos()}"; // ajusta con tu procedimiento

    try (CallableStatement cstmt = super.getConexion().prepareCall(sql);
         ResultSet result = cstmt.executeQuery()) {

        while (result.next()) {
            Pedido pedido = new Pedido(
                result.getInt(1),
                result.getString(2),
                result.getString(5),
                result.getString(4),
                result.getString(6),
                result.getString(3)
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

@Override
public boolean BuscarPorId(int id) {
    this.idProducto = id;

    if (!super.openConectionBD()) {
        JOptionPane.showMessageDialog(null, super.getMensajes());
        return false;
    }

    String sql = "{call sp_buscar_producto_por_id(?)}"; // ajusta con tu procedimiento

    try (CallableStatement cstmt = super.getConexion().prepareCall(sql)) {
        cstmt.setInt(1, this.idProducto);

        try (ResultSet result = cstmt.executeQuery()) {
            if (result.next()) {
                this.idProducto = result.getInt(1);
                this.CodigoProducto = result.getString(2);
                this.DescripcionProducto = result.getString(3);
                this.ExistenciaProducto = result.getString(4);
                this.NombreProducto = result.getString(5);
                this.PrecioProducto = result.getString(6);
                return true;
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

@Override
public boolean modificar(int id) {
    this.idProducto = id;

    if (!super.openConectionBD()) {
        JOptionPane.showMessageDialog(null, super.getMensajes());
        return false;
    }

    String sql = "{call sp_modificar_producto(?, ?, ?, ?, ?, ?)}"; // ajusta con tu procedimiento

    try (CallableStatement cstmt = super.getConexion().prepareCall(sql)) {
        cstmt.setInt(1, this.idProducto);
        cstmt.setString(2, this.CodigoProducto);
        cstmt.setString(3, this.DescripcionProducto);
        cstmt.setString(4, this.ExistenciaProducto);
        cstmt.setString(5, this.NombreProducto);
        cstmt.setString(6, this.PrecioProducto);

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

@Override
public boolean eliminar(int id) {
    this.idProducto = id;

    if (!super.openConectionBD()) {
        JOptionPane.showMessageDialog(null, super.getMensajes());
        return false;
    }

    String sql = "{call sp_eliminar_producto(?)}"; // ajusta con tu procedimiento

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
    
    
    
    

