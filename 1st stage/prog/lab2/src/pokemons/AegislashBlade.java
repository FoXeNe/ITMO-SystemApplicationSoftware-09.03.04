package pokemons;
import moves.*;

public class AegislashBlade extends Doublade {
    public AegislashBlade(String name, int level) {
        super(name, level);
        setStats(60, 150, 50, 150, 50, 60);
        addMove(new ShadowClaw());
    }
}
