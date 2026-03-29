// Tic Tac Toe Game
// A Java GUI application built using Java Swing
// Two players take turns placing X and O on a 3x3 grid

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TicTacToe extends JFrame implements ActionListener {

    // Array to store the 9 buttons that form the Tic Tac Toe board
    JButton[] buttons = new JButton[9];

    // Reset button to start a new game
    JButton resetButton;

    // Labels for title and player turn display
    JLabel titleLabel;
    JLabel turnLabel;

    // Boolean variable to track the current player
    // true = Player X, false = Player O
    boolean playerX = true;

    // Constructor to create the GUI
    public TicTacToe() {

        // Setting window properties
        setTitle("Tic Tac Toe");
        setSize(400,500);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // ---------------- TOP PANEL ----------------

        // Title label
        titleLabel = new JLabel("TIC TAC TOE", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 26));

        // Label to display whose turn it is
        turnLabel = new JLabel("Current Turn: X", JLabel.CENTER);
        turnLabel.setFont(new Font("Arial", Font.PLAIN, 16));

        // Panel to hold title and turn label
        JPanel topPanel = new JPanel(new GridLayout(2,1));
        topPanel.add(titleLabel);
        topPanel.add(turnLabel);

        add(topPanel, BorderLayout.NORTH);

        // ---------------- GAME BOARD ----------------

        // Panel that contains the 3x3 grid of buttons
        JPanel boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(3,3));
        boardPanel.setBackground(Color.LIGHT_GRAY);

        // Creating the 9 buttons for the board
        for(int i=0;i<9;i++)
        {
            buttons[i] = new JButton("");
            buttons[i].setFont(new Font("Arial",Font.BOLD,60));
            buttons[i].setFocusPainted(false);

            // Add click event listener
            buttons[i].addActionListener(this);

            boardPanel.add(buttons[i]);
        }

        add(boardPanel, BorderLayout.CENTER);

        // ---------------- RESET BUTTON ----------------

        // Button to reset the game
        resetButton = new JButton("Reset Game");
        resetButton.setFont(new Font("Arial", Font.BOLD,16));

        // When clicked, reset the board
        resetButton.addActionListener(e -> resetBoard());

        add(resetButton, BorderLayout.SOUTH);

        // Make the window visible
        setVisible(true);
    }

    // Method triggered when any board button is clicked
    public void actionPerformed(ActionEvent e) {

        // Identify which button was clicked
        JButton clicked = (JButton)e.getSource();

        // Only allow move if the button is empty
        if(clicked.getText().equals(""))
        {
            if(playerX)
            {
                // Player X move
                clicked.setText("X");
                clicked.setForeground(Color.BLUE);
                turnLabel.setText("Current Turn: O");
            }
            else
            {
                // Player O move
                clicked.setText("O");
                clicked.setForeground(Color.RED);
                turnLabel.setText("Current Turn: X");
            }

            // Switch player turn
            playerX = !playerX;

            // Check if someone has won
            checkWinner();
        }
    }

    // Method to check if any player has won the game
    public void checkWinner() {

        // All possible winning combinations
        int[][] winPositions = {
                {0,1,2}, // Top row
                {3,4,5}, // Middle row
                {6,7,8}, // Bottom row
                {0,3,6}, // Left column
                {1,4,7}, // Middle column
                {2,5,8}, // Right column
                {0,4,8}, // Diagonal
                {2,4,6}  // Diagonal
        };

        // Check each winning combination
        for(int[] pos : winPositions)
        {
            if(!buttons[pos[0]].getText().equals("") &&
               buttons[pos[0]].getText().equals(buttons[pos[1]].getText()) &&
               buttons[pos[1]].getText().equals(buttons[pos[2]].getText()))
            {
                // Highlight winning cells
                highlightWinner(pos);

                // Show winner message
                JOptionPane.showMessageDialog(this,
                        buttons[pos[0]].getText() + " Wins!");

                // Reset board after win
                resetBoard();
                return;
            }
        }

        // Check for draw condition
        boolean draw = true;

        for(JButton b : buttons)
        {
            if(b.getText().equals(""))
            {
                draw = false;
                break;
            }
        }

        if(draw)
        {
            JOptionPane.showMessageDialog(this,"Game Draw!");
            resetBoard();
        }
    }

    // Method to highlight winning buttons
    public void highlightWinner(int[] pos)
    {
        for(int i : pos)
        {
            buttons[i].setBackground(Color.GREEN);
        }
    }

    // Method to reset the board for a new game
    public void resetBoard()
    {
        for(JButton b : buttons)
        {
            b.setText("");
            b.setBackground(null);
        }

        playerX = true;
        turnLabel.setText("Current Turn: X");
    }

    // Main method to start the program
    public static void main(String[] args) {
        new TicTacToe();
    }
}
