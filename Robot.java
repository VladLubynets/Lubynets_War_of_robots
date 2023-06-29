package Lubynets_G7_War_of_Robots;

import java.util.Random;

public class Robot {
    private String name;
    private int health;
    private char button;
    private String alphabet;

    public Robot(String name, char button) {
        this.name = name;
        this.health = 100;
        this.button = button;
        this.alphabet = "QWEASDZXC";
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public void shoot() {
        System.out.println(name + " has been shot!");
        health -= 20;
    }

    public boolean isAlive() {
        return health > 0;
    }

    public void setButton(char newButton) {
        this.button = newButton;
    }

    public char getButton() {
        return button;
    }

    private char getRandomKey() {
        int randIdx = new Random().nextInt(alphabet.length());
        char randChar = alphabet.charAt(randIdx);
        alphabet = alphabet.replace(String.valueOf(randChar), "");
        return randChar;
    }
}