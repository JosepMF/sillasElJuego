package main.entities;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class Player extends Entity {
    GamePanel gp;
    public ArrayList<Bullet> bullets = new ArrayList<>();
    public int points;

    int i;

    public Rectangle r;

    public Player(GamePanel gp) {
        this.gp = gp;
        this.speed = 7;
        this.health = 1000;
        this.height = this.width = gp.tileSize;

        this.r = new Rectangle(x,y,width,height);
    }

    // move method
    public void move() {
        if (gp.keyController.up) {
            direction = Directions.UP;
            y -= speed;
        }

        if (gp.keyController.down) {
            direction = Directions.DOWN;
            y += speed;
        }

        if (gp.keyController.left) {
            direction = Directions.LEFT;
            x -= speed;
        }

        if (gp.keyController.right) {
            direction = Directions.RIGHT;
            x += speed;

        }

        if (gp.keyController.shoot) {
            i++;
            while (i >= 15) {
                Bullet b = new Bullet(this);
                bullets.add(b);
                System.out.println(i);
                i = 0;
            }
        }

        r.setLocation(x,y);
    }

    @Override
    public void update() {
        move();
    }

    @Override
    public void draw(Graphics2D g2) {
        try {
            g2.drawImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("./gun_man.png"))), x, y, width, height, null);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
