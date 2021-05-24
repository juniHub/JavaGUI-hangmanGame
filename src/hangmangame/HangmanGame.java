
package hangmangame;

import javax.swing.JFrame;

/**
 *
 * @author Thuy Tien Nguyen
 */

public class HangmanGame {

    private static final int WIDTH = 600;
    private static final int HEIGHT = 400;

    public static void main(String[] args) {

        JFrame frame = new GameOption();
        frame.setTitle("Nguyen's Hangman Game");
        frame.setSize(WIDTH, HEIGHT);
        frame.setResizable(true);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

}
