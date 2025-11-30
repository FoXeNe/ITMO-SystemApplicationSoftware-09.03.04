package moves;
import ru.ifmo.se.pokemon.*;

public class Thunderbolt extends SpecialMove{
    public Thunderbolt() {
        super(Type.ELECTRIC, 90, 100);
    }
    @Override
    protected String describe() {
        return "использует Thunderbolt";
    }
}
