/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package habitat;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.applet.Applet;
//import java.net.URL;
import java.awt.image.BufferedImage;
//import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.ArrayList;
import java.util.ListIterator;
import javax.imageio.ImageIO;
import javax.swing.event.MouseInputAdapter;
//==============================================================================
/**
 *
 * @author iUser
 */
public class Habitat extends Applet {
    private Timer m_timer = new Timer();
    private boolean m_runViaFrame = false; 
    private double m_time = 0;
    private final double p1 = 0.25; // ����������� ��������� ���������
    private final double p2 = 0.35; // ����������� ��������� ������
    private boolean emul_progress = false; // ������������� �������� T
    private boolean showtime = false; // ������������� �������� B
    
    int vel_count = 0;
    int vel_shown = 0;
    
    // ������������� ��� ��������� ���� � ���������
//    private final String moto_path = "images/motopic.png";  
//    private final String car_path = "images/carpic.png";
//    
//    private final URL motoURL = Habitat.class.getResource(moto_path);
//    private final URL carURL = Habitat.class.getResource(car_path);
//    
//    private final ImageIcon motoico = new ImageIcon(motoURL);
//    private final ImageIcon carico = new ImageIcon(carURL);
    
    //BufferedImage mot = null;
    BufferedImage motopic = null;
    BufferedImage carpic = null; 
    Image offScreenImage = null;
    
    private ArrayList<Velocity> lst; //������ �� ������ ��������
    //private boolean firstRUN = true; // ����� ������� ���������� (���������� ��� ���������� ��������)
    private boolean picLoaded=false; // �������� �������� �����������
    private String m_FileName1 = "motopic.png";
    private String m_FileName2 = "carpic.png";
    //private String PARAM_string_1 = "fileName";
    
//==============================================================================
    private class Updater extends TimerTask {
        private Habitat m_aplet = null;
        private boolean m_firstRun = true; // ������ �� ������ ������ run()?
        private long m_startTime = 0; // ����� ������ 
        private long m_lastTime = 0;  // ����� ���������� ����������
        
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
                 //����� ��������� �� ������, � ��������.
                 double elapsed = (currentTime - m_startTime) / 1000.0;
                 //����� ��������� �� ���������� ����������, � ��������.
                 double frameTime = (m_lastTime - m_startTime) / 1000.0;
             
                 //�������� ����������
                 m_aplet.Update(elapsed, frameTime);
                 m_lastTime = currentTime;
            }else{
                
            }
        }
    }
//==============================================================================   
    public Habitat(){
        System.out.println("����������� Habitat �������");
        
//        try{
//            mot = ImageIO.read(new File("./motopic.png"));
//        }catch(IOException e){
//            e.printStackTrace();
//        }
          try{
              motopic = ImageIO.read(getClass().getResource("motopic.png"));
          }catch(IOException e){
              //e.printStackTrace();
          }
    
          try{
              carpic = ImageIO.read(getClass().getResource("carpic.png"));
          }catch(IOException e){
              
          }
          
        
        //motopic = getImage(getCodeBase(), "images/motopic.png");
        //if(carpic == null)carpic = getImage(getDocumentBase(), "carpic.png");
        lst = new ArrayList<>();
        
        //---------------------------------
        // ���������� ������� �� ���� 
        //(�������� ����� � ������ �����. 
        // ����� ����� ������� ���� ����� �������� �����, 
        // ����� �� ����� �������������� ������� �� ����������.)
        
      MouseInputAdapter pm;  
      pm = new MouseInputAdapter() { 
       @Override
       public void mousePressed(MouseEvent e) { 
//             x=e.getX(); y=e.getY(); 
//             System.out.println(x); 
//             repaint(); 
       }}; 
       this.addMouseListener(pm);
        
        //---------------------------------
        KeyAdapter pk;
        
        pk = new KeyAdapter() {
        @Override
        public void keyPressed(KeyEvent e){
            System.out.println(e);
            int keycode = e.getKeyCode();
            
            switch(keycode){
                case KeyEvent.VK_B: // ��������� ���������
                    System.out.println("B is pressed");
                    emul_progress = true;
                    repaint();
                    break;
                case KeyEvent.VK_E: //���������� ���������
                    System.out.println("E is pressed");
                    emul_progress = false;
                    vel_shown = 0;
                    vel_count = 0;
                    lst.clear();
                    repaint();
                    break;
                case KeyEvent.VK_T:
                    System.out.println("T is pressed");
                    showtime = !showtime;
                    repaint();
                    break;
            }
            
        } 
    };
        this.addKeyListener(pk);
        Init();
        
    }
 //==============================================================================   
    public Habitat(boolean viaFrame){
        m_runViaFrame = viaFrame;
        Init();
    }
//==============================================================================    
    @Override
    public boolean imageUpdate(Image img, int infoflags,int x, int y,int w, int h){
        
        if(infoflags == ALLBITS){
            picLoaded = true;
            repaint();
            return false; // ������ ����� update() �� ��������
        }else{
            return true;
        }
    }
//==============================================================================    
    private void Init(){
        // ������ ����� ���������� ������ 1500��
        m_timer.schedule(new Updater(this), 0, 100);
     
        //String param = getParameter(PARAM_string_1);
        
    }
//==============================================================================    
    public void Update(double elapsedTime, double frameTime){
       m_time = elapsedTime;
       
       if(emul_progress){
           double p0 = Math.random();
           
           if(p0 <= p1){ // �������� ��������
               
               Moto m = new Moto();
               if(!m.setPic(motopic)) System.err.println("motoico missed");
               m.x = (int)((getWidth()/1.2) * Math.random());
               m.y = (int)((getHeight()/1.2) *Math.random()); 
               lst.add(m);
               ++vel_count;
               //repaint();
           }
           
           if(p0 <= p2){ // ��������� ������
               
               Car c = new Car();
               if(!c.setPic(carpic)) System.err.println("carico missed");
               c.x = (int)((getWidth()/1.2) * Math.random());
               c.y = (int)((getHeight()/1.2) *Math.random()); 
               lst.add(c);
               ++vel_count;
               //repaint();
           }    
       }
       
       
       this.repaint();
    }
    
//==============================================================================    
    @Override
    public void paint(Graphics g){
        // �������� ������������ ������
        int width = getSize().width,heigth = getSize().height;    
        offScreenImage = createImage(width, heigth);
        // ��������� ��������� ������������ ������
        Graphics offScreenGraphics = offScreenImage.getGraphics();
        
        //������� ������      
            offScreenGraphics.setColor(Color.white);
            offScreenGraphics.fillRect(0, 0, this.getWidth(), this.getHeight());
            offScreenGraphics.setColor(Color.black);
            //firstRUN = false;

        String str = "Time =" + Double.toString(m_time); //��������� ������� �� �������
        
        if(showtime)offScreenGraphics.drawString(str, 15, 15); // ����������� �������        
        
        //if(vel_count > vel_shown){ //���� �������������� ����� ���������
            synchronized(lst){ // ������������� ��������� � ����� (���������� ��������� ���������)        
                //Iterator<Velocity> iterator = lst.iterator();
                ListIterator<Velocity> l_it = lst.listIterator(/*vel_shown*/0);

                for(int i=0/*vel_shown*/;l_it.hasNext();++i,l_it.next() ,vel_shown = i){
                    //Class<? extends Velocity> s = lst.get(i).getClass();      
                    //g.drawString(s.getTypeName(), 50 + 10*i, 50);
                    //g.drawString("step {i}", 200 + 10*1, 50 + 20*i);
            
                  //  g.drawString(lst.get(i).Beep(),75,50+ i*10);
                    offScreenGraphics.drawImage(lst.get(i).getPic(),
                            /*(int)((getWidth()/1.2) * Math.random())*/ lst.get(i).x,
                            /*(int)((getHeight()/1.2) *Math.random()) */lst.get(i).y,
                            this);
                    //
                }
            }
        //}        
        offScreenGraphics.drawString("showtime = " + showtime, 100, 100);
        offScreenGraphics.drawString("emul_progress = " + emul_progress, 120, 120);
        offScreenGraphics.drawString("vel_count = " + vel_count, 120, 130);
        offScreenGraphics.drawString("vel_shown = " + vel_shown, 120, 140);
        
        
        //--------
        if(/*picLoaded*/true){
           g.drawImage(offScreenImage, 0, 0, null); 
        }else{
            showStatus("Loading image");
        }
        
        //--------
        
    }
//==============================================================================    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        JFrame frame = new JFrame();
        
        
        Habitat app = new Habitat();
        //app.init(); 
        app.start();
        
        
        frame.getContentPane().add(app);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,600);
        frame.setVisible(true);
    
    }
    
    
    
}// end
//==============================================================================
