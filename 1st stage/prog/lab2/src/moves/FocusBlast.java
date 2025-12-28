package moves;
import ru.ifmo.se.pokemon.*;

public class FocusBlast extends SpecialMove {
    boolean flag = false;
    public FocusBlast() {
        super(Type.FIGHTING, 120, 70);
    }
    @Override
    protected void applyOppEffects(Pokemon p) {
        Effect eff = new Effect();
        p.addEffect(eff.chance(0.1));
        if (eff.success()) {
            flag = true;
            p.addEffect(eff.stat(Stat.SPECIAL_DEFENSE, -1));
        }
    }
    @Override
    protected String describe() {
        if (flag) {
            return "использует FocusBlast и уменьшает special defense противника на 1";
        }
        else {
            return "использует FocusBlast";
        }
    }
}