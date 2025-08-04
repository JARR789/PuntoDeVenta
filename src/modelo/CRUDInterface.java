/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package modelo;

import java.util.ArrayList;

/**
 *
 * @author J.A.R.R
 */
public interface CRUDInterface {
    
    public boolean insertar();//Create
    public ArrayList buscar();//Read
    public boolean BuscarPorId(int id);
    public boolean modificar(int id);//update
    public boolean eliminar(int id);//Delete
}
