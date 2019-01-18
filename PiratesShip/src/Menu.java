import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu {

    private JFrame mainFrame;
    private JFrame help;
    public void startWindow() {
        mainFrame = new JFrame("Pirate Hunt");
        mainFrame.setSize(600, 600);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

     
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(5, 10));

        mainPanel.setBackground(Color.LIGHT_GRAY);

        JLabel welcomeMessage = new JLabel("Welcome in Pirate Hunt", SwingConstants.CENTER);
        welcomeMessage.setFont(new Font("Serif", Font.PLAIN, 50));
        welcomeMessage.setForeground(Color.RED);

        JButton startNewGame = new JButton("New Game");
        JButton getHelp = new JButton("Help");
        JButton quit = new JButton("Quit");

        startNewGame.setFont(new Font("Serif", Font.PLAIN, 30));
        getHelp.setFont(new Font("Serif", Font.PLAIN, 30));
        quit.setFont(new Font("Serif", Font.PLAIN, 30));

        
        startNewGame.addActionListener(e -> {
            // TODO open your game
        	Game game = new Game();
            game.init();
           //game.startWindow();
      this.setWindowVisibility(true);
              
});

        getHelp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                help = new JFrame("Help");
                help.setSize(850, 750);
                help.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                JPanel helpPanel = new JPanel();
                JLabel gameHelp = new JLabel();
                gameHelp.setText("<html><h1>Upustvo</h1><h2>1. Igra se igra na matrici n×n sa preprekama.</h2><h2>2. Cilj igrača je obići određena unaprijed zadana polja ili doći iz jednog polja u drugo. </h2><h2>3. Gusari pokušavaju uhvatiti igrača.</h2><h2>4. Ako gusar udari u prepreku, nestaje.</h2><h2>5. Ako se dva gusara sudare, nastane nova prepreka.</h2><h2>6. Brod se kreće pomoću brojeva, pri čemu su:</h2><h2> 1-LEFT DOWN</h2><h2> 2-DOWN</h2><h2> 3-RIGHT DOWN</h2><h2> 4-LEFT</h2><h2> 6-RIGHT</h2><h2> 7-LEFT UP</h2><h2> 8-UP</h2><h2> 9-RIGHT UP</h2> </html>");
               
                
                helpPanel.add(gameHelp);
                help.add(helpPanel);
                help.setVisible(true);
            }
        });

        quit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        mainPanel.add(welcomeMessage);
        mainPanel.add(startNewGame);
        mainPanel.add(getHelp);
        mainPanel.add(quit);


        mainFrame.add(mainPanel);
        mainFrame.setResizable(false);

    }

    public void setWindowVisibility(boolean visibility) {
        mainFrame.setVisible(visibility);
    }

}
