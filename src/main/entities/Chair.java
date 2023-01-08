package main.entities;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class Chair extends Entity {
    GamePanel gp;
    Player p;
    public Rectangle r;
    public int damage = 5;

    public Chair(GamePanel gp, Player p, Point point) {
        this.gp = gp;
        this.p = p;
        this.health = 10;
        this.width = this.height = (int)(this.gp.tileSize / 1.01);
        this.speed = 3;
        this.x = (int)point.getX();
        this.y = (int)point.getY();

        this.r = new Rectangle(x, y, width, height);
    }

    @Override
    public void update() {
        if(p.x > x) {
            x += speed;
        }
        if(p.x < x) {
            x -= speed;
        }
        if(p.y > y) {
            y += speed;
        }
        if(p.y < y) {
            y -= speed;
        }

        r.setLocation(new Point(x, y));
    }

    @Override
    public void draw(Graphics2D g2) {
        try {
            g2.drawImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("./chair.png"))), x, y, width, height, null);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        g2.setColor(Color.RED);
        g2.drawString(((health*100)/10) + "%", x, y - height + 5);
    }
}
