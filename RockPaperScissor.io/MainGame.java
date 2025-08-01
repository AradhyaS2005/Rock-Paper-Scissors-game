import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;

class MainFrame extends JFrame implements ActionListener
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

        // adding padding around buttons using emptyborder
        mainButtonPanel.setBorder(BorderFactory.createEmptyBorder(30,60,30,60));

        // wrapper panel to centre the mainButtonPanel
        JPanel wrapper = new JPanel(new GridBagLayout()); // this centres the content
        wrapper.add(mainButtonPanel);

        add(wrapper, BorderLayout.CENTER);
    }
    @Override
    public void actionPerformed(ActionEvent e) 
    {
        if(e.getSource() == exit)
        {
            System.exit(0);
        }
        if(e.getSource() == play)
        {
            new PlayFrame().setVisible(true); // opens the next play frame
        }
    }
    public void attachActionListnerMethod()
    {
        exit.addActionListener(this);
        play.addActionListener(this);
    }
}

class PlayFrame extends JFrame implements ActionListener
{
    JLabel welcomeLabel, askName;
    JTextField userName;

    PlayFrame() 
    {
        super("Game Window");

        setSize(500, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        welcomeLabel = new JLabel("Welcome to the game of rock, paper, and scissors");
        askName = new JLabel("Enter your name:");
        userName = new JTextField(20);
        userName.addActionListener(this);

        // Create a panel with vertical layout
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50)); // Add some padding

        // Center align labels and textfield
        welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        askName.setAlignmentX(Component.CENTER_ALIGNMENT);
        userName.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Add spacing between components
        contentPanel.add(welcomeLabel);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        contentPanel.add(askName);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        contentPanel.add(userName);

        add(contentPanel); // Add the panel to the frame
    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        String name = userName.getText();
        new GameStartFrame(name).setVisible(true); // opens the next play frame
    }
}

class GameStartFrame extends JFrame 
{
    JLabel gamerName;
    JLabel noOfRounds;
    JSpinner roundsChoice;
    public GameStartFrame(String name)
    {
        super("Welcome " + name);
        setSize(500,500);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        gamerName = new JLabel(name);
        noOfRounds = new JLabel("Select how many rounds you want to play?");

        SpinnerNumberModel model = new SpinnerNumberModel(1,1,10,1);
        roundsChoice = new JSpinner(model);

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));

        // Center and space components
        gamerName.setAlignmentX(Component.CENTER_ALIGNMENT);
        noOfRounds.setAlignmentX(Component.CENTER_ALIGNMENT);
        roundsChoice.setAlignmentX(Component.CENTER_ALIGNMENT);

        contentPanel.add(gamerName);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        contentPanel.add(noOfRounds);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        contentPanel.add(roundsChoice);

        add(contentPanel);

        JButton startButton = new JButton("Start Game");
        startButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        startButton.addActionListener(e -> {
        int rounds = (int) roundsChoice.getValue();
        new playingStartFrame(rounds, name).setVisible(true);
        });
        contentPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        contentPanel.add(startButton);

        setVisible(true);
    }
}

class playingStartFrame extends JFrame
{
    JLabel currentRoundLabel, resultLabel;
    JLabel playerImageLabel, CPUImageLabel;
    JButton rockButton, scissorsButton, paperButton;
    int totalRounds, currentRound;
    String playerName;
    playingStartFrame(int rounds, String name)
    {
        super("Game - " + name);
        setSize(500,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.totalRounds = rounds;
        this.playerName = name;
        this.currentRound = 1;

        setLayout(new BorderLayout(10,10));

        // Top panel: Current round & move buttons
        JPanel topPanel = new JPanel();
        currentRoundLabel = new JLabel("Round " + currentRound + " of " + totalRounds);
        rockButton = new JButton("Rock");
        paperButton = new JButton("Paper");
        scissorsButton = new JButton("Scissors");

        topPanel.add(currentRoundLabel);
        topPanel.add(rockButton);
        topPanel.add(paperButton);
        topPanel.add(scissorsButton);
        add(topPanel, BorderLayout.NORTH);

        // Center panel: Player & CPU choices
        JPanel centerPanel = new JPanel(new GridLayout(1, 2, 20, 0));
        playerImageLabel = new JLabel("You", SwingConstants.CENTER);
        playerImageLabel.setBorder(BorderFactory.createTitledBorder("Your Choice"));

        CPUImageLabel = new JLabel("CPU", SwingConstants.CENTER);
        CPUImageLabel.setBorder(BorderFactory.createTitledBorder("Computer's Choice"));

        centerPanel.add(playerImageLabel);
        centerPanel.add(CPUImageLabel);
        add(centerPanel, BorderLayout.CENTER);

        // Bottom panel: Result
        JPanel bottomPanel = new JPanel();
        resultLabel = new JLabel("Result will be shown here", SwingConstants.CENTER);
        bottomPanel.add(resultLabel);
        add(bottomPanel, BorderLayout.SOUTH);

        rockButton.addActionListener(e -> playRound("Rock"));
        paperButton.addActionListener(e -> playRound("Paper"));
        scissorsButton.addActionListener(e -> playRound("Scissors"));
    }
    private void playRound(String playerMove) 
    {
        // Placeholder logic
        String cpuMove = getRandomMove();

        // Show player and CPU move
        String basePath = "D:/PROJECTS (Java)/RockPaperScissor.io/imageResources/";
        playerImageLabel.setIcon(getScaledIcon(basePath + playerMove.toLowerCase() + ".png"));
        CPUImageLabel.setIcon(getScaledIcon(basePath + cpuMove.toLowerCase() + ".png"));

        // Show result
        String result = getResult(playerMove, cpuMove);
        resultLabel.setText(result);

        currentRound++;
        if (currentRound <= totalRounds) {
            currentRoundLabel.setText("Round " + currentRound + " of " + totalRounds);
        } else {
            currentRoundLabel.setText("Game Over");
            rockButton.setEnabled(false);
            paperButton.setEnabled(false);
            scissorsButton.setEnabled(false);
        }
    }

    private String getRandomMove() {
        String[] moves = {"Rock", "Paper", "Scissors"};
        return moves[(int) (Math.random() * 3)];
    }

    private String getResult(String player, String cpu) 
    {
        if (player.equals(cpu)) return "It's a Draw!";
        if ((player.equals("Rock") && cpu.equals("Scissors")) ||
            (player.equals("Paper") && cpu.equals("Rock")) ||
            (player.equals("Scissors") && cpu.equals("Paper")))
            return "You Win!";
        else
            return "You Lose!";
    }

    private ImageIcon getScaledIcon(String path) {
    ImageIcon icon = new ImageIcon(path);
    Image img = icon.getImage();
    Image scaledImg = img.getScaledInstance(150, 150, Image.SCALE_SMOOTH); // adjust size as needed
    return new ImageIcon(scaledImg);
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
        mainFrame.attachActionListnerMethod();
    }
}