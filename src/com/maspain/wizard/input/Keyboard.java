package com.maspain.wizard.input;

import com.maspain.wizard.Handler;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Keyboard extends KeyAdapter {
    
    private static Keyboard instance = new Keyboard();
    public static Keyboard getInstance() { return instance; }
    
    private Keyboard() {}
    
    @Override
    public void keyPressed(KeyEvent event) {
        updateHandler(event, true);
    }
    
    @Override
    public void keyReleased(KeyEvent event) {
        updateHandler(event, false);
    }
    
    private void updateHandler(KeyEvent event, boolean isPressed) {
        int key = event.getKeyCode();
        Handler handler = Handler.getInstance();
        switch (key) {
            case KeyEvent.VK_W: handler.setUp(isPressed);    break;
            case KeyEvent.VK_S: handler.setDown(isPressed);  break;
            case KeyEvent.VK_A: handler.setLeft(isPressed);  break;
            case KeyEvent.VK_D: handler.setRight(isPressed); break;
            default: break;
        }
    }
    
}