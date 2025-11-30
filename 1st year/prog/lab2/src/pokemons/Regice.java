package pokemons;
import moves.*;
import ru.ifmo.se.pokemon.*;

public class Regice extends Pokemon {
    public Regice(String name, int level){
        super(name, level);
        setType(Type.ICE);
        setStats(80, 50, 100, 100, 200, 50);
        setMove(new Bulldoze(), new Thunderbolt(), new Stomp(), new Swagger());
    }
}