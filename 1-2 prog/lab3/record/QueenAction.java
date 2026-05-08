package record;

import models.AbstractCharacter;

public record QueenAction(String actionName, AbstractCharacter target) {
    public void logAction() {
        System.out.println("Злая королева " + actionName);
    }
}