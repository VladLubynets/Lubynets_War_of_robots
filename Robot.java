package Lubynets_G7_Exam.Lubynets_G7_War_of_Robots;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Robot {
    private String name;
    private int health;
    private List<Character> buttons;
    private String alphabet;

    public Robot(String name, List<Character> buttons, String alphabet) {  // constructor of Robots class
        this.name = name;
        this.health = 100;
        this.buttons = buttons;
        this.alphabet = alphabet;
    }

    public String getName() {  // get robot`s name
        return name;
    }

    public int getHealth() {  //   get robot`s health
        return health;
    }

    public void shoot() {  // do shoot in robot (-20 health)
        System.out.println(name + " has been shot!");
        health -= 20;
    }

    public boolean isAlive() {  // check alive bot or not   true = if health > 0
        return health > 0;
    }

    public List<Character> getButtons() {  // list buttons of damage
        return buttons;
    }

    public void removeButton(Character button) {  // delete specified button
        buttons.remove(button);
    }

    public  static  char getRandomKey(String alphabet) { //randomnizer 1 symbol
        int randIdx = new Random().nextInt(alphabet.length());
        char randChar = alphabet.charAt(randIdx);
        alphabet = alphabet.replace(String.valueOf(randChar), "");
        return randChar;
    }

    public static List<Character> getRandomButtons() { // randomizer 5 symbols
        List<Character> buttons = new ArrayList<>();
        String alphabet = "QWEASDZXC";
        for (int i = 0; i < 5; i++) {
            char button = getRandomKey(alphabet);
            buttons.add(Character.toUpperCase(button));
            alphabet = alphabet.replace(String.valueOf(button), "");
        }
        return buttons;
    }

}