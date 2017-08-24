package com.maspain.wizard.tools;

import com.maspain.wizard.Game;
import com.maspain.wizard.graphics.SpriteSheet;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.function.Function;
import java.util.stream.Collectors;

public class SpriteSheetCoordinatesLoader {
    
    private static Map<String, Properties> spriteSheetCoordinatesStore = new HashMap<>();
    
    static {
        load(SpriteSheet.DEFAULT_SPRITE_SHEET_PATH, SpriteSheet.DEFAULT_SPRITE_SHEET_COORDINATES_PATH);
    }
    
    public static void load(String spriteSheetName, String fileName) {
        try {
            Properties p = new Properties();
            p.load(Game.class.getResourceAsStream(fileName));
            
            spriteSheetCoordinatesStore.put(spriteSheetName, p);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static Map<String, Point> getAllCoordinatesForSpriteSheet(String spriteSheetName) {
        Properties p = spriteSheetCoordinatesStore.get(spriteSheetName);
        return p.stringPropertyNames().stream().collect(Collectors.toMap(Function.identity(), s -> toPoint(p.getProperty(s))));
    }
    
    public static Point getCoordinates(String spriteName) {
        return getCoordinates(spriteName, SpriteSheet.DEFAULT_SPRITE_SHEET_PATH);
    }
    
    public static Point getCoordinates(String spriteName, String spriteSheetName) {
        Properties p = spriteSheetCoordinatesStore.get(spriteSheetName);
        return toPoint(p.getProperty(spriteName));
    }
    
    private static Point toPoint(String pointString) {
        String[] coordinates = pointString.split(",");
        return new Point(Integer.parseInt(coordinates[0]), Integer.parseInt(coordinates[1]));
    }
    
}