//Ryan Alli

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JComponent;
import javax.swing.JFrame;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
//import java.applet.*;
import java.awt.Image;
import javax.swing.ImageIcon;

public class Driver extends JComponent implements KeyListener, MouseListener, MouseMotionListener
{
    //instance variables
    private int WIDTH;
    private int HEIGHT;
    
    
    private Knight player = new Knight(468, 218);
    private Image backround;
//    private Enemy e = new Enemy(-100,100);
//    private Enemy e2 = new Enemy(1075, 50);
//    private Enemy e3 = new Enemy(500,-350);
//    private Enemy e4 = new Enemy(450, 850);
    private Enemy[] enemies;
    private Boss boss;
    private Projectile proj[];
    private boolean bossSpawned;
    
    private int playerVel;
    private int score;
    private int highScore;
    private int enemiesKilled;
    private long startTime, currTime, actionTime, currentTime, currentAttackTime, attackTime;
    private int gameState;
    private int neededKills;
    private int healthOnBossKill;
    private JButton normalMode, hardMode, nightmareMode;
    private boolean buttonCreated;
    private boolean musicPlaying;
    
    //music
    //private AudioClip startMusic;
    //private AudioClip gameMusic;
    
    
//Default Constructor
    public Driver()
    {
        enemies = new Enemy[4];
        enemies[0] =  new Enemy(-150,100);
        enemies[1] = new Enemy(1150, 50);
        enemies[2] = new Enemy(500,-350);
        enemies[3] = new Enemy(450, 850);
        boss = new Boss(400, -150);
        proj = new Projectile[15];
        proj[0] = new Projectile(500, -50);
        proj[1] = new Projectile(500, -75);
        proj[2] = new Projectile(500, -100);
        proj[3] = new Projectile(500, -125);
        proj[4] = new Projectile(500, -175);
        proj[5] = new Projectile(500, -200);
        proj[6] = new Projectile(500, -225);
        proj[7] = new Projectile(500, -250);
        proj[8] = new Projectile(500, -275);
        proj[9] = new Projectile(500, -150);
        proj[10] = new Projectile(500, -300);
        proj[11] = new Projectile(500, -325);
        proj[12] = new Projectile(500, -350);
        proj[13] = new Projectile(500, -375);
        proj[14] = new Projectile(500, -400);
        bossSpawned = false;
        healthOnBossKill = 0;
    //initializing instance variables
        WIDTH = 1000;
        HEIGHT = 500;
    //Setting up the GUI
        JFrame gui = new JFrame();
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.setTitle("The Knight That Fights");
        gui.setPreferredSize(new Dimension(WIDTH + 5, HEIGHT + 30));
        gui.setResizable(false);
        gui.getContentPane().add(this);
        gui.pack(); //Packs everything together
        gui.setLocationRelativeTo(null); //Makes so the gui opens in the center of screen
        gui.setVisible(true); //Makes the gui visible
        gui.addKeyListener(this);//stating that this object will listen to the keyboard
        gui.addMouseListener(this); //stating that this object will listen to the Mouse
        gui.addMouseMotionListener(this); //stating that this object will acknowledge when the Mouse moves
        
        actionTime = 0;
        attackTime = 0;
        startTime = System.currentTimeMillis();
        playerVel = 3;
        highScore = 0;
        gameState = 0;
        buttonCreated = false;
        neededKills = 15;
        
        //startMusic = Applet.newAudioClip(this.getClass().getResource("Music/StartScreenMusic.wav"));
        //gameMusic = Applet.newAudioClip(this.getClass().getResource("Music/GameMusic.wav"));
        
        ImageIcon backroundImg = new ImageIcon(Driver.class.getResource("Backround/Backround.png"));
        backround = backroundImg.getImage();
        
        //createStartMenuButtons();
        createNormalButton();
        createHardButton();
        createNightmareButton();
    }
//This method will acknowledge user input
    public void keyPressed(KeyEvent e)
    {
        int key = e.getKeyCode();
        if(key == KeyEvent.VK_W)
        {
            player.setVelY(-playerVel);
            player.setDirection(1);
        }
        if(key == KeyEvent.VK_A)
        {
            player.setVelX(-playerVel);
            player.setDirection(2);
        }
        if(key == KeyEvent.VK_S)
        {
            player.setVelY(playerVel);
            player.setDirection(3);
        }
        if(key == KeyEvent.VK_D)
        {
            player.setVelX(playerVel);
            player.setDirection(4);
        }
        if(key == 32)
        {
           if(player.getDirection() == 1 || player.getDirection() == 6) 
            {
                player.setDirection(10);
                player.setVelX(0);
                player.setVelY(0);
            }
           if(player.getDirection() == 2 || player.getDirection() == 7) 
            {
                player.setDirection(11);
                player.setVelX(0);
                player.setVelY(0);
            }
           if(player.getDirection() == 3 || player.getDirection() == 5) 
            {
                player.setDirection(12);
                player.setVelX(0);
                player.setVelY(0);
            }
           if(player.getDirection() == 4 || player.getDirection() == 8) 
            {
                player.setDirection(13);
                player.setVelX(0);
                player.setVelY(0);
            }
        }
    }
//All your UI drawing goes in here
    public void paintComponent(Graphics g)
    {
        g.drawImage(backround, 0, 0, WIDTH, HEIGHT, null);
        Graphics2D g2d = (Graphics2D)g;
        
        for(int i = 0; i < enemies.length; i++)
        {
            enemies[i].DrawSelf(g2d);   
        }
        for(int i = 0; i < proj.length; i++)
        {
            proj[i].DrawSelf(g2d);
        }   
        if(gameState == 1)
        {        
            if(player.getY() + player.getLength() < boss.getY() + boss.getDiam())
            {
                player.act(g2d);
                boss.DrawSelf(g2d);
            }
            else
            {
                boss.DrawSelf(g2d);
                player.act(g2d);
            }
            if(bossSpawned)
            {
                boss.spawn();
                g.setColor(Color.white);
                g.drawRect(298,478, 402, 25);
                g.setColor(Color.red);
                g.fillRect(300, 480, boss.getHealth() * 20, 20);
            }
            g.setColor(Color.white);
            Font f = new Font("Arial", Font.BOLD, 20);
            g.setFont(f);
            g.drawString("Score: " + score, 850, 40);
            g.setColor(Color.green);
            g.setFont(f);
            g.drawString("Health: " + player.getHealth(), 30, 30);
            g.setColor(Color.yellow);
            g.drawString("High Score: " + highScore, 25, 490);
        }
        else if(gameState == 0)
        {
            g.setColor(Color.white);
            Font f2 = new Font("Arial", Font.BOLD, 30);
            g.setFont(f2);
            g.drawString("The Knight That Fights", 320, 150);
            g.drawString("Controls", 100, 250);
            Font f3 = new Font("Arial", Font.BOLD, 15);
            g.setFont(f3);
            g.drawString("Move: WASD", 100, 275);
            g.drawString("Attack: Spacebar", 100, 300);
        }
        //displaying enemy health
        g.setColor(Color.red);
        Font eFont = new Font("Arial", Font.PLAIN, 12);
        g.setFont(eFont);
        
        
//        
//        g.drawString(e.getHealth() + "/2", e.getX() + 25, e.getY()+ 20);
//        g.drawString(e2.getHealth() + "/2", e2.getX() + 25, e2.getY()+ 20);
//        g.drawString(e3.getHealth() + "/2", e3.getX() + 25, e3.getY()+ 20);
//        g.drawString(e4.getHealth() + "/2", e4.getX() + 25, e4.getY()+ 20);
    }
    public void loop()
    {
        /*if(!musicPlaying)
        {
            playMusic();
        }*/
        currTime = System.currentTimeMillis() - startTime;
        currentTime = System.currentTimeMillis();
        currentAttackTime = System.currentTimeMillis();
        player.movement();
        if(gameState == 1)
        {
            for(int i = 0; i < enemies.length; i++)
            {
                enemies[i].pathfinding(player, enemies);
            }
            boss.movemnt();
        }
        int side = (int)(Math.random()* ((4 - 1) + 1)) + 1;
        
        if(currentTime - actionTime >= 500)
        {
            actionTime = currentTime;
            
            for(int i = 0; i < enemies.length; i++)
            {
                Enemy e = enemies[i];
                e.respawn();
                if(player.getIsAttacking() && detectEnemyCollision(e))
                {
                    e.takeDamage(1, player);
                    if(e.getHealth() <= 0)
                    {
                        e.setHealth(2);
                        if(side == 1)
                        {
                            e.setcords(-150, 250);
                            score += 10 * e.getDifficultyMultiplier();
                            if(!bossSpawned)
                                enemiesKilled++;
                        }
                        else if(side == 2)
                        {
                            e.setcords(1150, 250);
                            score += 10 * e.getDifficultyMultiplier();
                            if(!bossSpawned)
                                enemiesKilled++;
                        }
                        else if(side == 3)
                        {
                            e.setcords(600, 650);
                            score += 10 * e.getDifficultyMultiplier();
                            if(!bossSpawned)
                                enemiesKilled++;
                        }
                        else if(side == 4)
                        {
                            e.setcords(600, -250);
                            score += 10 * e.getDifficultyMultiplier();
                            if(!bossSpawned)
                                enemiesKilled++;
                        }
                    }
                }
            }
            
            if(detectBossCollision(boss) && player.getIsAttacking())
            {
                boss.TakeDamage(1);
            }
        }
        
        //enemy attack cooldown
        if(currentAttackTime - attackTime >= 750)
        {
            attackTime = currentAttackTime;
            for(int i = 0; i < enemies.length; i++)
            {
                Enemy e = enemies[i];
                if(e.getAttacking())
                {
                    e.attackPlayer(player);
                }
            }
            
            for(int i = 0; i < proj.length; i++)
            {
                Projectile p = proj[i];
                if(detectProjCollision(p))
                {
                    player.takeDamage(1);
                }
            }
        }
        
        if(enemiesKilled != 0 && enemiesKilled % neededKills == 0)
        {
            enemiesKilled++;
            bossSpawned = true;
        }
        if(bossSpawned)
        {
            for(int i = 0; i < proj.length; i++)
            {
                proj[i].movement();
                proj[i].respawn();
            }
            boss.moveAround();
        }
        else
        {
            proj[0] = new Projectile(500, -50);
            proj[1] = new Projectile(500, -75);
            proj[2] = new Projectile(500, -100);
            proj[3] = new Projectile(500, -125);
            proj[4] = new Projectile(500, -175);
            proj[5] = new Projectile(500, -200);
            proj[6] = new Projectile(500, -225);
            proj[7] = new Projectile(500, -250);
            proj[8] = new Projectile(500, -275);
            proj[9] = new Projectile(500, -150);
            proj[10] = new Projectile(500, -300);
            proj[11] = new Projectile(500, -325);
            proj[12] = new Projectile(500, -350);
            proj[13] = new Projectile(500, -375);
            proj[14] = new Projectile(500, -400);
        }
                    
        if(score >= highScore)
        {
            highScore = score;
        }
        if(player.getHealth() <= 0)
        {
            resetGame();
        }
        if(boss.getHealth() <= 0)
        {
            bossSpawned = false;
            boss.setHealth(50);
            boss = new Boss(400, -150);
            score += 250;
            player.setHealth(player.getHealth() + healthOnBossKill);
        }
        repaint();
    }
//These methods are required by the compiler.
//You might write code in these methods depending on your goal.
    public void keyTyped(KeyEvent e)
    {
    }
    public void keyReleased(KeyEvent e)
    {
        int key = e.getKeyCode(); 
    
        if(key == KeyEvent.VK_W)
        {
            player.setVelY(0);
            player.setDirection(6);
        }
        if(key == KeyEvent.VK_A)
        {
            player.setVelX(0);
            player.setDirection(7);
        }
        if(key == KeyEvent.VK_S)
        {
            player.setVelY(0);
            player.setDirection(5);
        }
        if(key == KeyEvent.VK_D)
        {
            player.setVelX(0);
            player.setDirection(8);
        }
//        if(key == 32)
//        {
//           //player.setIsAttacking(false);
//        }

    }
    public void mousePressed(MouseEvent e)
    {
        
    }
    public void mouseReleased(MouseEvent e)
    {
        
    }
    public void mouseClicked(MouseEvent e)
    {
        
    }
    public void mouseEntered(MouseEvent e)
    {
    }
    public void mouseExited(MouseEvent e)
    {
    }
    public void mouseMoved(MouseEvent e)
    {
    }
    public void mouseDragged(MouseEvent e)
    {
    }
    
    private double distance(int x1, int x2, int y1, int y2)
{
    double ans = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    return ans;
}
public boolean detectEnemyCollision(Enemy e)
{
    boolean output = false;
    int radius = e.getDiam() / 2;
    int nextX = e.getX() + e.getVX();
    int nextY = e.getY() + e.getVY();
    int centerX = (2 * nextX + e.getDiam())/2;
    int centerY = (2 * nextY + e.getDiam())/2;
    
    for(int xPixel = player.getX(); xPixel <= player.getX() + player.getLength(); xPixel++)
    {
        for(int yPixel = player.getY(); yPixel <= player.getY() + player.getLength(); yPixel++)
        {
            if(distance(xPixel, centerX, yPixel, centerY) < player.getLength()/2)
            {
                output = true;
            }
        }
    }
    return output;
}

public boolean detectProjCollision(Projectile p)
{
    boolean output = false;
    int radius = p.getDiam() / 2;
    int nextX = p.getX() + p.getVX();
    int nextY = p.getY() + p.getVY();
    int centerX = (2 * nextX + p.getDiam())/2;
    int centerY = (2 * nextY + p.getDiam())/2;
    
    for(int xPixel = player.getX() + 32; xPixel <= player.getX() + player.getLength() - 32; xPixel++)
    {
        for(int yPixel = player.getY() + 15; yPixel <= player.getY() + player.getLength(); yPixel++)
        {
            if(distance(xPixel, centerX, yPixel, centerY) < player.getLength() / 2)
            {
                output = true;
            }
        }
    }
    return output;
}

public boolean detectBossCollision(Boss b)
{
    boolean output = false;
    int radius = b.getDiam() / 2;
    int nextX = b.getX() + b.getVX();
    int nextY = b.getY() + b.getVY();
    int centerX = (2 * nextX + b.getDiam())/2;
    int centerY = (2 * nextY + b.getDiam())/2;
    
    for(int xPixel = player.getX(); xPixel <= player.getX() + player.getLength(); xPixel++)
    {
        for(int yPixel = player.getY(); yPixel <= player.getY() + player.getLength(); yPixel++)
        {
            if(distance(xPixel, centerX, yPixel, centerY) < player.getLength()/2)
            {
                output = true;
            }
        }
    }
    return output;
}

//reset game when over
    public void resetGame()
    {
        player = new Knight(468, 218);
        enemies = new Enemy[4];
        enemies[0] =  new Enemy(-100,100);
        enemies[1] = new Enemy(1075, 50);
        enemies[2] = new Enemy(500,-350);
        enemies[3] = new Enemy(450, 850);
    //    e = new Enemy(-100,100);
    //    e2 = new Enemy(1075, 50);
    //    e3 = new Enemy(500,-250);
    //    e4 = new Enemy(450, 650);
        actionTime = 0;
        score = 0;
        startTime = System.currentTimeMillis();
        gameState = 0;
        normalMode.setBounds(400, 290 , 200, 40);
        hardMode.setBounds(400, 330 , 200, 40);
        nightmareMode.setBounds(400, 370 , 200, 40);
        bossSpawned = false;
        enemiesKilled = 0;
        boss = new Boss(400, -150);
        proj[0] = new Projectile(500, -50);
        proj[1] = new Projectile(500, -75);
        proj[2] = new Projectile(500, -100);
        proj[3] = new Projectile(500, -125);
        proj[4] = new Projectile(500, -175);
        proj[5] = new Projectile(500, -200);
        proj[6] = new Projectile(500, -225);
        proj[7] = new Projectile(500, -250);
        proj[8] = new Projectile(500, -275);
        proj[9] = new Projectile(500, -150);
        proj[10] = new Projectile(500, -300);
        proj[11] = new Projectile(500, -325);
        proj[12] = new Projectile(500, -350);
        proj[13] = new Projectile(500, -375);
        proj[14] = new Projectile(500, -400);
        musicPlaying = false;
    }
    private void createNormalButton()
    {
        normalMode = new JButton();
        this.add(normalMode);
        normalMode.setBounds(380, 290 , 200, 40);
        normalMode.setText("Normal Mode");
        normalMode.addActionListener(new ActionListener()
        {
        //@Override
        public void actionPerformed(ActionEvent e)
        {
        // This runs when the button is clicked
            gameState = 1;
            hardMode.setBounds(1500, 250 , 200, 40);
            normalMode.setBounds(1500, 250 , 200, 40);
            nightmareMode.setBounds(1500, 250 , 200, 40);
            player.setHealth(15);
            healthOnBossKill = 5;
            for(int i = 0; i < enemies.length; i++)
            {
                enemies[i].setDifficultyMultiplier(1.0);
            }
            musicPlaying = false;
        }
    });
        normalMode.setFocusable(false);
        normalMode.setVisible(true);
    }
    private void createHardButton()
    {
        hardMode = new JButton();
        this.add(hardMode);
        hardMode.setBounds(380, 330 , 200, 40);
        hardMode.setText("Hard Mode");
        hardMode.addActionListener(new ActionListener()
        {
        //@Override
        public void actionPerformed(ActionEvent e)
        {
        // This runs when the button is clicked
            gameState = 1;
            hardMode.setBounds(1500, 250 , 200, 40);
            normalMode.setBounds(1500, 250 , 200, 40);
            nightmareMode.setBounds(1500, 250 , 200, 40);
            player.setHealth(20);
            healthOnBossKill = 7;
            for(int i = 0; i < enemies.length; i++)
            {
                enemies[i].setDifficultyMultiplier(1.5);
            }
            musicPlaying = false;
        }
    });
        hardMode.setFocusable(false);
        hardMode.setVisible(true);
    }
    private void createNightmareButton()
    {
        nightmareMode = new JButton();
        this.add(nightmareMode);
        nightmareMode.setBounds(380, 370 , 200, 40);
        nightmareMode.setText("Nighmare Mode");
        nightmareMode.addActionListener(new ActionListener()
        {
        //@Override
        public void actionPerformed(ActionEvent e)
        {
        // This runs when the button is clicked
            gameState = 1;
            hardMode.setBounds(1500, 250 , 200, 40);
            normalMode.setBounds(1500, 250 , 200, 40);
            nightmareMode.setBounds(1500, 250 , 200, 40);
            player.setHealth(25);
            healthOnBossKill = 10;
            for(int i = 0; i < enemies.length; i++)
            {
                enemies[i].setDifficultyMultiplier(2.0);
            }
            musicPlaying = false;
        }
    });
        nightmareMode.setFocusable(false);
        nightmareMode.setVisible(true);
    }
    
    /*public void playMusic()
    {
        musicPlaying = true;
        if(gameState == 0)
        {
            gameMusic.stop();
            startMusic.loop();
        }
        else
        {
            startMusic.stop();
            gameMusic.loop();
        }
    }*/
    
    public void start(final int ticks)
    {
        Thread gameThread = new Thread()
        {
            public void run()
            {
                while(true)
                {
                    loop();
                    try{
                        Thread.sleep(1000 / ticks);
                    }
                    catch(Exception e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        };
        gameThread.start();
    }
    public static void main(String[] args)
    {
        Driver g = new Driver();
        g.start(60);
    }
}