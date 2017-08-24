package com.maspain.wizard.graphics;

import com.maspain.wizard.Game;
import com.maspain.wizard.object.GameObject;
import java.awt.Rectangle;
import java.awt.geom.Point2D.Float;

public class Camera {
    
    private static final float TWEENING_FACTOR = 0.05f;
    
    private Float position;
    private Rectangle bounds;
    private GameObject player;
    
    public Camera(float x, float y, GameObject player) {
        this.position = new Float(x, y);
        this.player = player;
        this.bounds = new Rectangle(Game.WIDTH + player.getWidth(), Game.HEIGHT + player.getHeight());
    }
    
    public void tick() {
        this.position.x += (player.getX() - position.x - Game.HALF_WIDTH) * TWEENING_FACTOR;
        this.position.y += (player.getY() - position.y - Game.HALF_HEIGHT) * TWEENING_FACTOR;
        
        checkBounds();
    }
    
    private void checkBounds() {
        if      (position.x <= bounds.getMinX()) position.x = (float) bounds.getMinX();
        else if (position.x >= bounds.getMaxX()) position.x = (float) bounds.getMaxX();
        else if (position.y <= bounds.getMinY()) position.y = (float) bounds.getMinY();
        else if (position.y >= bounds.getMaxY()) position.y = (float) bounds.getMaxY();
    }
    
    public float getX() {
        return this.position.x;
    }
    
    public float getY() {
        return this.position.y;
    }
    
    public void setX(float x) {
        this.position.x = x;
    }
    
    public void setY(float y) {
        this.position.y = y;
    }
    
}