package com.maspain.wizard;

import com.maspain.wizard.object.GameObject;
import java.awt.Graphics;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class Handler implements Iterable<GameObject> {
    
    private static Handler instance = new Handler();
    public static Handler getInstance() { return instance; }
    
    private Handler() {}
    
    private List<GameObject> objects = new LinkedList<>();
    
    private GameObject player = null;
    
    private boolean up    = false;
    private boolean down  = false;
    private boolean left  = false;
    private boolean right = false;
    
    public void tick() {
        Iterator<GameObject> it = this.objects.iterator();
        while (it.hasNext()) {
            GameObject gameObject = it.next();
            //System.out.println(gameObject.getUUID());
            gameObject.tick();
        }
    }
    
    public void render(Graphics g) {
        this.objects.forEach(o -> o.render(g));
    }
    
    public void addObject(GameObject object) {
        this.objects.add(object);
    }
    
    public void removeObject(GameObject object) {
        this.objects.remove(object);
    }
    
    public void clearObjects() { objects.clear(); }
    
    public void addPlayer(GameObject object) {
        addObject(object);
        setPlayer(object);
    }
    
    public GameObject getPlayer() {
        return this.player;
    }
    
    public void setPlayer(GameObject player) {
        this.player = player;
    }
    
    public boolean isUp() {
        return this.up;
    }
    
    public boolean isDown() {
        return this.down;
    }
    
    public boolean isLeft() {
        return this.left;
    }
    
    public boolean isRight() {
        return this.right;
    }
    
    public void setUp(boolean up) {
        this.up = up;
    }
    
    public void setDown(boolean down) {
        this.down = down;
    }
    
    public void setLeft(boolean left) {
        this.left = left;
    }
    
    public void setRight(boolean right) {
        this.right = right;
    }
    
    @Override
    public Iterator<GameObject> iterator() {
        return this.objects.iterator();
    }
    
    @Override
    public void forEach(Consumer<? super GameObject> action) {
        this.objects.forEach(action);
    }
    
    public Stream<GameObject> stream() {
        return this.objects.stream();
    }
    
}