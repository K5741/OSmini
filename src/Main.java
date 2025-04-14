public class Main {
    public static void main(String[] args) {
        /*
        javax.swing.SwingUtilities.invokeLater(() -> {
            new GameWindow();
        });
         */
        
        PopupThread spawn = new PopupThread();
        spawn.start();
    }
}