package main;

import javax.swing.*;

public class GameFrame extends JFrame {

    public GameFrame() {
        GamePanel gp = new GamePanel();
        gp.gameThread.start();

        this.setTitle("juego sillas");
        this.add(gp);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

}
