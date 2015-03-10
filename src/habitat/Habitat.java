/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package habitat;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author iUser
 */
public class Habitat extends JApplet {
    private Timer m_timer = new Timer();
    private boolean m_runViaFrame = false; 
    private double m_time = 0;
    private final double p1 = 0.25;
    private final double p2 = 0.35;
    private boolean emul_progress = false; // переключаетс€ нажатием T
    private boolean showtime = false; // переключаетс€ нажатием B
    
    

    private ArrayList<Velocity> lst; //ссылка на список объектов  
    
    
    private class Updater extends TimerTask {
        private Habitat m_aplet = null;
        private boolean m_firstRun = true; // первый ли запуск метода run()?
        private long m_startTime = 0; // врем€ начала 
        private long m_lastTime = 0;  // врем€ последнего обновлени€
        
        public Updater(Habitat applet){
            m_aplet = applet;
        }
        
        @Override
        public void run(){
            
            if(true){
                if(m_firstRun){
                    m_startTime = System.currentTimeMillis();
                    m_lastTime = m_startTime;
                    m_firstRun = false;
                }
            
                 long currentTime  = System.currentTimeMillis();
                 //врем€ прошедшее от начала, в секундах.
                 double elapsed = (currentTime - m_startTime) / 1000.0;
                 //врем€ прошедшее от последнего обновлени€, в секундах.
                 double frameTime = (m_lastTime - m_startTime) / 1000.0;
             
                 //вызываем обновление
                 m_aplet.Update(elapsed, frameTime);
                 m_lastTime = currentTime;
            }else{
                
            }
        }
    }
    
    public Habitat(){
        System.out.println("конструктор Habitat запущен");
        lst = new ArrayList<>();
        
        KeyAdapter pk;
        
        pk = new KeyAdapter() {
        
        @Override
        public void keyPressed(KeyEvent e){
            System.out.println(e);
            int keycode = e.getKeyCode();
            
            switch(keycode){
                case KeyEvent.VK_B: // запустить симул€цию
                    System.out.println("B is pressed");
                    emul_progress = true;
                    break;
                case KeyEvent.VK_E: //остановить симул€цию
                    System.out.println("E is pressed");
                    emul_progress = false;
                    break;
                case KeyEvent.VK_T:
                    System.out.println("T is pressed");
                    showtime = !showtime;
                    break;
            }
            
        } 
    };
        this.addKeyListener(pk);
        Init();
        //lst.add(new Car());
        //lst.add(new Moto());
    }
    
    public Habitat(boolean viaFrame){
        m_runViaFrame = viaFrame;
        Init();
    }
    
    private void Init(){
        // таймер будет вызыватьс€ каждые 100мс
        m_timer.schedule(new Updater(this), 0, 100);
       
        
    }
    
    public void Update(double elapsedTime, double frameTime){
       m_time = elapsedTime;
       this.repaint();
    }
    
    
    @Override
    public void paint(Graphics g){
        //очистка экрана      
        g.setColor(Color.white);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        
        
        g.setColor(Color.black);
        String str = "Time =" + Double.toString(m_time); //получение времени по таймеру
        
        if(showtime)g.drawString(str, 15, 15); // отображение таймера        
        
        Iterator<Velocity> iterator = lst.iterator();
        
        for(int i=0;iterator.hasNext();++i,iterator.next()){
//            Class<? extends Velocity> s = lst.get(i).getClass();      
//            g.drawString(s.getTypeName(), 50 + 10*i, 50);
//            g.drawString("step {i}", 200 + 10*1, 50 + 20*i);
            
            g.drawString(lst.get(i).Beep(),75,50+ i*10);
            
        }
        
        g.drawString("showtime = " + showtime, 100, 100);
        g.drawString("emul_progress = " + emul_progress, 120, 120);
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        JFrame frame = new JFrame();
        Habitat app = new Habitat();
        app.init(); app.start();
        
        frame.getContentPane().add(app);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,600);
        frame.setVisible(true);
    
    }
    
    
    
}// end
