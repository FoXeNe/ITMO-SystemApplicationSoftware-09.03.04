package moves;

import ru.ifmo.se.pokemon.Effect;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.SpecialMove;
import ru.ifmo.se.pokemon.Type;

public class Headbutt extends SpecialMove {
    boolean flag = false;
    public Headbutt() {
        super(Type.PSYCHIC, 50, 100);
    }
    @Override
    protected void applyOppEffects(Pokemon p) {
        Effect eff = new Effect();
        p.addEffect(eff.chance(0.3));
        if (eff.success()) {
            flag = true;
            Effect.flinch(p);
        }
    }
    @Override
    protected String describe() {
        if (flag){
            return "использует Headbutt и накладывает flinch на противника";
        }
        else {
            return "использует headbutt";
        }
    }
}
