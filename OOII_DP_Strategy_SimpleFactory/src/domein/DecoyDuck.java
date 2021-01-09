package domein;

public class DecoyDuck extends Duck {

    /*public DecoyDuck() {
     setQuackBehavior(new MuteQuack());
     setFlyBehavior(new FlyNoWay());
     }*/
    public DecoyDuck(QuackBehavior quackBehavior, FlyBehavior flyBehavior) {
        super(quackBehavior, flyBehavior);
    }

    public String display() {
        return "Ik ben een lokeend";
    }

}
