package com.maspain.wizard;

import com.maspain.wizard.graphics.SpriteSheet;
import com.maspain.wizard.object.Block;
import com.maspain.wizard.object.Crate;
import com.maspain.wizard.object.Enemy;
import com.maspain.wizard.object.Wizard;
import java.awt.Color;
import java.awt.image.BufferedImage;

public class Level {
    
    public static final String LEVEL_1 = "/wizard_level.png";
    
    private Level() {}
    
    public static void loadLevelFromImage(BufferedImage image) {
        int width  = image.getWidth();
        int height = image.getHeight();
        
        Handler handler = Handler.getInstance();
        
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Color color = new Color(image.getRGB(x, y));
                
                int xx = x + SpriteSheet.DEFAULT_TILE_WIDTH;
                int yy = y + SpriteSheet.DEFAULT_TILE_HEIGHT;
                
                // TODO: add objects here
                if      (color.equals(Color.red))   handler.addObject(new Block(xx, yy));
                else if (color.equals(Color.blue))  handler.addPlayer(new Wizard(xx, yy));
                else if (color.equals(Color.green)) handler.addObject(new Enemy(xx, yy));
                else if (color.equals(Color.cyan))  handler.addObject(new Crate(xx, yy));
                
            }
        }
    }
    
    public static void clearLevel() {
        Handler.getInstance().clearObjects();
    }
    
}