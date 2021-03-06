package com.maspain.wizard.object;

import com.maspain.wizard.ID;
import com.maspain.wizard.graphics.SpriteSheet;
import com.maspain.wizard.tools.SpriteSheetCoordinatesLoader;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Crate extends GameObject {
    
    private static final String SPRITE_NAME = "crate";
    private static final Point SPRITE_SHEET_COORDINATES = SpriteSheetCoordinatesLoader.getCoordinates(SPRITE_NAME);
    private static final Rectangle BOUNDS = SpriteSheet.DEFAULT_BOUNDS;
    private static final BufferedImage SPRITE = SpriteSheet.DEFAULT_SPRITE_SHEET.extractImage(SPRITE_SHEET_COORDINATES, BOUNDS);
    
    public static final int AMMO_CAPACITY = 10;
    
    public Crate(int x, int y) {
        super(x, y, ID.Crate, BOUNDS, SPRITE);
    }
    
}