package models;

import enums.Appearance;
import interfaces.Recognizing;

public abstract class Animal extends AbstractCharacter implements Recognizing {
    protected boolean recognizing = true;

    public Animal(String name) {
        super(name);
        this.recognizing = recognizing;
    }

    public void Recognize(AbstractCharacter target) {
        if (target.name == "Элиза") {
            this.recognizing = false;
            System.out.println("Узнала ли " + name + " " + target + "? " + recognizing);
        }
        else {
            this.recognizing = true;
            System.out.println("Узнала ли " + name + " " + target + "? " + recognizing);
        }
    }
}