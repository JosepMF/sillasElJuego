package main.entities;

import java.awt.*;

public class Bullet extends Entity {
    Player p;
    public Rectangle r;

    public Bullet(Player p) {
        this.p = p;
        this.speed = 10;

        this.direction = p.direction;

        this.x = p.x + p.width / 2;
        this.y = p.y + p.height / 2;

        this.height = this.width = 5;

        this.r = new Rectangle(x, y, width, height);
    }

    @Override
    public void update() {
        if (direction.equals(Directions.UP)) {
            y -= speed;
        }
        if (direction.equals(Directions.DOWN)) {
            y += speed;
        }
        if (direction.equals(Directions.LEFT)) {
            x -= speed;
        }
        if (direction.equals(Directions.RIGHT)) {
            x += speed;
        }

        r.setLocation(new Point(x, y));
    }

    @Override
    public void draw(Graphics2D g2) {
        g2.setColor(Color.RED);
        g2.fillRect(x, y, width, height);
    }
}
