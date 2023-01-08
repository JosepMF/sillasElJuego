package main.controllers;

import main.GamePanel;
import main.entities.Bullet;
import main.entities.Chair;
import main.entities.Player;

import java.util.ArrayList;

public class CollisionController {
    GamePanel gp;
    EntityController entityController;
    Player player;
    ArrayList<Bullet> bullets;
    ArrayList<Chair> chairs;

    public CollisionController(GamePanel gp) {
        this.gp = gp;
        this.entityController = gp.entityController;
        this.player = entityController.player;
        this.bullets = entityController.player.bullets;
        this.chairs = entityController.chairs;
    }

    public void controller() {
        bulletsCollisionWithTheBorder();
        bulletsImpactInAChair();
        chairsCollisionWithPlayer();
    }

    public void bulletsCollisionWithTheBorder() {
        player.bullets.removeIf(bullet -> (bullet.y < 0 || bullet.y > gp.HEIGHT || bullet.x < 0 || bullet.x > gp.WIDTH));
    }

    public void bulletsImpactInAChair() {
        bullets.forEach(bullet -> {
            chairs.forEach(chair -> {
                if (chair.r.intersects(bullet.r)) {
                    chair.health--;
                    if(chair.health <= 0) {
                        player.points++;
                    }
                }
            });
        });


        chairs.removeIf(chair -> (chair.health <= 0));
        chairs.forEach(chair -> {
            bullets.removeIf(bullet -> (bullet.r.intersects(chair.r)));
        });
    }

    public void chairsCollisionWithPlayer() {

        chairs.forEach(chair -> {
            if (chair.r.intersects(player.r)) {
                player.health -= chair.damage;
            }
        });
    }
}
