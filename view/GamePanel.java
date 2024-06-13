package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import controller.KeyboardController;
import model.field.Field;
import model.character.Character;

public class GamePanel extends JPanel implements Runnable {
    private Thread gameThread;
    private Field field;
    private BufferedImage buffer;
    KeyboardController kbController = new KeyboardController();
    
    public GamePanel(int width, int height) {
        setPreferredSize(new Dimension(width, height));
        setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.setFocusable(true);

        this.field = new Field(height, width);
        // field.addCharacter("c1", "resources/c1/");
        field.addCharacter("samurai", "resources/samurai/");
    }

    public void startGameThread() {
        if (gameThread == null) {
            gameThread = new Thread(this);
            gameThread.start();
        }
    }

    @Override
    public void run() {
        double drawInterval = 1000000000 / 24;
        double drawNextTile = System.nanoTime() + drawInterval;
        while (gameThread != null) {
            update();

            try {
                double remainingTime = drawNextTile - System.nanoTime();
                remainingTime = remainingTime/1000000;
                if (remainingTime < 0) {
                    remainingTime = 0;
                }
                Thread.sleep((long)remainingTime);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void update() {
        field.act(kbController);
        paintComponent(getGraphics());
    }

    public void paintCharacter(Graphics g, Character c) {
        try {
            BufferedImage img = ImageIO.read(new File(c.getImgPath() + c.getSelectedIMG()));
            g.drawImage(img, c.getXPosition(), c.getYPosition(), this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        if (buffer == null || buffer.getWidth() != getWidth() || buffer.getHeight() != getHeight()) {
            buffer = (BufferedImage) createImage(getWidth(), getHeight());
        }

        Graphics bufferGraphics = buffer.getGraphics();
        super.paintComponent(bufferGraphics);
        for (Character c : field.getCharacters()) {
            paintCharacter(bufferGraphics, c);
        }
        g.drawImage(buffer, 0, 0, null);
    }
}
