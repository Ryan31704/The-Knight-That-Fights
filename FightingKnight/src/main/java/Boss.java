//Ryan Alli

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;
import java.awt.Graphics;
//import java.applet.*;

public class Boss
{
    private int x, y, vx, vy, diam, health, stage;
    private long startTime, currTime, actionTime, currentTime;
    private Image[] stageOne;
    private Image[] stageTwo;
    //private AudioClip enemyHurt;
    
    public Boss(int xpos, int ypos)
    {
        x = xpos;
        y = ypos;
        vx = 0;
        vy = 0;
        diam = 128;
        stage = 1;
        health = 20;
        actionTime = 0;
        startTime = System.currentTimeMillis();
        stageOne = new Image[8];
        //enemyHurt = Applet.newAudioClip(this.getClass().getResource("SFX/EnemyHurt2.wav"));
//        stageTwo = new Image[8];
        
        
        //getting animations for stage one and two
        //stage one
        ImageIcon Boss = new ImageIcon(Enemy.class.getResource("BossAnimations/BossStageOne1.png"));
        stageOne[0] = Boss.getImage();
        Boss = new ImageIcon(Enemy.class.getResource("BossAnimations/BossStageOne2.png"));
        stageOne[1] = Boss.getImage();
        Boss = new ImageIcon(Enemy.class.getResource("BossAnimations/BossStageOne3.png"));
        stageOne[2] = Boss.getImage();
        Boss = new ImageIcon(Enemy.class.getResource("BossAnimations/BossStageOne4.png"));
        stageOne[3] = Boss.getImage();
        Boss = new ImageIcon(Enemy.class.getResource("BossAnimations/BossStageOne5.png"));
        stageOne[4] = Boss.getImage();
        Boss = new ImageIcon(Enemy.class.getResource("BossAnimations/BossStageOne6.png"));
        stageOne[5] = Boss.getImage();
        Boss = new ImageIcon(Enemy.class.getResource("BossAnimations/BossStageOne7.png"));
        stageOne[6] = Boss.getImage();
        Boss = new ImageIcon(Enemy.class.getResource("BossAnimations/BossStageOne8.png"));
        stageOne[7] = Boss.getImage();
        
        //stage two
//        Boss = new ImageIcon(Enemy.class.getResource("BossAnimations/BossStageTwo1.png"));
//        stageTwo[0] = Boss.getImage();
//        Boss = new ImageIcon(Enemy.class.getResource("BossAnimations/BossStageTwo2.png"));
//        stageTwo[1] = Boss.getImage();
//        Boss = new ImageIcon(Enemy.class.getResource("BossAnimations/BossStageTwo3.png"));
//        stageTwo[2] = Boss.getImage();
//        Boss = new ImageIcon(Enemy.class.getResource("BossAnimations/BossStageTwo4.png"));
//        stageTwo[3] = Boss.getImage();
//        Boss = new ImageIcon(Enemy.class.getResource("BossAnimations/BossStageTwo5.png"));
//        stageTwo[4] = Boss.getImage();
//        Boss = new ImageIcon(Enemy.class.getResource("BossAnimations/BossStageTwo6.png"));
//        stageTwo[5] = Boss.getImage();
//        Boss = new ImageIcon(Enemy.class.getResource("BossAnimations/BossStageTwo7.png"));
//        stageTwo[6] = Boss.getImage();
//        Boss = new ImageIcon(Enemy.class.getResource("BossAnimations/BossStageTwo8.png"));
//        stageTwo[7] = Boss.getImage();
    }
    public void setVelX(int vXp)
    {
        vx = vXp;
    }
    public void setVelY(int vYp)
    {
        vy = vYp;
    }
    public int getX()
    {
        return x;
    }
    public int getY()
    {
        return y;
    }
    public void setcords(int xCord, int yCord)
    {
        x = xCord;
        y = yCord;
    }
    public int getVX()
    {
        return vx;
    }
    public int getVY()
    {
        return vy;
    }
    
    public int getDiam()
    {
        return diam;
    }
    public int getHealth()
    {
        return health;
    }
    public void setHealth(int h)
    {
        health = h;
    }
    
    public void TakeDamage(int d)
    {
        health -= d;
        //enemyHurt.play();
    }
    
    public void spawn()
    {
        if(y < 100)
        {
            vy = 1;
        }
        else
        {
            vy = 0;
        }
    }
    public void movemnt()
    {
        x += vx;
        y += vy;
    }
    
    public void moveAround()
    {
        double dir = Math.random();
        currentTime = System.currentTimeMillis();
        if(currentTime - actionTime >= 1500)
        {
            actionTime = currentTime;
            
            if(dir > 0.5)
            {
                vx = 1;
            }
            else
            {
                vx = -1;
            }
        }
        if(x < 200)
        {
            vx = 1;
        }
        else if(x > 800)
        {
            vx = -1;
        }
    }
    
    public void DrawSelf(Graphics2D g)
    {
        currTime = System.currentTimeMillis() - startTime;
        long index = currTime%500/100;
        
        if(stage == 1)
        {
            g.drawImage(stageOne[(int)index], x, y, diam, diam, null);
        }
        else if(stage == 2)
        {
            g.drawImage(stageTwo[(int)index], x, y, diam, diam, null);
        }
    }
}