package creatures;

import enums.Appearance;
import enums.LocationType;
import interfaces.Traveling;

import java.util.Arrays;
import java.util.Objects;


public class Eliza extends Human implements Traveling {
    private boolean isCrying;
    private boolean destinationKnown;
    private LocationType currLocation;
    private Appearance appearance = Appearance.BEAUTIFUL;

    public Eliza(String name) {
        super(name);
        this.isCrying = false;
        this.destinationKnown = false;
        this.currLocation = LocationType.PALACE;
        this.appearance = appearance;
    }

    public void cry() {
        isCrying = true;
        System.out.println(name + " заплакала");
    }

    public void yearn(String brothers) {
        System.out.println(name + " истосковалась по " + brothers   );
        System.out.println(name + " решила искать братьев " + LocationType.EVERYWHERE);
    }

    public void searchBrothers(LocationType location) {
        System.out.println(name + " пошла искать братьев");
        walkTo(location);
    }

    @Override
    public void walkTo(LocationType destination) {
        System.out.println(name + " идет в " + destination);
        wanderlnSwamp();
    }

    @Override
    public void wanderlnSwamp() {
        System.out.println(name + " бредет по болотам");
        currLocation = LocationType.SWAMP;
    }

    public String toString() {
        return "Элиза";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Eliza eliza = (Eliza) obj;

        if (!Objects.equals(name, eliza.name)) {
            return false;
        }

        return isCrying == eliza.isCrying &&
                destinationKnown == eliza.destinationKnown &&
                currLocation == eliza.currLocation &&
                appearance == eliza.appearance;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, isCrying, destinationKnown, currLocation, appearance);
    }
}