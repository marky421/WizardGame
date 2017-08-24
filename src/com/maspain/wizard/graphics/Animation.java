package com.maspain.wizard.graphics;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Animation {
    
    private BufferedImage[] images;
    private BufferedImage currentImage;
    
    private int speed;
    private int frames;
    private int index = 0;
    private int count = 0;
    
    public Animation(int speed, BufferedImage[] images) {
        this.speed = speed;
        this.images = images;
        this.frames = images.length;
    }
    
    public void runAnimation() {
        if (++index > speed) {
            index = 0;
            nextFrame();
        }
    }
    
    public void nextFrame() {
        currentImage = images[count];
        if (++count >= frames) {
            count = 0;
        }
    }
    
    public void drawAnimation(Graphics g, double x, double y, int offset) {
        g.drawImage(currentImage, (int) x - offset, (int) y, null);
    }
    
    public int getSpeed() {
        return this.speed;
    }
    
    public int getCount() {
        return this.count;
    }
    
    public void setSpeed(int speed) {
        this.speed = speed;
    }
    
    public void setCount(int count) {
        this.count = count;
    }
    
}