//Ryan Alli

//imports for drawing Images
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Graphics;
//import java.applet.*;
public class Knight 
{
    private int x, y, vx, vy, length, countdown, health, enemiesKilled;
    private int direction;
    private boolean isAttacking = false;
    private long startTime, currTime;
    //private AudioClip playerHurt;
    private Image[] walkDown;
    private Image[] walkUp;
    private Image[] walkLeft;
    private Image[] walkRight;
    
    private Image[] AttackDown;
    private Image[] AttackUp;
    private Image[] AttackLeft;
    private Image[] AttackRight; 
    
    public Knight(int xpos, int ypos)
    {
        x = xpos;
        y = ypos;
        vx = 0;
        vy = 0;
        direction = 5;
        length = 64;
        countdown = 200;
        health = 10;
        enemiesKilled = 1;
        startTime = System.currentTimeMillis();
        
        //playerHurt = Applet.newAudioClip(this.getClass().getResource("SFX/PlayerHurt.wav"));
        
        //walk animations
        walkDown = new Image[5];
        walkUp = new Image[5];
        walkRight = new Image[5];
        walkLeft = new Image[5];
        //Attack animations
        AttackDown = new Image[5];
        AttackUp = new Image[5];
        AttackLeft = new Image[5];
        AttackRight = new Image[5];
          
        //Walking animations
        
        //down
        ImageIcon walkImg = new ImageIcon(Knight.class.getResource("KnightAnimations/KnightWalkDown1.png"));
        walkDown[0] = walkImg.getImage();
        walkImg = new ImageIcon(Knight.class.getResource("KnightAnimations/KnightWalkDown2.png"));
        walkDown[1] = walkImg.getImage();
        walkImg = new ImageIcon(Knight.class.getResource("KnightAnimations/KnightWalkDown3.png"));
        walkDown[2] = walkImg.getImage();
        walkImg = new ImageIcon(Knight.class.getResource("KnightAnimations/KnightWalkDown4.png"));
        walkDown[3] = walkImg.getImage();
        walkImg = new ImageIcon(Knight.class.getResource("KnightAnimations/KnightWalkDown5.png"));
        walkDown[4] = walkImg.getImage();
        
        //up
        walkImg = new ImageIcon(Knight.class.getResource("KnightAnimations/KnightWalkUp1.png"));
        walkUp[0] = walkImg.getImage();
        walkImg = new ImageIcon(Knight.class.getResource("KnightAnimations/KnightWalkUp2.png"));
        walkUp[1] = walkImg.getImage();
        walkImg = new ImageIcon(Knight.class.getResource("KnightAnimations/KnightWalkUp3.png"));
        walkUp[2] = walkImg.getImage();
        walkImg = new ImageIcon(Knight.class.getResource("KnightAnimations/KnightWalkUp4.png"));
        walkUp[3] = walkImg.getImage();
        walkImg = new ImageIcon(Knight.class.getResource("KnightAnimations/KnightWalkUp5.png"));
        walkUp[4] = walkImg.getImage();
        
        //left
        walkImg = new ImageIcon(Knight.class.getResource("KnightAnimations/KnightWalkLeft1.png"));
        walkLeft[0] = walkImg.getImage();
        walkImg = new ImageIcon(Knight.class.getResource("KnightAnimations/KnightWalkLeft2.png"));
        walkLeft[1] = walkImg.getImage();
        walkImg = new ImageIcon(Knight.class.getResource("KnightAnimations/KnightWalkLeft3.png"));
        walkLeft[2] = walkImg.getImage();
        walkImg = new ImageIcon(Knight.class.getResource("KnightAnimations/KnightWalkLeft4.png"));
        walkLeft[3] = walkImg.getImage();
        walkImg = new ImageIcon(Knight.class.getResource("KnightAnimations/KnightWalkLeft5.png"));
        walkLeft[4] = walkImg.getImage();
        
        //right
        walkImg = new ImageIcon(Knight.class.getResource("KnightAnimations/KnightWalkRight1.png"));
        walkRight[0] = walkImg.getImage();
        walkImg = new ImageIcon(Knight.class.getResource("KnightAnimations/KnightWalkRight2.png"));
        walkRight[1] = walkImg.getImage();
        walkImg = new ImageIcon(Knight.class.getResource("KnightAnimations/KnightWalkRight3.png"));
        walkRight[2] = walkImg.getImage();
        walkImg = new ImageIcon(Knight.class.getResource("KnightAnimations/KnightWalkRight4.png"));
        walkRight[3] = walkImg.getImage();
        walkImg = new ImageIcon(Knight.class.getResource("KnightAnimations/KnightWalkRight5.png"));
        walkRight[4] = walkImg.getImage();
        
//===========================================================================================================        
        
        //Attack animations
        
        // Attack Down
        ImageIcon attackImg = new ImageIcon(Knight.class.getResource("KnightAnimations/Knight AtkD1.png"));
        AttackDown[0] = attackImg.getImage();
        attackImg = new ImageIcon(Knight.class.getResource("KnightAnimations/Knight AtkD2.png"));
        AttackDown[1] = attackImg.getImage();
        attackImg = new ImageIcon(Knight.class.getResource("KnightAnimations/Knight AtkD3.png"));
        AttackDown[2] = attackImg.getImage();
        attackImg = new ImageIcon(Knight.class.getResource("KnightAnimations/Knight AtkD4.png"));
        AttackDown[3] = attackImg.getImage();
        attackImg = new ImageIcon(Knight.class.getResource("KnightAnimations/Knight AtkD5.png"));
        AttackDown[4] = attackImg.getImage();
        
        //Attack Up
        attackImg = new ImageIcon(Knight.class.getResource("KnightAnimations/Knight AtkU1.png"));
        AttackUp[0] = attackImg.getImage();
        attackImg = new ImageIcon(Knight.class.getResource("KnightAnimations/Knight AtkU2.png"));
        AttackUp[1] = attackImg.getImage();
        attackImg = new ImageIcon(Knight.class.getResource("KnightAnimations/Knight AtkU3.png"));
        AttackUp[2] = attackImg.getImage();
        attackImg = new ImageIcon(Knight.class.getResource("KnightAnimations/Knight AtkU4.png"));
        AttackUp[3] = attackImg.getImage();
        attackImg = new ImageIcon(Knight.class.getResource("KnightAnimations/Knight AtkU5.png"));
        AttackUp[4] = attackImg.getImage();
        
        //Attack Right
        attackImg = new ImageIcon(Knight.class.getResource("KnightAnimations/Knight AtkR1.png"));
        AttackRight[0] = attackImg.getImage();
        attackImg = new ImageIcon(Knight.class.getResource("KnightAnimations/Knight AtkR2.png"));
        AttackRight[1] = attackImg.getImage();
        attackImg = new ImageIcon(Knight.class.getResource("KnightAnimations/Knight AtkR3.png"));
        AttackRight[2] = attackImg.getImage();
        attackImg = new ImageIcon(Knight.class.getResource("KnightAnimations/Knight AtkR4.png"));
        AttackRight[3] = attackImg.getImage();
        attackImg = new ImageIcon(Knight.class.getResource("KnightAnimations/Knight AtkR5.png"));
        AttackRight[4] = attackImg.getImage();
        
        //Attacking Left
        attackImg = new ImageIcon(Knight.class.getResource("KnightAnimations/Knight AtkL1.png"));
        AttackLeft[0] = attackImg.getImage();
        attackImg = new ImageIcon(Knight.class.getResource("KnightAnimations/Knight AtkL2.png"));
        AttackLeft[1] = attackImg.getImage();
        attackImg = new ImageIcon(Knight.class.getResource("KnightAnimations/Knight AtkL3.png"));
        AttackLeft[2] = attackImg.getImage();
        attackImg = new ImageIcon(Knight.class.getResource("KnightAnimations/Knight AtkL4.png"));
        AttackLeft[3] = attackImg.getImage();
        attackImg = new ImageIcon(Knight.class.getResource("KnightAnimations/Knight AtkL5.png"));
        AttackLeft[4] = attackImg.getImage();
    }
    
    public void setIsAttacking(boolean bool)
    {
        isAttacking = bool;
    }
    public void setVelX(int vXp)
    {
        vx = vXp;
    }
    public void setVelY(int vYp)
    {
        vy = vYp;
    }
    public boolean getIsAttacking()
    {
        return isAttacking;
    }
    public int getX()
    {
        return x;
    }
    public int getY()
    {
        return y;
    }
    public int getVX()
    {
        return vx;
    }
    public int getVY()
    {
        return vy;
    }
    public void setDirection(int d)
    {
        direction = d;
    }
    public int getDirection()
    {
        return direction;
    }
    public int getLength()
    {
        return length;
    }
     public int getHealth()
    {
        return health;
    }
    public void setHealth(int h)
    {
        health = h;
    }
    public void movement()
    {
        x += vx;
        y += vy;
    }
    public void enemyKilled()
    {
        enemiesKilled ++;
    }
    public void act(Graphics2D g)
    {
        currTime = System.currentTimeMillis() - startTime;
        long index = currTime%500/100;
        
        if(direction == 1)
        {
            g.drawImage(walkUp[(int)index], x, y, length, length, null);
            isAttacking = false;
        }
        if(direction == 2)
        {
            g.drawImage(walkLeft[(int)index], x, y, length, length, null);
            isAttacking = false;
        }
        if(direction == 3)
        {
            g.drawImage(walkDown[(int)index], x, y, length, length, null);
            isAttacking = false;
        }
        if(direction == 4)
        {
            g.drawImage(walkRight[(int)index], x, y, length, length, null);
            isAttacking = false;
        }
        
        //idle
        if(direction == 5)
        {
            g.drawImage(walkDown[2], x, y, length, length, null);
            isAttacking = false;
        }
        if(direction == 6)
        {
            g.drawImage(walkUp[2], x, y, length, length, null);
            isAttacking = false;
        }
        if(direction == 7)
        {
            g.drawImage(walkLeft[2], x, y, length, length, null);
            isAttacking = false;
        }
        if(direction == 8)
        {
            g.drawImage(walkRight[2], x, y, length, length, null);
            isAttacking = false;
        }
        
        //Attacking
        
        
        if(direction == 10)
        {
            g.drawImage(AttackUp[(int)index], x, y, length, length, null);
            isAttacking = true;
        }
        if(direction == 11)
        {
            g.drawImage(AttackLeft[(int)index], x, y, length, length, null);
            isAttacking = true;
        }
        if(direction == 12)
        {
            g.drawImage(AttackDown[(int)index], x, y, length, length, null);
            isAttacking = true;
        }
        if(direction == 13)
        {
            g.drawImage(AttackRight[(int)index], x, y, length, length, null);
            isAttacking = true;
        }
    }
    public void takeDamage(int d)
    {
        health -= d;
        //playerHurt.play();
    }   
}