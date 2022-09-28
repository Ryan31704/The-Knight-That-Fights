//Ryan Alli

//imports for drawing Images
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;
import java.awt.Graphics;
//import java.applet.*;
public class Enemy
{
    private int x, y, vx, vy, diam, health, lastDirection, attackDirection, direction;
    private long startTime, currTime;
    private long moveStart, moveCooldown;
    private int cooldownTime;
    private double difficultyMultiplier;
    private boolean attacking;
    //private AudioClip enemyHurt;
    //private AudioClip enemyHurt2;
    private Image[] goblinWalkR;
    private Image[] goblinWalkL;
    private Image[] goblinAtkR;
    private Image[] goblinAtkL;
    
    public Enemy(int xpos, int ypos)
    {
        x = xpos;
        y = ypos;
        vx = 0;
        vy = 0;
        diam = 64;
        health = 2;
        lastDirection = 0;
        attacking = false;
        attackDirection = 0;
        moveStart = 0;
        cooldownTime = 100;
        startTime = System.currentTimeMillis();
        
        //enemyHurt = Applet.newAudioClip(this.getClass().getResource("SFX/EnemyHurt.wav"));
        //enemyHurt2 = Applet.newAudioClip(this.getClass().getResource("SFX/EnemyHurt2.wav"));
        
        goblinWalkR = new Image[10];
        goblinWalkL = new Image[10];
        goblinAtkR = new Image[9];
        goblinAtkL = new Image[9];
        
        
        //walk animations for goblin
        //walk right
        ImageIcon goblin = new ImageIcon(Enemy.class.getResource("GoblinAnimations/GoblinWalkR1.png"));
        goblinWalkR[0] = goblin.getImage();
        goblin = new ImageIcon(Enemy.class.getResource("GoblinAnimations/GoblinWalkR2.png"));
        goblinWalkR[1] = goblin.getImage();
        goblin = new ImageIcon(Enemy.class.getResource("GoblinAnimations/GoblinWalkR3.png"));
        goblinWalkR[2] = goblin.getImage();
        goblin = new ImageIcon(Enemy.class.getResource("GoblinAnimations/GoblinWalkR4.png"));
        goblinWalkR[3] = goblin.getImage();
        goblin = new ImageIcon(Enemy.class.getResource("GoblinAnimations/GoblinWalkR5.png"));
        goblinWalkR[4] = goblin.getImage();
        goblin = new ImageIcon(Enemy.class.getResource("GoblinAnimations/GoblinWalkR6.png"));
        goblinWalkR[5] = goblin.getImage();
        goblin = new ImageIcon(Enemy.class.getResource("GoblinAnimations/GoblinWalkR7.png"));
        goblinWalkR[6] = goblin.getImage();
        goblin = new ImageIcon(Enemy.class.getResource("GoblinAnimations/GoblinWalkR8.png"));
        goblinWalkR[7] = goblin.getImage();
        goblin = new ImageIcon(Enemy.class.getResource("GoblinAnimations/GoblinWalkR9.png"));
        goblinWalkR[8] = goblin.getImage();
        goblin = new ImageIcon(Enemy.class.getResource("GoblinAnimations/GoblinWalkR10.png"));
        goblinWalkR[9] = goblin.getImage();
        
        //walk left
        goblin = new ImageIcon(Enemy.class.getResource("GoblinAnimations/GoblinWalkL1.png"));
        goblinWalkL[0] = goblin.getImage();
        goblin = new ImageIcon(Enemy.class.getResource("GoblinAnimations/GoblinWalkL2.png"));
        goblinWalkL[1] = goblin.getImage();
        goblin = new ImageIcon(Enemy.class.getResource("GoblinAnimations/GoblinWalkL3.png"));
        goblinWalkL[2] = goblin.getImage();
        goblin = new ImageIcon(Enemy.class.getResource("GoblinAnimations/GoblinWalkL4.png"));
        goblinWalkL[3] = goblin.getImage();
        goblin = new ImageIcon(Enemy.class.getResource("GoblinAnimations/GoblinWalkL5.png"));
        goblinWalkL[4] = goblin.getImage();
        goblin = new ImageIcon(Enemy.class.getResource("GoblinAnimations/GoblinWalkL6.png"));
        goblinWalkL[5] = goblin.getImage();
        goblin = new ImageIcon(Enemy.class.getResource("GoblinAnimations/GoblinWalkL7.png"));
        goblinWalkL[6] = goblin.getImage();
        goblin = new ImageIcon(Enemy.class.getResource("GoblinAnimations/GoblinWalkL8.png"));
        goblinWalkL[7] = goblin.getImage();
        goblin = new ImageIcon(Enemy.class.getResource("GoblinAnimations/GoblinWalkL9.png"));
        goblinWalkL[8] = goblin.getImage();
        
        //========================================================================
        //attack animations
        
        //attack left
        goblin = new ImageIcon(Enemy.class.getResource("GoblinAnimations/GoblinAtkL1.png"));
        goblinAtkL[0] = goblin.getImage();
        goblin = new ImageIcon(Enemy.class.getResource("GoblinAnimations/GoblinAtkL2.png"));
        goblinAtkL[1] = goblin.getImage();
        goblin = new ImageIcon(Enemy.class.getResource("GoblinAnimations/GoblinAtkL3.png"));
        goblinAtkL[2] = goblin.getImage();
        goblin = new ImageIcon(Enemy.class.getResource("GoblinAnimations/GoblinAtkL4.png"));
        goblinAtkL[3] = goblin.getImage();
        goblin = new ImageIcon(Enemy.class.getResource("GoblinAnimations/GoblinAtkL5.png"));
        goblinAtkL[4] = goblin.getImage();
        goblin = new ImageIcon(Enemy.class.getResource("GoblinAnimations/GoblinAtkL6.png"));
        goblinAtkL[5] = goblin.getImage();
        goblin = new ImageIcon(Enemy.class.getResource("GoblinAnimations/GoblinAtkL7.png"));
        goblinAtkL[6] = goblin.getImage();
        goblin = new ImageIcon(Enemy.class.getResource("GoblinAnimations/GoblinAtkL8.png"));
        goblinAtkL[7] = goblin.getImage();
        goblin = new ImageIcon(Enemy.class.getResource("GoblinAnimations/GoblinAtkL9.png"));
        goblinAtkL[8] = goblin.getImage();
        
        //attacking right
        goblin = new ImageIcon(Enemy.class.getResource("GoblinAnimations/GoblinAtkR1.png"));
        goblinAtkR[0] = goblin.getImage();
        goblin = new ImageIcon(Enemy.class.getResource("GoblinAnimations/GoblinAtkR2.png"));
        goblinAtkR[1] = goblin.getImage();
        goblin = new ImageIcon(Enemy.class.getResource("GoblinAnimations/GoblinAtkR3.png"));
        goblinAtkR[2] = goblin.getImage();
        goblin = new ImageIcon(Enemy.class.getResource("GoblinAnimations/GoblinAtkR4.png"));
        goblinAtkR[3] = goblin.getImage();
        goblin = new ImageIcon(Enemy.class.getResource("GoblinAnimations/GoblinAtkR5.png"));
        goblinAtkR[4] = goblin.getImage();
        goblin = new ImageIcon(Enemy.class.getResource("GoblinAnimations/GoblinAtkR6.png"));
        goblinAtkR[5] = goblin.getImage();
        goblin = new ImageIcon(Enemy.class.getResource("GoblinAnimations/GoblinAtkR7.png"));
        goblinAtkR[6] = goblin.getImage();
        goblin = new ImageIcon(Enemy.class.getResource("GoblinAnimations/GoblinAtkR8.png"));
        goblinAtkR[7] = goblin.getImage();
        goblin = new ImageIcon(Enemy.class.getResource("GoblinAnimations/GoblinAtkR9.png"));
        goblinAtkR[8] = goblin.getImage();
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
    public boolean getAttacking()
    {
        return attacking;
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
    
    public void takeDamage(int d, Knight k)
    {
        double ran = Math.random();
        if(ran>= 0.5)
        {
            //enemyHurt.play();
        }
        else
        {
            //enemyHurt2.play();
        }
        if(k.getDirection() == 13 && x > k.getX())
        {
            x += 30 * difficultyMultiplier;
            health -=d;
        }
        if(k.getDirection() == 11 && x < k.getX())
        {
            x -= 30  * difficultyMultiplier;
            health -=d;
        }
        if(k.getDirection() == 10 && y < k.getY())
        {
            y -= 30  * difficultyMultiplier;
            health -=d;
        }
        if(k.getDirection() == 12&& y > k.getY())
        {
            y += 30  * difficultyMultiplier;
            health -=d;
        }

    }
    public int getHealth()
    {
        return health;
    }
    public void setHealth(int h)
    {
        health = h;
    }
    public void setDifficultyMultiplier(double num)
    {
        difficultyMultiplier = num;
    }
    public double getDifficultyMultiplier()
    {
        return difficultyMultiplier;
    }
    private int compareXPos(Knight k)
    {
        if(k.getX() > x)
        {
            //knight is on right
            return 0;
        }else
        {
            //knight is on left
            return 1;
        }
    }
    public void attackPlayer(Knight k)
    {
        k.takeDamage(1);
    }
    private double distance(int x1, int x2, int y1, int y2)
    {
        double ans = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
        return ans;
    }
    
    public void pathfinding(Knight k, Enemy[] allEnemies)
    {
        moveCooldown = System.currentTimeMillis();
        boolean touching = false;
        if(moveCooldown - moveStart >= 1)
        {
            moveStart = moveCooldown;
            cooldownTime--;
        }
        if(cooldownTime <= 0)
        {
            cooldownTime = 100;
        }
        if(Math.abs(k.getX() - x) > 32 || Math.abs(k.getY() - y) > 32)
        {
           attacking = false;
           if(k.getX() != x)
            {
                if(x < k.getX())
                {
                    vx = 1;
                }
                else if(x > k.getX())
                {
                    vx = -1;
                }
            }
           else
           {
               vx = 0;
           }
            if(k.getY() != y)
            {
                if(y < k.getY())
                {
                    vy = 1;
                }
                else if(y > k.getY())
                {
                   vy = -1;
                }
            }
            else
           {
               vy = 0;
           }
        }
        else
        {
            vx = 0;
            vy = 0;
            attacking = true;
            if(this.compareXPos(k) == 0)
            {
                attackDirection = 0;
            }
            else
            {
                attackDirection = 1;
            }
        }
        
        for(int i= 0; i < allEnemies.length; i++)
        {
            Enemy currE = allEnemies[i];
            if(currE != this) //not me
            {
                int myRad = getDiam()/2;
                int currERad = currE.getDiam()/2;
                int nextX = x + vx;
                int nextY = y + vy;
                
                if(distance(nextX, currE.getX(), nextY,currE.getY()) <= myRad )
                {
//                    vx = 0;
//                    vy = 0;
                    if(cooldownTime >= 0)
                    {
                        if(direction == 1)
                        {
                            vy = -1;
                        }
                        else if(direction == 2)
                        {
                            vx = 1;
                        }
                        else if(direction == 3)
                        {
                            vy = 1;
                        }
                        else if(direction == 4)
                        {
                            vx = -1;
                        }
                    }
                }
            }
        }
        
        x += vx * difficultyMultiplier;
        y += vy * difficultyMultiplier;
        
    }
    public void DrawSelf(Graphics2D g)
    {
        currTime = System.currentTimeMillis() - startTime;
        long index = currTime%500/100;
        
        //displaying enemy health
        g.setColor(Color.red);
        Font eFont = new Font("Arial", Font.PLAIN, 12);
        g.setFont(eFont);
              
      
        g.drawString(getHealth() + "/2", getX() + 25,getY()+ 20);
        
        //walk animations
        if(!attacking)
        {
            if(vx < 0)
            {
                g.drawImage(goblinWalkL[(int)index], x, y, diam, diam, null);
                lastDirection = 1;
            }
            else if(vx > 0)
            {
                g.drawImage(goblinWalkR[(int)index], x, y, diam, diam, null);
                lastDirection = 2;
            }
            else
            {
                g.drawImage(goblinWalkR[(int)index], x, y, diam, diam, null);
                if(vy > 0)
                {
                    lastDirection = 3;
                }
                else
                {
                    lastDirection = 4;
                }
            }
        }
        
        if(attacking)
        {
            if(lastDirection == 1)
            {
                g.drawImage(goblinAtkL[(int)index], x, y, diam, diam, null);
            }
            if(lastDirection == 2)
            {
                g.drawImage(goblinAtkR[(int)index], x, y, diam, diam, null);
            }
            if(lastDirection == 3 || lastDirection == 4)
            {
                if(attackDirection == 0)
                {
                    g.drawImage(goblinAtkR[(int)index], x, y, diam, diam, null);
                }
                else if(attackDirection == 1)
                {
                    g.drawImage(goblinAtkL[(int)index], x, y, diam, diam, null);
                } 
            }
        }
    }
    public void respawn()
    {
        direction = (int)(Math.random()* ((4 - 1) + 1)) + 1;
    }
}