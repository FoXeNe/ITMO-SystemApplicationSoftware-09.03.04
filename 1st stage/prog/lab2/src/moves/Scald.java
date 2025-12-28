package moves;
import ru.ifmo.se.pokemon.*;


public class Scald extends SpecialMove {
    boolean flag = false;
    public Scald(){
        super(Type.WATER, 80, 100);
    }
    @Override
    protected void applyOppEffects(Pokemon p) {
        Effect eff = new Effect();
        p.addEffect(eff.chance(0.3));
        if (eff.success()) {
            flag = true;
            Effect.burn(p);
        }
    }
    @Override
    protected String describe() {
        if (flag) {
            return "использует Scald и поджигает противника";
        }
        else {
            return "использует Scald";
        }

    }
}