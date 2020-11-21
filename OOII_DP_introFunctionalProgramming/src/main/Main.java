package main;

import java.util.function.Supplier;


import domein.Brok;

public class Main {
	public static void main(String[] args) {
		new Main().run();
	}

	private void run() {
		System.out.println("it begins");
		Supplier<String> supplier = () -> "hier is een string";
		System.out.println(supplier.get());
		Supplier<Brok> brokSupplier = () -> new Brok("Coli");
		System.out.println(brokSupplier.get());
		System.out.println(foo(() -> new Brok("soep")));
		System.out.println(foo(Brok::new));
	}
	
	public <T> T foo(Supplier<T> supplier) {
		return supplier.get();
	}
 
}
