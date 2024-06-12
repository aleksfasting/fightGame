package main;
import javax.swing.JFrame;
import view.GamePanel; 

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1408, 832);
        frame.setResizable(false);

        GamePanel panel = new GamePanel(1408, 832);
        frame.add(panel);

        frame.pack();

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        panel.startGameThread();
    }
}
