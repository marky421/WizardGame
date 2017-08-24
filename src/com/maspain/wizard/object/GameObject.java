package com.maspain.wizard.object;

import com.maspain.wizard.ID;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Point2D.Float;
import java.awt.image.BufferedImage;
import java.util.UUID;

public abstract class GameObject {
    
    private UUID uuid;
    
    protected Point position;
    protected Float velocity;
    protected Rectangle bounds;
    protected ID id;
    protected BufferedImage sprite;
    
    public GameObject(int x, int y, ID id, int width, int height) {
        this(x, y, id, new Rectangle(width, height), null);
    }
    
    public GameObject(int x, int y, ID id, Rectangle bounds) {
        this(x, y, id, bounds, null);
    }
    
    public GameObject(int x, int y, ID id, Rectangle bounds, BufferedImage sprite) {
        this.uuid = UUID.randomUUID();
        this.position = new Point(x, y);
        this.velocity = new Float(0f, 0f);
        this.id = id;
        this.bounds = bounds;
        this.sprite = sprite;
    }
    
    public void tick() {}
    
    public void render(Graphics g) {
        g.drawImage(sprite, position.x, position.y, null);
    }
    
    public void move() {
        setX(getX() + getVelocityX());
        setY(getY() + getVelocityY());
    }
    
    public boolean isA(ID id) {
        return this.id == id;
    }
    
    public boolean isA(ID... ids) {
        for (ID id : ids) {
            if (this.id == id) {
                return true;
            }
        }
        return false;
    }
    
    public boolean intersects(GameObject gameObject) {
        return getBounds().intersects(gameObject.getBounds());
    }
    
    public boolean isMoving() {
        return !(getVelocityX() == 0f && getVelocityY() == 0f);
    }
    
    public Point getPosition() {
        return this.position;
    }
    
    public int getX() {
        return this.position.x;
    }
    
    public int getY() {
        return this.position.y;
    }
    
    public void setPosition(Point position) {
        this.position.setLocation(position);
    }
    
    public void setX(int x) {
        this.position.x = x;
    }
    
    public void setY(int y) {
        this.position.y = y;
    }
    
    public void setX(float x) {
        this.position.x = (int) x;
    }
    
    public void setY(float y) {
        this.position.y = (int) y;
    }
    
    public Float getVelocity() {
        return this.velocity;
    }
    
    public float getVelocityY() {
        return this.velocity.y;
    }
    
    public float getVelocityX() {
        return this.velocity.x;
    }
    
    public void setVelocity(Float velocity) {
        this.velocity.setLocation(velocity);
    }
    
    public void setVelocityX(float x) {
        this.velocity.x = x;
    }
    
    public void setVelocityY(float y) {
        this.velocity.y = y;
    }
    
    public Rectangle getBounds() {
        return this.bounds;
    }
    
    public void setBounds(Rectangle bounds) {
        this.bounds.setBounds(bounds);
    }
    
    public int getWidth() { return (int) this.bounds.getWidth(); }
    
    public int getHeight() { return (int) this.bounds.getHeight(); }
    
    public ID getId() {
        return this.id;
    }
    
    public void setId(ID id) {
        this.id = id;
    }
    
    public UUID getUUID() {
        return this.uuid;
    }
    
}