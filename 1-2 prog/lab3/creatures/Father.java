package creatures;

import enums.Appearance;
import interfaces.Recognizing;
import models.AbstractCharacter;
import models.Animal;

public class Father extends Human {
    private boolean recognizing;

    public Father(String name) {
        super(name);
        this.name = name;
    }

    public void Recognize(AbstractCharacter target) {
        if (target.name == "Элиза") {
            this.recognizing = false;
            System.out.println("я не узнаю, " + target + " это не моя дочь");
        }
        else {
            this.recognizing = true;
            System.out.println("Я знаю, кто это, но это не моя дочь");
        }
    }
}
