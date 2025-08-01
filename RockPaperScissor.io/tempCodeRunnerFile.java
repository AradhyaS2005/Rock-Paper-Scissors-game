import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

class MainFrame extends JFrame
{
    JButton play, options, exit;
    JPanel mainButtonPanel;
    public MainFrame()
    {
        super("ROCK_PAPER_SCISSOR.io");

        play = new JButton("PLAY");
        options = new JButton("OPTIONS");
        exit = new JButton("EXIT");

        mainButtonPanel = new JPanel();
        mainButtonPanel.setLayout(new GridLayout(3,1,100,100));
        mainButtonPanel.add(play);
        mainButtonPanel.add(options);
        mainButtonPanel.add(exit);

        add(mainButtonPanel, BorderLayout.CENTER);
    }
}

public class MainGame
{
    public static void main(String args[])
    {
        MainFrame mainFrame = new MainFrame();
        mainFrame.setSize(500,500);
        mainFrame.setVisible(true);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}