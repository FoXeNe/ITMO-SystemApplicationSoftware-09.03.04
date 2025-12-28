package pokemons;
import ru.ifmo.se.pokemon.*;
 import moves.*;

public class Honedge extends dop {
    public Honedge(String name, int level) {
        super(name, level, new Type[]{Type.STEEL, Type.GHOST});
        int baseAttack = 45;
        int bonusCount = 0;
        setMove(new IronDefense(), new Slash());
        setStats(baseAttack, 80, 100, 35, 37, 28);
        if (level % 100 == 0 & !randType()) {
            bonusCount = level / 100;
            System.out.println(name + " урон увелчилися на 20 процентов");
            baseAttack = (int) (baseAttack * Math.pow(1.2, bonusCount));
            setStats(baseAttack, 80, 100, 35, 37, 28);
        }
    }
}