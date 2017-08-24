package com.maspain.wizard.tools;

import com.maspain.wizard.Game;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class BufferedImageLoader {
    
    private BufferedImageLoader() {}
    
    public static BufferedImage loadImage(String path) {
        BufferedImage image = null;
        try {
            image = ImageIO.read(Game.class.getResource(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }
    
}