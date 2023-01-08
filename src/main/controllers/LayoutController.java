package main.controllers;

import main.GamePanel;
import main.entities.Player;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class LayoutController {
    GamePanel gp;
    Player player;
    boolean backgroundIsLoad = false;

    public LayoutController(GamePanel gp) {
        this.gp = gp;
        this.player = gp.entityController.player;
    }

    public void draw(Graphics2D g2) {
        gameOver(g2);
        healthLayout(g2);
    }

    private void gameOver(Graphics2D g2) {
        if (player.health <= 0) {
            g2.setColor(Color.RED);
            g2.setFont(new Font("Courier", Font.PLAIN, 40));
            g2.drawString("Game Over", (gp.WIDTH / 2) - 40, gp.HEIGHT / 2);

            gp.gameThread = null;
        }
    }

    private void healthLayout(Graphics2D g2) {
        g2.setColor(Color.RED);
        g2.setFont(new Font("Courier", Font.PLAIN, 30));
        g2.drawString((player.health * 100) / 1000 + "% <3", (gp.WIDTH / 35) - 40, gp.HEIGHT / 30);
        g2.setColor(Color.GREEN);
        g2.drawString(player.points + " BTN", 127 + 40, gp.HEIGHT / 30);
    }

    public void setBackground(Graphics2D g2) {
            try {
                g2.drawImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("./background.png"))), 0, 0, gp.WIDTH, gp.HEIGHT, null);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
    }
}
