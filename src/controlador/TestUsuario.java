/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import modelo.Usuario;

/**
 *
 * @author J.A.R.R
 */
public class TestUsuario {
    public static void main(String[] args){
    Usuario usuario=new Usuario(1,"Jose Antonio", "Ruiz", "Rivera", "patata909@gmail.com", "8641263133");
    System.out.println(usuario);
    Usuario usuario2=new Usuario();
    usuario2.setIdUsuario(2);
    usuario2.setNombreUsuario("Atzin");
    usuario2.setApPaternoUsuario("Rivas");
    usuario2.setApMaternoUsuario("Aguilar");
    usuario2.setEmailUsuario("24300212@uttt.edu.mx");
    usuario2.setTelefonoCelular("773645473");
    System.out.println(usuario2);   
    System.out.println("");
        
    Usuario usuario3=new Usuario();
        
        
    usuario3.setIdUsuario(3);
    usuario3.setNombreUsuario("Stefany");
    usuario3.setApPaternoUsuario("Ausencio");
    usuario3.setApMaternoUsuario("lopez");
    usuario3.setEmailUsuario("24300619@uttt.edu.mx");
    usuario3.setTelefonoCelular("7727634259");
    
        System.out.println("Id \t Nombre \t Apellido Paterno \t Apellido Materno \t Email \t Telefono \n");
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------");
        System.out.println(usuario3.getIdUsuario() + "\t" + usuario3.getNombreUsuario() + "\t" + usuario3.getApPaternoUsuario() + "\t" + usuario3.getApMaternoUsuario() + "\t" + usuario.getEmailUsuario() + "\t" +usuario3.getTelefonoCelular() + "\n");
        
    }
}
