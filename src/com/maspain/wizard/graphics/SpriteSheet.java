package com.maspain.wizard.graphics;

import com.maspain.wizard.tools.BufferedImageLoader;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class SpriteSheet {
    
    public static final int DEFAULT_TILE_WIDTH  = 32;
    public static final int DEFAULT_TILE_HEIGHT = 32;
    public static final String DEFAULT_SPRITE_SHEET_PATH             = "/sprite_sheet.png";
    public static final String DEFAULT_SPRITE_SHEET_COORDINATES_PATH = "/sprite.coordinates.properties";
    
    public static final Rectangle DEFAULT_BOUNDS = new Rectangle(DEFAULT_TILE_WIDTH, DEFAULT_TILE_HEIGHT);
    
    public static final SpriteSheet DEFAULT_SPRITE_SHEET = new SpriteSheet(DEFAULT_TILE_WIDTH, DEFAULT_TILE_HEIGHT, DEFAULT_SPRITE_SHEET_PATH);
    
    private int tileWidth;
    private int tileHeight;
    private BufferedImage sheet;
    
    public SpriteSheet(int tileWidth, int tileHeight, String path) {
        this(tileWidth, tileHeight, BufferedImageLoader.loadImage(path));
    }
    
    public SpriteSheet(int tileWidth, int tileHeight, BufferedImage sheet) {
        this.tileWidth  = tileWidth;
        this.tileHeight = tileHeight;
        this.sheet      = sheet;
    }
    
    public BufferedImage extractImage(Point coordinates) {
        return extractImage(coordinates, DEFAULT_BOUNDS);
    }
    
    public BufferedImage extractImage(Point coordinates, Rectangle bounds) {
        return extractImage(coordinates, bounds.width, bounds.height);
    }
    
    public BufferedImage extractImage(Point coordinates, int width, int height) {
        return extractImage(coordinates.x, coordinates.y, width, height);
    }
    
    public BufferedImage extractImage(int col, int row, int width, int height) {
        return sheet.getSubimage(col * tileWidth - tileWidth, row * tileHeight - tileHeight, width, height);
    }
    
}