/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package habitat;

import java.awt.image.BufferedImage;

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

    @Override
    public boolean setPic(BufferedImage pic) {
        if(pic != null){
            this.pic = pic;
            
            return true;
        }else{
            return false;
        }
    }
    
    /**
     * ������ ������ �� �������� 
     */
    private BufferedImage pic;

    @Override
    public BufferedImage getPic() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return this.pic;
    }

    
}
