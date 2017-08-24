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

public class Wizard extends GameObject {
    
    private static final int WIDTH  = 32;
    private static final int HEIGHT = 48;
    private static final Rectangle BOUNDS = new Rectangle(WIDTH, HEIGHT);
    
    private static final float SPEED = 5f;
    
    private static final String SPRITE_NAME_PREFIX = "wizard";
    private static final int NUM_SPRITES = 3;
    private static final BufferedImage[] SPRITES = new BufferedImage[NUM_SPRITES];
    
    private static final int ANIMATION_SPEED = 3;
    private static final int DEFAULT_AMMO = 100;
    private static final int DEFAULT_HP = 100;
    
    private Animation animation;
    private int ammo = 0;
    private int hp = 0;
    
    static {
        for (int i = 0; i < NUM_SPRITES; i++) {
            Point coordinates = SpriteSheetCoordinatesLoader.getCoordinates(SPRITE_NAME_PREFIX + i);
            SPRITES[i] = SpriteSheet.DEFAULT_SPRITE_SHEET.extractImage(coordinates, BOUNDS);
        }
    }
    
    public Wizard(int x, int y) {
        super(x, y, ID.Player, BOUNDS);
        this.animation = new Animation(ANIMATION_SPEED, SPRITES);
        this.ammo = DEFAULT_AMMO;
        this.hp = DEFAULT_HP;
    }
    
    public void tick() {
        move();
        
        handleCollisions();
        
        Handler handler = Handler.getInstance();
        
        // up and down
        if      (handler.isUp() && handler.isDown()) setVelocityY(0f);
        else if (handler.isUp())                     setVelocityY(-SPEED);
        else if (handler.isDown())                   setVelocityY(SPEED);
        else                                         setVelocityY(0f);
        
        // left and right
        if      (handler.isLeft() && handler.isRight()) setVelocityX(0f);
        else if (handler.isLeft())                      setVelocityX(-SPEED);
        else if (handler.isRight())                     setVelocityX(SPEED);
        else                                            setVelocityX(0f);
        
        animation.runAnimation();
    }
    
    public void render(Graphics g) {
        if (!isMoving()) {
            g.drawImage(SPRITES[0], getX(), getY(), null);
        } else {
            animation.drawAnimation(g, getX(), getY(), 0);
        }
    }
    
    private void handleCollisions() {
        Handler handler = Handler.getInstance();
        for (Iterator<GameObject> it = handler.iterator(); it.hasNext();) {
            GameObject gameObject = it.next();
            if (intersects(gameObject)) {
                switch (gameObject.getId()) {
                    case Block:
                        setX(getX() - getVelocityX());
                        setY(getY() - getVelocityY());
                        break;
                    case Crate:
                        ammo += Crate.AMMO_CAPACITY;
                        //it.remove();
                        break;
                    case Enemy:
                        hp--;
                        break;
                    default:
                        break;
                }
            }
        }
    }
    
    public void shoot(int mx, int my) {
        Handler handler = Handler.getInstance();
        int x = getX() + (getWidth() / 2);
        int y = getY() + (getHeight() / 2);
        handler.addObject(new Bullet(x, y, mx, my));
    }
    
    public int getAmmo() {
        return this.ammo;
    }
    
    public int getHp() {
        return this.hp;
    }
    
    public void setAmmo(int ammo) {
        this.ammo = ammo;
    }
    
    public void setHp(int hp) {
        this.hp = hp;
    }
    
}