package creatures;

import enums.Appearance;
import models.AbstractCharacter;

public class Brothers extends Human {
    private boolean isKicked;
    public String brothers;

    public Brothers(String names) {
        super(names);
        this.isKicked = true;
        this.brothers = names;
    }

    @Override
    public String toString() {
        return brothers;
    }
}
