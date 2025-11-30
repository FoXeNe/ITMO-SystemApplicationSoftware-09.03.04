package moves;
import ru.ifmo.se.pokemon.*;

public class Bulldoze extends PhysicalMove {
    public Bulldoze() {
        super(Type.GROUND, 60, 100);
    }

    @Override
    protected void applyOppEffects(Pokemon p) {
        p.addEffect(new Effect().stat(Stat.SPEED, -1));
    }

    @Override
    protected String describe() {
        return "использует bulldoze и уменьшает скорость оппонента на 1";
    }
}