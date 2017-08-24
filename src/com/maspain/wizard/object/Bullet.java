package com.maspain.wizard.object;

import com.maspain.wizard.Handler;
import com.maspain.wizard.ID;
import java.awt.Color;
import java.awt.Graphics;

public class Bullet extends GameObject {
    
    private static final int WIDTH  = 8;
    private static final int HEIGHT = 8;
    
    public static final int DAMAGE = 50;
    
    public Bullet(int x, int y, int mx, int my) {
        super(x, y, ID.Bullet, WIDTH, HEIGHT);
        float vx = (mx - x) / 10;
        float vy = (my - y) / 10;
        this.setVelocityX(vx);
        this.setVelocityY(vy);
    }
    
    public void tick() {
        move();
        Handler handler = Handler.getInstance();
        if (handler.stream().anyMatch(gameObject -> isA(ID.Block) && intersects(gameObject))) {
            handler.removeObject(this);
        }
    }
    
    public void render(Graphics g) {
        g.setColor(Color.green);
        g.fillOval(getX(), getY(), getWidth(), getHeight());
    }
    
}