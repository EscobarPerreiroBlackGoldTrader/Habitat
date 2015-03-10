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
public class Car extends Velocity implements IBehaviour {

    public Car(){
        pic = null;
    }
    
   //public static double p1 = 0.2;

    @Override
    public String Beep() {
      return "Im a Car";
    }

    
    
    public Image pic;
}
