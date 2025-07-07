import java.util.ArrayList;
import java.util.Random;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

public class sneakegame extends JPanel implements ActionListener,KeyListener{
    class tile{
        int x,y;
        tile(int x,int y){
            this.x=x;
            this.y=y;
        }
    }
    int boardwidth;
    int boarheight;
    int tilesize =30;

    tile sneakhead;
    tile food;

    Random random;


    Timer gamelogic;
    int velocityX,velocityY;
   
     ArrayList<tile> sneakebodey;

    boolean Gameover =false;

    public sneakegame(int w,int h) {
        this.boardwidth=w;
        this.boarheight=h;
        setPreferredSize(new Dimension(this.boardwidth,this.boarheight));
        setBackground(Color.BLACK);
        addKeyListener(this);
        setFocusable(true);


        sneakhead=new tile(6,6);
        sneakebodey = new ArrayList<>();

        food =new tile(11,11);

        random= new Random();
        palcefood();

        gamelogic = new Timer(100,this);
        gamelogic.start();
        velocityX=0;
        velocityY=0;
    }
    public void draw(Graphics g){
       
        //food
        g.setColor(Color.YELLOW);
        
        g.fill3DRect(food.x*tilesize,food.y*tilesize,tilesize,tilesize,Boolean.TRUE);
        //snake body
        g.setColor(Color.blue);
        g.fill3DRect(sneakhead.x*tilesize,sneakhead.y*tilesize,tilesize,tilesize,true);
        for(int i =0;i<sneakebodey.size();i++){
            tile sneakepart= sneakebodey.get(i);
            // g.fillRect(sneakepart.x*tilesize,sneakepart.y*tilesize,tilesize,tilesize);
            g.fill3DRect(sneakepart.x*tilesize,sneakepart.y*tilesize,tilesize,tilesize,true);
         }
        g.setFont(new Font("Arial",Font.PLAIN,15));
        if(Gameover){
            g.setColor(Color.ORANGE);
            g.drawString("Game over :" +String.valueOf(sneakebodey.size()),tilesize-15,tilesize);
        }
        else {
            g.drawString("score : "+String.valueOf(sneakebodey.size()),tilesize-15,tilesize);
        }
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
    }
    public boolean  collisoin(tile t1,tile t2){
        return t1.x == t2.x&&t1.y ==t2.y;
    }
    public void palcefood(){
        food.x =random.nextInt(boardwidth/tilesize);
        food.y =random.nextInt(boarheight/tilesize);
    };
     public void move(){
        //game over condition
        for (int i = 0; i < sneakebodey.size(); i++) {
            tile sneakepart =sneakebodey.get(i);
            if(collisoin(sneakhead,sneakepart )){
                Gameover =true;
            }
        }
        if (collisoin(sneakhead,food)){
           sneakebodey.add(new tile(food.x,food.y));
            palcefood();
        }
        //sneake body 
        for (int i =sneakebodey.size()-1;i>=0;i--) {
            tile sneakepart=sneakebodey.get(i);
            if(i==0){
                sneakepart.x =sneakhead.x;
                sneakepart.y =sneakhead.y;
            }
            else{
                tile prevsnakepart = sneakebodey.get(i-1);
                sneakepart.x=prevsnakepart.x;
                sneakepart.y=prevsnakepart.y;
            }
        }
        sneakhead.x+=velocityX;
        sneakhead.y+=velocityY;
        if (sneakhead.x*tilesize<0||sneakhead.x*tilesize>boarheight|| sneakhead.y*tilesize<0||sneakhead.y*tilesize>boarheight){
            Gameover =true;
        }
     }
    public void actionPerformed (ActionEvent e){
        move();   
        repaint(); 
        if(Gameover){
            gamelogic.stop();
        }
    }

      @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                velocityX=0;
                velocityY =-1;
                break;
            case KeyEvent.VK_DOWN:
                velocityX=0;
                velocityY =1;
                break;
            case KeyEvent.VK_LEFT:
                velocityX=-1;
                velocityY =0;
                break;
            case KeyEvent.VK_RIGHT:
                velocityX=1;
                velocityY =0;
                break;
            default:
                break;
        }
       }
   
    public void keyTyped(KeyEvent e) {}
    public void keyReleased(KeyEvent e) {}
}
