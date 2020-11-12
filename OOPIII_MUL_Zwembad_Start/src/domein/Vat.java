package domein;

//VUL DE KLASSE VERDER AAN
public class Vat implements Runnable{

    private final Tafel tafel;
    private int inhoud;

    public Vat(int emmers, Tafel tafel) {
        inhoud = emmers;
        this.tafel = tafel;
    }

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(inhoud > 0) {
			tafel.vulEmmer();
			inhoud--;
		}
		tafel.setVatIsLeeg();
		System.out.println("vat is leeg");
		
	}
}
