package main;

import main.controllers.CollisionController;
import main.controllers.EntityController;
import main.controllers.KeyController;
import main.controllers.LayoutController;
import main.entities.Player;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{
    // columns and rows of windows
    public int columnsW = 20, rowsW = 12; // at the moment
    // tiles size and scale of the game windows
    public int gameScale = 7;
    public int tileSize = 10 * gameScale;

    public int HEIGHT = rowsW * tileSize;
    public int WIDTH = columnsW * tileSize;


    // FPS
    int FPS = 60;

    // game thread
    public Thread gameThread = new Thread(this);

    // game keys
    public KeyController keyController = new KeyController();

    // Entity controller
    public EntityController entityController = new EntityController(this);

    // Collision controller
    public CollisionController collisionController = new CollisionController(this);

    LayoutController layoutController = new LayoutController(this);

    @Override
    public void run() {
        double drawInterval = 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        long drawCount = 0;

        while (gameThread != null) {

            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (delta >= 1) {
                update();
                repaint();
                delta--;
                drawCount++;
            }

            if (timer >= 1000000000) {
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }

    private void update() {
        entityController.update();
        collisionController.controller();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        layoutController.setBackground(g2);

        entityController.draw(g2);

        layoutController.draw(g2);

        g2.dispose();

    }

    public GamePanel() {
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setBackground(Color.BLACK);

        // keys configuration
        this.setDoubleBuffered(true);
        this.addKeyListener(keyController);
        this.setFocusable(true);

        System.out.println(WIDTH+ ""+ HEIGHT);
    }
}
