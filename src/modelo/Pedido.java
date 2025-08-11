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
public class Pedido   {
    //Atributos
    private int idProducto;
    private String CodigoProducto;
    private String  NombreProducto;
    private String ExistenciaProducto;
    private String PrecioProducto;
    private String DescripcionProducto;

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
    
    
    
    
    
    
}
