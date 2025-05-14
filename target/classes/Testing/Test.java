/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Testing;

import Clases.Usuario;
import middlewares.CurrentSession;

/**
 *
 * @author criskop
 */
public class Test {
    
    public static void main(String[] args) {
        
     CurrentSession.getInstance().login(1, "1234");
        
        /*
        =========================================================================
        Linea de codigo para recuperar la clase instancia:

        CurrentSession currentUser = CurrentSession.getInstance();
        =========================================================================
        */
         
         CurrentSession currentUser = CurrentSession.getInstance();
         
         Usuario usuario = currentUser.getCurrentSessionData();
         
         System.out.println(usuario.getNombre());
        System.out.println(usuario.getRol());
    }
    
}
