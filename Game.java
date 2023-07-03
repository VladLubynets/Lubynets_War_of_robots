package Lubynets_G7_Exam.Lubynets_G7_War_of_Robots;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {
    private List<Robot> robots;
    private Scanner scanner;
    private int currentPlayerIndex;

    public Game() {   // constructor  class game
        robots = new ArrayList<>();
        scanner = new Scanner(System.in);
        currentPlayerIndex = 0;   //index current player = 0
    }

    public void start() {
        initializeRobots(); // Game is started  Initializes robots  displays information about them
        printRobots();

        do {                 // Consistently processes the moves of the players until the end of the game
            printCurrentPlayer();
            makeMove();
            printRobots();
            changeCurrentPlayer();
        } while (!isGameOver());

        Robot winner = getWinner();
        if (winner != null) {
            System.out.println("The winner is " + winner.getName() + "!"); // Display choose winner or draw
        } else {
            System.out.println("It's a draw!");
        }
    }

    private void initializeRobots() {
        System.out.println("Game is started!!!");
        System.out.println("Use only these letters for Attack: Q, W, E, A, S, D, Z, X, C");
        System.out.println("If you want to exit, please press 'P' after choosing the names of the robots");
        System.out.println("Initializing Robots...");
        System.out.println();

        Robot robot1 = createRobot(1);  // initialize Robot 1
        robots.add(robot1);

        Robot robot2 = createRobot(2); // initialize Robot 2
        robots.add(robot2);

        System.out.println("Robots have been initialized!");
        System.out.println();
    }

    private Robot createRobot(int index) {
        System.out.println("Enter the name of Robot " + index + ":");
        String name = scanner.nextLine(); // read name of robot
        List<Character> buttons = Robot.getRandomButtons(); // generate random buttons with method getRandomButtons() Robot`s class
        Robot robot = new Robot(name, buttons, "QWEASDZXC"); // create robot with chosen name and with alphabet
        return robot; // return chosen robot
    }


    private void printRobots() {  // get status of health all robots and names all robots
        System.out.println("Current status of Robots:");
        for (int i = 0; i < robots.size(); i++) {
            Robot robot = robots.get(i);
            System.out.println(robot.getName() + " - Health: " + robot.getHealth());
        }
        System.out.println();
    }

    private boolean isGameOver() {   // check if game over or not. if alive robot  1 or less = true
        int aliveRobotsCount = 0;
        for (int i = 0; i < robots.size(); i++) {
            if (robots.get(i).isAlive()) {
                aliveRobotsCount++;
            }
        }
        return aliveRobotsCount <= 1;
    }

    private void makeMove() {
        System.out.println("Enter the button you want to press:");
        char button = Character.toUpperCase(scanner.next().charAt(0)); // read symbol as symbol with capital letter
        scanner.nextLine(); //  read symbol until the end of the line

        boolean buttonPressed = false; // check button pressed or not.  Button not pressed as default
        Robot currentPlayer = getCurrentPlayer();
        if (currentPlayer.getButtons().contains(button)) { //  if buttons = buttons with damage
            currentPlayer.shoot();  // take damage current player
            currentPlayer.removeButton(button); // // remove button which deals damage
            buttonPressed = true; //  Button has been pressed
        }

        if (!buttonPressed) {
            System.out.println("Button is inactive!"); // If the button is inactive
        }
        if (button == 'P') {
            System.out.println("Game over!!!");  // If P button is pressed, the game ends.
            System.exit(0);
        }

        System.out.println();
    }


    private Robot getCurrentPlayer() { // get index current player
        return robots.get(currentPlayerIndex);
    }

    private void changeCurrentPlayer() { // change current player with cycle do while
        currentPlayerIndex++;
        if (currentPlayerIndex >= robots.size()) {
            currentPlayerIndex = 0;
        }
    }

    private void printCurrentPlayer() { // print information about current player and information about new target to hit
        Robot currentPlayer = getCurrentPlayer();
        System.out.println("Now shoot at: " + currentPlayer.getName() + ". If you hit the correct button, " + currentPlayer.getName() + " will take damage.");
        System.out.println();
    }

    private Robot getWinner() {  // WIN  if robots.get = alive   or not alive = DRAW
        for (int i = 0; i < robots.size(); i++) {
            if (robots.get(i).isAlive()) {
                return robots.get(i);
            }
        }
        return null;
    }
}