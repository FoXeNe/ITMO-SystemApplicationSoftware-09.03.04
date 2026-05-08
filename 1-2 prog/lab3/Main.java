import creatures.*;
import enums.Appearance;
import enums.LocationType;
import record.QueenAction;

public class Main {
    public static void main(String[] args) {
        Eliza eliza1 = new Eliza("Элиза");
        Eliza eliza2 = new Eliza("Элиза");
        eliza1.cry();
        eliza1.wanderlnSwamp();
        eliza2.cry();
        eliza2.wanderlnSwamp();
        boolean areEqual = eliza1.equals(eliza2);
        System.out.println(areEqual);

        Eliza eliza = new Eliza("Элиза");
        evilQueen queen = new evilQueen("Злая королева");
        Brothers brothers = new Brothers("Игорь, Святополк, Владимир");
        Dog dog = new Dog("Собака");
        Martin martin = new Martin("Ласточка");
        Father father = new Father("Отец");

        queen.smearWithWalnutJuice(eliza);
        queen.applySmellyOintment(eliza);
        queen.tangleHair(eliza);

        dog.Recognize(eliza);
        martin.Recognize(eliza);
        father.Recognize(eliza);

        eliza.cry();
        eliza.searchBrothers(LocationType.FOREST);
        eliza.yearn(brothers.toString());

    }
}