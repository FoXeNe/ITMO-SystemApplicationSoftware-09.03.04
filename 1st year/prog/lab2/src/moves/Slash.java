package moves;

import ru.ifmo.se.pokemon.*;

public class Slash extends PhysicalMove {
    boolean flag = false;
    public Slash() {
        super(Type.NORMAL, 70, 100);
    }
    @Override
    protected double calcCriticalHit(Pokemon att, Pokemon def) {
        if (att.getStat(Stat.SPEED) / (double)8.0F > Math.random()) {
            flag = true;
            return (double)2.0F;
        } else {
            return (double)1.0F;
        }
    }
    @Override
    protected String describe() {
        if (flag) {
            return "использует Slash с повышенным шансом на крит";}
        else {
            return "использует Slash";
        }
    }
}
