package main.entities;

import java.awt.*;

public abstract class Entity {
    public int x, y;
    public int speed;
    public int health;
    public int height, width;

    public String direction = "";
    /**
     * that's doing magic
     *
     * */
    abstract public void update();

    /**
     * @param g2 graphic object
     *
     * */
    abstract public void draw(Graphics2D g2);

    public static class Directions {
        public static String UP = "UP";
        public static String DOWN = "DOWN";
        public static String LEFT = "LEFT";
        public static String RIGHT = "RIGHT";
    }

}
