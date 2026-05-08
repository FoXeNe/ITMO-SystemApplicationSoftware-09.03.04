package pokemons;
import moves.*;
import ru.ifmo.se.pokemon.*;

public class Slowpoke extends dop {
    public Slowpoke(String name, int level) {
        super(name, level, new Type[]{Type.WATER, Type.PSYCHIC});
        int baseAttack = 90;
        int bonusCount = 0;
        setStats(baseAttack, 65, 65, 40, 40, 15);
        setMove(new Scald(), new SlackOff(), new Headbutt());
        if (level % 100 == 0 & !randType()) {
            bonusCount = level / 100;
            System.out.println(name + " урон увелчилися на 20 процентов");
            baseAttack = (int) (baseAttack * Math.pow(1.2, bonusCount));
            setStats(baseAttack, 65, 65, 40, 40, 15);
        }
    }
}
