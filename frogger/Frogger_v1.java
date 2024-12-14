
import java.applet.*;
import java.awt.*;
import java.awt.event.*;
// this applet prints out the key stroke

public class Frogger_v1 extends Applet implements Runnable, KeyListener 
{ 
  // Create an off-screen image
     Graphics bufferGraphics; 
     Image offscreen; 
 
     // for the width and height of the applet. 
     int appletHeight, appletWidth;
     
     
  Image fUpImage, fDownImage, fLeftImage, fRightImage, currentFrogImage, froggerBackground, 
    redCar, blueCar, greenCar, yellowCar, pinkCar;
  
  int FrogX, FrogY,redCarX, redCarY, FrogW, FrogH, redCarW, redCarH,
    blueCarX, blueCarY, blueCarW, blueCarH, greenCarX, greenCarY, greenCarW, greenCarH,
    yellowCarX, yellowCarY, yellowCarW, yellowCarH, pinkCarX, pinkCarY, pinkCarW, pinkCarH,
    backgroundW, backgroundH;
  int Froglives;

 // *****************************************************************
 public void init()
 {
    appletHeight = 800;
   appletWidth = 1200;  

   // Make the off-screen the size of the applet screen. 
   offscreen = createImage(appletWidth, appletHeight); 
   bufferGraphics = offscreen.getGraphics(); 

   setBackground(Color.black);
   addKeyListener(this);
   FrogX = 725;
   FrogY = 710;
   FrogW = 50;
   FrogH = 50;
   Froglives = 3;
     
   redCarX = 1550;
   redCarY = 300;
   redCarW = 200;
   redCarH = 100;
   
   blueCarX = 1550;
   blueCarY = 550;
   blueCarW = 200;
   blueCarH = 100;
   
   greenCarX = 1550;
   greenCarY = 300;
   greenCarW = 200;
   greenCarH = 100;
   
   yellowCarX = 1550;
   yellowCarY = 300;
   yellowCarW = 200;
   yellowCarH = 100;
   
   pinkCarX = 200;
   pinkCarY = 700;
   pinkCarW = 200;
   pinkCarH = 100;
   
   
   
   
   
   
   fUpImage = this.getImage (this.getDocumentBase(), "FUp.png");
   fDownImage = this.getImage (this.getDocumentBase(), "FDown.png");
   fLeftImage = this.getImage (this.getDocumentBase(), "FLeft.png");
   fRightImage = this.getImage (this.getDocumentBase(), "FRight.png");
   currentFrogImage = this.getImage (this.getDocumentBase(), "FUp.png");
   froggerBackground = this.getImage (this.getDocumentBase(), "frogger_background.gif");
   
   redCar = this.getImage (this.getDocumentBase(), "redCar.png");
   blueCar = this.getImage (this.getDocumentBase(), "blueCar.png");
   greenCar = this.getImage (this.getDocumentBase(), "greenCar.png");
   yellowCar = this.getImage (this.getDocumentBase(), "yellowCar.png");
   pinkCar = this.getImage (this.getDocumentBase(), "pinkCar.png");


 }
 
 public void start(){
   Thread myAnimation = new Thread (this);
   myAnimation.start();
 }


 public void keyTyped(KeyEvent e){
 // do nothing
 }
 public void keyPressed(KeyEvent e){}

 public void keyReleased(KeyEvent e) 
 {
  int key = e.getKeyCode();
  System.out.println("you pressed " + key);
  
  if (key == 38){ 
    FrogY = FrogY - 10; 
    currentFrogImage = fUpImage;
  }
    else if (key == 37){ 
      FrogX = FrogX - 10; 
      currentFrogImage = fLeftImage;                        
    }                                     
    else if (key == 39){
      FrogX = FrogX + 10;
     currentFrogImage = fRightImage;     
    }
    else if (key == 40){
      FrogY = FrogY + 10;
     currentFrogImage = fDownImage;
    }
    repaint();
    e.consume(); 
 }
   
 //end of KeyReleased method
 // ***************************************************************** 
 

 public void paint (Graphics g)
 {
    bufferGraphics.clearRect(0,0,appletWidth, appletHeight);

  bufferGraphics.drawImage(froggerBackground, 0, 0, appletWidth, appletHeight, this);
  bufferGraphics.drawImage(currentFrogImage, FrogX, FrogY, this);
  bufferGraphics.setColor(Color.red);
  
  bufferGraphics.drawImage(redCar, redCarX, redCarY, this);
  
  bufferGraphics.drawImage(blueCar, blueCarX, blueCarY, this);
  
  g.drawImage(offscreen,0,0,this);
 }
 
 public void update(Graphics g) 
{ 
   paint(g); 


}

 public void run(){
   while(true){
     redCarX = redCarX - 2;
     if (redCarX == 0){
       redCarX = 1200;
     }
     repaint();
     try{Thread.sleep(20);}
     catch(InterruptedException e) {}
     
      
     if                    
       (collision(FrogX, FrogY, FrogW, FrogH, redCarX, redCarY, redCarW, redCarH)) { 
                    
       FrogX = 725;           
       FrogY = 710;        
       Froglives = Froglives - 1;
    }
   }
 }

 private boolean collision (int x1, int y1, int width1, int height1, int x2, int y2, int width2, int height2 ) 

 {
        if (redCarX + redCarW <= FrogX || FrogX + FrogW <= redCarX ||  redCarY + redCarH <= FrogY || FrogY + FrogH <= redCarY) 
           return false; // No collision
       else
         return true; // Collision detected
       
 }        
}
