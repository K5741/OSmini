/*
 * Right now if you run it calls the popup character
 * That will be on the side or near the gameboard giving warnings
 */
public class Main {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            new GameWindow();
        });
        
        PopupThread spawn = new PopupThread();
        spawn.start();
    }
}