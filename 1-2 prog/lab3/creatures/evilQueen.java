package creatures;

import enums.Appearance;
import exception.CurseException;
import interfaces.Disgusting;
import models.AbstractCharacter;
import record.QueenAction;

import java.nio.file.FileSystemNotFoundException;

public class evilQueen extends Human implements Disgusting {
    private boolean isWicked = true;
    int counter =  0;

    public evilQueen(String name) {
        super(name);
    }

    @Override
    public void smearWithWalnutJuice(AbstractCharacter target) {
        target.appearance = Appearance.BROWN_SKIN;
        QueenAction queenAction = new QueenAction("Намазала соком", target);
        queenAction.logAction();
        counter++;
        if (counter > 3) {
            throw new CurseException("Больше 3 заклинаний");
        }
    }

    @Override
    public void applySmellyOintment(AbstractCharacter target) {
        QueenAction queenAction = new QueenAction("Вымазала личико вонючей мазью", target);
        queenAction.logAction();
        counter++;
        if (counter > 3) {
            throw new CurseException("Больше 3 заклинаний");
        }
    }

    @Override
    public void tangleHair(AbstractCharacter target) {
        target.appearance = Appearance.UNKEMPT;
        QueenAction queenAction = new QueenAction("связала волосы", target);
        queenAction.logAction();
        counter++;
        if (counter > 3) {
            throw new CurseException("Больше 3 заклинаний");
        }
    }
}