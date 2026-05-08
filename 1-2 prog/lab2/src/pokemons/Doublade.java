package pokemons;
import moves.*;

public class Doublade extends Honedge {
    public Doublade(String name, int level) {
        super(name, level);
        int baseAttack = 59;
        setStats(baseAttack, 110, 150, 45, 49, 35);
        addMove(new Tackle());
    }
}
