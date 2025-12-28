import ru.ifmo.se.pokemon.Battle;
import ru.ifmo.se.pokemon.Pokemon;
import pokemons.*;

class main {
    public static void main(String[] args) {
        Battle b = new Battle();
        Pokemon a1 = new AegislashBlade("Игорь", 100);
        Pokemon  a2 = new Doublade("Святогор", 100);
        Pokemon a3 = new Slowpoke("Родион", 100);
        Pokemon f1 = new Slowbro("Медленныйчувак", 100);
        Pokemon f2 = new Regice("Олег", 100);
        Pokemon f3 = new Honedge("Хз", 100);
        b.addAlly(a1);
        b.addAlly(a2);
        b.addAlly(a3);
        b.addFoe(f1);
        b.addFoe(f2);
        b.addFoe(f3);
        b.go();
    }
}
