/*
 * Supposed to be a side character who gives the warnings when things speed up/garbage coming
 * Thinking to add the timer triggers here or it can be another class and this alone deals with messages
 */
import javax.swing.*;
import java.awt.*;
import java.io.PipedOutputStream;

public class PopupThread extends Thread {
    private final String[] messages = {
        "Speeding up soon! =͟͟͞͞( •̀д•́)))",
        "Do you smell that..?",
        "Dumping On You Soon...💩",
        "Time to speed things up! ༽◺_◿༼)"
    };

    private PipedOutputStream pipeOut;
    private JFrame popup;
    private JLabel characterImage;
    private JLabel messageLabel;

    public PopupThread(PipedOutputStream pipeOut) {
        this.pipeOut = pipeOut;
    }

    public void run() {
        popup = new JFrame("Warning Msgs");
        popup.setSize(250, 150);
        popup.setLocation(80, 50);
        popup.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        popup.setUndecorated(true);
        popup.setAlwaysOnTop(true);

        JPanel panel = new JPanel();
        // panel.setBackground(Color.YELLOW);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        ImageIcon icon = new ImageIcon("C:/Users/kim75/Downloads/spawn.png");
        Image scaledImage = icon.getImage().getScaledInstance(120, 110, Image.SCALE_SMOOTH);
        characterImage = new JLabel(new ImageIcon(scaledImage));
        characterImage.setAlignmentX(Component.CENTER_ALIGNMENT);
        messageLabel = new JLabel("");
        messageLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
        messageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(characterImage);
        panel.add(Box.createVerticalStrut(10));
        panel.add(messageLabel);

        popup.add(panel);

        for (int i = 0; i < messages.length; i++) {
            final int index = i;
            try {
                // Show msg each minute
                Thread.sleep(60000);
                pipeOut.write(("SHOW\n").getBytes());
                pipeOut.write(("MODE" + i + "\n").getBytes());

                SwingUtilities.invokeLater(() -> {
                    messageLabel.setText(messages[index]);
                    popup.setVisible(true);
                });
                // Show msg for 3 secs
                Thread.sleep(3000);

                SwingUtilities.invokeLater(() -> popup.setVisible(false));
                pipeOut.write(("HIDE\n").getBytes());

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        popup.dispose();
    }
}