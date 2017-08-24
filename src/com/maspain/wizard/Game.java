package com.maspain.wizard;

import com.maspain.wizard.graphics.Camera;
import com.maspain.wizard.graphics.SpriteSheet;
import com.maspain.wizard.input.Keyboard;
import com.maspain.wizard.input.Mouse;
import com.maspain.wizard.object.Wizard;
import com.maspain.wizard.tools.BufferedImageLoader;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class Game extends Canvas implements Runnable {

    private static final long serialVersionUID = 1L;
    
    private static final String TITLE = "Wizard Game";
    
    public static final int WIDTH  = 1000;
    public static final int HEIGHT = 563;
    
    public static final int HALF_WIDTH  = WIDTH >> 1;
    public static final int HALF_HEIGHT = HEIGHT >> 1;
    
    public static final Rectangle BOUNDS = new Rectangle(WIDTH, HEIGHT);
    
    private boolean isRunning = false;
    private Thread thread;
    
    private BufferedImage floor = null;
    
    private Camera camera;
    
    private Game() {
        initWindow(WIDTH, HEIGHT, TITLE);
    
        Level.loadLevelFromImage(BufferedImageLoader.loadImage(Level.LEVEL_1));
        
        this.floor = SpriteSheet.DEFAULT_SPRITE_SHEET.extractImage(new Point(4, 2));
        this.camera = new Camera(0f, 0f, Handler.getInstance().getPlayer());
        
        this.addKeyListener(Keyboard.getInstance());
        this.addMouseListener(new Mouse(camera));
        
    }
    
    private void initWindow(int width, int height, String title) {
        JFrame frame = new JFrame(title);
        
        Dimension dimension = new Dimension(width, height);
    
        frame.setPreferredSize(dimension);
        frame.setMaximumSize(dimension);
        frame.setMinimumSize(dimension);
    
        frame.add(this);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    
    public void run() {
        this.requestFocus();
        
        double ticks = 60.0;
        double ns = 1000000000 / ticks;
        double delta = 0.0;
        long timer = System.currentTimeMillis();
        int frames = 0;
    
        long lastTime = System.nanoTime();
        while (isRunning) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            
            while (delta >= 1) {
                tick();
                delta--;
            }
            
            render();
            frames++;
            
            if (System.currentTimeMillis() - timer > 1000) {
                System.out.println("FPS: " + frames);
                timer += 1000;
                frames = 0;
            }
        }
        
        stop();
    }
    
    private void tick() {
        camera.tick();
        Handler.getInstance().tick();
    }
    
    private void render() {
        Handler handler = Handler.getInstance();
        Wizard player = (Wizard) handler.getPlayer();
    
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }
    
        Graphics2D g = (Graphics2D) bs.getDrawGraphics();
    
        //------------------------------------------------------------------------------------------
    
        g.translate(-camera.getX(), -camera.getY());
    
        for (int xx = 0; xx < 30 * 72; xx += 32) {
            for (int yy = 0; yy < 30 * 72; yy += 32) {
                g.drawImage(floor, xx, yy, null);
            }
        }
    
        handler.render(g);
    
        g.translate(camera.getX(), camera.getY());
    
        g.setColor(Color.gray);
        g.fillRect(5, 5, 200, 32);
        g.setColor(Color.green);
        g.fillRect(5, 5, player.getHp() * 2, 32);
        g.setColor(Color.black);
        g.drawRect(5, 5, 200, 32);
    
        g.setColor(Color.white);
        g.drawString("Ammo: " + player.getAmmo(), 5, 50);
        
        //------------------------------------------------------------------------------------------
        
        g.dispose();
        bs.show();
    }
    
    private void start() {
        isRunning = true;
        thread = new Thread(this);
        thread.start();
    }
    
    private void stop() {
        isRunning = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        new Game().start();
    }
    
}