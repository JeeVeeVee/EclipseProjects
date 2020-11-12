package domein;

public abstract class Duck {

	private FlyBehavior flyBehavior;
	private QuackBehavior quackBehavior;

	public void setFlyBehavior(FlyBehavior flyBehavior) {
		this.flyBehavior = flyBehavior;
	}

	public void setQuackBehavior(QuackBehavior quackBehavior) {
		this.quackBehavior = quackBehavior;
	}

	public java.lang.String performQuack() {
		// TODO - implement Duck.performQuack
		throw new UnsupportedOperationException();
	}

	public java.lang.String performFly() {
		// TODO - implement Duck.performFly
		throw new UnsupportedOperationException();
	}

	public java.lang.String swim() {
		// TODO - implement Duck.swim
		throw new UnsupportedOperationException();
	}

	public abstract java.lang.String display();

	public void ANDERE_eend_achtige_methoden() {
		// TODO - implement Duck.ANDERE_eend_achtige_methoden
		throw new UnsupportedOperationException();
	}

}