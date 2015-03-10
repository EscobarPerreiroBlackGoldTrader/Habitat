/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package habitat;

import java.awt.Image;

/**
 *
 * @author iUser
 */
public class Moto extends Velocity implements IBehaviour {
    
    
   //public static double p2 = 0.3;

    @Override
    public String Beep() {
       return "Im a Moto"; 
    }
    
    public Image pic;
}
