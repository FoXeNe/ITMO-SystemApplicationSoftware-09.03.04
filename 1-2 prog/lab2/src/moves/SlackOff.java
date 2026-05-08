package moves;
import ru.ifmo.se.pokemon.*;

public class SlackOff extends StatusMove {
    public SlackOff() {
        super(Type.NORMAL, 0, 0);
    }
    @Override
    protected void applySelfEffects(Pokemon p) {
        double hp = p.getHP() / 2;
        p.addEffect(new Effect().stat(Stat.HP, (int) hp));
    }
    @Override
    protected String describe() {
        return "использует SlackOff восстанавливая половину hp";
    }
}