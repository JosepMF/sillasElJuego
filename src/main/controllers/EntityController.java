package main.controllers;

import main.GameFrame;
import main.GamePanel;
import main.entities.Bullet;
import main.entities.Chair;
import main.entities.Player;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class EntityController {
    public Player player;
    public ArrayList<Chair> chairs = new ArrayList<Chair>();
    GamePanel gp;

    public EntityController(GamePanel gp) {
        this.gp = gp;
        this.player = new Player(gp);
    }

    // test
    public void generateChairs() {
        if (chairs.isEmpty()) {
            chairs.add(new Chair(gp, player, new Point(
                    (int) Math.floor(Math.random() * (gp.WIDTH + 1)),
                    (int) Math.floor(Math.random() * (gp.HEIGHT + 1))
            )));
        }
    }

    public void update() {
        generateChairs();
        player.update();
        if (!chairs.isEmpty()) {
            chairs.forEach(Chair::update);
        }
        if (!player.bullets.isEmpty()) {
            player.bullets.forEach(Bullet::update);
        }
    }

    public void draw(Graphics2D g2) {
        player.draw(g2);
        if (!chairs.isEmpty()) {
            chairs.forEach(chair -> {
                chair.draw(g2);
            });
        }
        if (!player.bullets.isEmpty()) {
            player.bullets.forEach(bullet -> {
                bullet.draw(g2);
            });
        }
    }
}
