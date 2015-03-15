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
public interface IBehaviour {
    /**
     * выводит строку имитирующую звуковой сигнал
     * @return 
     */
    String Beep();
    
    
    
    boolean setPic(BufferedImage pic/* вы€снить какие параметры ещЄ нужны */);
    
    BufferedImage getPic();
    
//    void moveLeft();
//    void moveRight();
//    void moveUp();
//    void moveDown();
    
    
    
}
