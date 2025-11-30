package moves;
import ru.ifmo.se.pokemon.*;

public class Stomp extends PhysicalMove {
    public Stomp() {
        super(Type.NORMAL, 60, 100);
    }
    @Override
    protected String describe() {
        return "использует Stomp";
    }
}