package Lubynets_G7_War_of_Robots;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Game {
    private List<Robot> robots;
    private Random randomGenerator;
    private Scanner scanner;

    public Game() {
        robots = new ArrayList<>();
        randomGenerator = new Random();
        scanner = new Scanner(System.in);
    }

    public void addRobot(Robot robot) {
        robots.add(robot);
    }

    public void start() {
        initializeRobots();
        printRobots();

        while (!isGameOver()) {
            makeMove();
            printRobots();
        }

        Robot winner = getWinner();
        if (winner != null) {
            System.out.println("The winner is " + winner.getName() + "!");
        } else {
            System.out.println("It's a draw!");
        }
    }

    private void initializeRobots() {
        System.out.println("Game is started!!!");
        System.out.println("Use only capital letters for Attack \"Q,W,E,A,S,D,Z,X,C\"");
        System.out.println("If you want Exit - Please press \"P\" after choosing the names of the robots");
        System.out.println("Initializing Robots...");
        System.out.println();

        Robot robot1 = createRobot(1);
        robots.add(robot1);

        Robot robot2 = createRobot(2);
        robots.add(robot2);

        System.out.println("Robots have been initialized!");
        System.out.println();
    }

    private Robot createRobot(int index) {
        System.out.println("Enter the name of Robot " + index + ":");
        String name = scanner.nextLine();
        char button = getRandomKey();
        return new Robot(name, button);
    }

    private void printRobots() {
        System.out.println("Current status of Robots:");
        int count = robots.size();
        for (int i = 0; i < count; i++) {
            Robot robot = robots.get(i);
            System.out.println(robot.getName() + " - Health: " + robot.getHealth());
        }
        System.out.println();
    }

    private boolean isGameOver() {
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
        char button = scanner.next().charAt(0);

        boolean buttonPressed = false;
        Robot robot = null;
        for (int i = 0; i < robots.size(); i++) {
            if (robots.get(i).getButton() == button) {
                robot = robots.get(i);
                robot.shoot();
                char newButton = getRandomKey();
                robot.setButton(newButton);
                buttonPressed = true;
                break;
            }
        }

        if (!buttonPressed) {
            System.out.println("Button is inactive!");
        }
        if (button == 'P') {
            System.out.println("Game over!!!");
            System.exit(0);
        }

        System.out.println();
    }

    private char getRandomKey() {
        int index = randomGenerator.nextInt(9);
        return "QWEASDZXC".charAt(index);
    }

    private Robot getWinner() {
        Robot winner = null;
        for (int i = 0; i < robots.size(); i++) {
            if (robots.get(i).isAlive()) {
                winner = robots.get(i);
                break;
            }
        }
        return winner;
    }
}