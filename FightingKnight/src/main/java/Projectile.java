//Ryan Alli

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;
import java.awt.Graphics;
public class Projectile
{
    private int x, y, vx, vy, diam;
    private long startTime, currTime, actionTime, currentTime;
    private boolean shot;
    private Image[] animation;
    
    public Projectile(int xpos, int ypos)
    {
        x = xpos;
        y = ypos;
        vx = 0;
        vy = 0;
        diam = 32;
        shot = true;
        startTime = System.currentTimeMillis();
        actionTime = 0;
        animation = new Image[5];
        
        ImageIcon bullet = new ImageIcon(Projectile.class.getResource("ProjectileAnimations/BossProjectile1.png"));
        animation[0] = bullet.getImage();
        bullet = new ImageIcon(Projectile.class.getResource("ProjectileAnimations/BossProjectile2.png"));
        animation[1] = bullet.getImage();
        bullet = new ImageIcon(Projectile.class.getResource("ProjectileAnimations/BossProjectile3.png"));
        animation[2] = bullet.getImage();
        bullet = new ImageIcon(Projectile.class.getResource("ProjectileAnimations/BossProjectile4.png"));
        animation[3] = bullet.getImage();
        bullet = new ImageIcon(Projectile.class.getResource("ProjectileAnimations/BossProjectile5.png"));
        animation[4] = bullet.getImage();
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
    
    public void DrawSelf(Graphics2D g)
    {
        currTime = System.currentTimeMillis() - startTime;
        long index = currTime%500/100;
        
        g.drawImage(animation[(int)index], x, y, diam, diam, null);
    }
    
    public void movement()
    {
        if(y < 500)
        {
            vy = 2;
        }
        x += vx;
        y += vy;
    }
    
    
    private double distance(int x1, int x2, int y1, int y2)
    {
        double ans = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
        return ans;
    }
    public void respawn()
    {
        currentTime = System.currentTimeMillis();
        //boss attack cooldown
        if(currentTime - actionTime >= 500)
        {
            actionTime = currentTime;
            if(y > 500)
            {
                y = -50;
                x = (int)(Math.random()* ((990 - 10) + 10)) + 10;
            }
        }
    }
}