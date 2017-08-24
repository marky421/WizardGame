package com.maspain.wizard.object;

import com.maspain.wizard.Handler;
import com.maspain.wizard.ID;
import com.maspain.wizard.graphics.Animation;
import com.maspain.wizard.graphics.SpriteSheet;
import com.maspain.wizard.tools.SpriteSheetCoordinatesLoader;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Iterator;
import java.util.Random;

public class Enemy extends GameObject {
    
    private static final Rectangle BOUNDS = SpriteSheet.DEFAULT_BOUNDS;
    
    private static final String SPRITE_NAME_PREFIX = "enemy";
    private static final int NUM_SPRITES = 3;
    private static final BufferedImage[] SPRITES = new BufferedImage[NUM_SPRITES];
    
    private static final int ANIMATION_SPEED = 3;
    private static final int DEFAULT_HP = 100;
    
    private Random random = new Random();
    private Animation animation;
    private int hp = 0;
    
    static {
        for (int i = 0; i < NUM_SPRITES; i++) {
            Point coordinates = SpriteSheetCoordinatesLoader.getCoordinates(SPRITE_NAME_PREFIX + i);
            SPRITES[i] = SpriteSheet.DEFAULT_SPRITE_SHEET.extractImage(coordinates, BOUNDS);
        }
    }
    
    public Enemy(int x, int y) {
        super(x, y, ID.Enemy, BOUNDS);
        this.animation = new Animation(ANIMATION_SPEED, SPRITES);
        this.hp = DEFAULT_HP;
    }
    
    public void tick() {
        /*
        move();
        
        int rand = random.nextInt(10);
    
        Handler handler = Handler.getInstance();
        
        for (Iterator<GameObject> it = handler.iterator(); it.hasNext();) {
            GameObject gameObject = it.next();
            
            switch (gameObject.getId()) {
                case Block:
                    if (intersects(gameObject)) {
                        setVelocityX(-getVelocityX());
                        setVelocityY(-getVelocityY());
                        setX(getX() + (getVelocityX() * 5));
                        setY(getY() + (getVelocityY() * 5));
                    } else if (rand == 0) {
                        setVelocityX(random.nextInt(8) - 4);
                        setVelocityY(random.nextInt(8) - 4);
                    }
                    break;
                case Bullet:
                    if (intersects(gameObject)) {
                        hp -= Bullet.DAMAGE;
                        it.remove();
                    }
            }
        }
        
        // from example
        /*
        for (int i = 0; i < handler.object.size(); i++){
            GameObject tempObject = handler.object.get(i);
            
            if(tempObject.getId() == ID.Block){
                if(getBoundsBig().intersects(tempObject.getBounds())){
                    x += (velX*5) * -1;
                    y += (velY*5) * -1;
                    velX *= -1;
                    velY *= -1;
                }else if(choose == 0){
                    velX =(r.nextInt(4 - -4) + -4);
                    velY =(r.nextInt(4 - -4) + -4);
                }
            }
            
            if(tempObject.getId() == ID.Bullet){
                if(getBounds().intersects(tempObject.getBounds())){
                    hp -= 50;
                    handler.removeObject(tempObject);
                }
                
            }
        }
        */
        // end example
        /*
        animation.runAnimation();
        
        if (hp <= 0) {
            handler.removeObject(this);
        }
        */
    }
    
    public void render(Graphics g) {
        if (!isMoving()) {
            g.drawImage(SPRITES[0], getX(), getY(), null);
        } else {
            animation.drawAnimation(g, getX(), getY(), 0);
        }
    }
    
}