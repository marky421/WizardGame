package com.maspain.wizard.input;

import com.maspain.wizard.graphics.Camera;
import com.maspain.wizard.Handler;
import com.maspain.wizard.object.Wizard;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Mouse extends MouseAdapter {
    
    private Camera camera;
    
    public Mouse(Camera camera) {
        this.camera = camera;
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        int mx = (int) (e.getX() + camera.getX());
        int my = (int) (e.getY() + camera.getY());
    
        Handler handler = Handler.getInstance();
        ((Wizard) handler.getPlayer()).shoot(mx, my);
    }
    
}