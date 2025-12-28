package pokemons;
import ru.ifmo.se.pokemon.*;
import java.util.Arrays;

public class dop extends Pokemon {
    public dop(String name, int level, Type[] types) {
        super(name, level);
        setType(types);
    }

    private final Type[][] hz = {
            {Type.FIRE, Type.WATER},
            {Type.NORMAL, Type.GHOST},
            {Type.ELECTRIC, Type.GRASS},
            {Type.ICE, Type.FIRE},
            {Type.FIGHTING, Type.GHOST},
            {Type.DARK, Type.PSYCHIC}
    };


    protected boolean randType() {
        Type[] currTypes = this.getTypes();
        if (currTypes.length >= 2) {
            return false;
        }

        final Type[] allTypes = Type.values();
        // удалить none из массива
        int rand = (int) (Math.random() * allTypes.length) - 1;
        Type newType = allTypes[rand];

        boolean allowed = true;
        for (Type[] pair : hz) {
            boolean oneInPair = false;
            boolean newTypeInPair = false;

            for (Type oneOfPair : pair) {
                if (oneOfPair == newType) {
                    newTypeInPair = true;
                }
                for (Type ct : currTypes) {
                    if (ct == oneOfPair) {
                        oneInPair = true;
                    }
                }
            }
            if (oneInPair && newTypeInPair) {
                allowed = false;
                break;
            }
        }

        if (allowed) {
            addType(newType);
            Type[] jlj = this.getTypes();
            System.out.println("получает случайный тип");
            System.out.println(Arrays.toString(jlj));
            return true;
        }
        return false;
    }
}
