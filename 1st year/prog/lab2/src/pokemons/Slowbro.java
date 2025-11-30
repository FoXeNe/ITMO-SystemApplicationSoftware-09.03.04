package pokemons;
import moves.*;

public class Slowbro extends Slowpoke {
    public Slowbro(String name, int level) {
        super(name, level);
        setStats(95, 75, 110, 100, 80, 30);
        setMove(new FocusBlast());
    }
}
