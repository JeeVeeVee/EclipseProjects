package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.api.Assertions;

import static domein.InitDobbelsteen.*;
import domein.Dobbelsteen;

class DobbelsteenTest {
	
	private Dobbelsteen dobbelsteen;
	
	private static Stream<Integer> addFixture(){
		return Stream.of(DEFAULT, MIN_GRENS, MAX_GRENS, 10, 20, 30, 50, 100, 1000);
	}
	
	public void before(int grootte) {
		dobbelsteen = new Dobbelsteen(grootte);	
	}
	
	@ParameterizedTest
	@ValueSource(ints = {Integer.MIN_VALUE, -100 ,-4, -1, MIN_GRENS -1 ,0, MAX_GRENS + 1, Integer.MAX_VALUE})
	public void foutieveDobbelsteen(int grootte) {
		Assertions.assertThrows(IllegalArgumentException.class, () -> new Dobbelsteen(grootte));
	}
	
	@ParameterizedTest
	@MethodSource("addFixture")
	public void correcteDobbelsteen(int grootte) {
		before(grootte);
		Assertions.assertEquals(grootte, dobbelsteen.getAantalVlakken());	
	}
	
	private final int AANTAL = 100;
	
	@ParameterizedTest
	@MethodSource("addFixture")
	public void gooiBinnenGrenzen (int grootte) {
		before(grootte);
		int[] resultaat = new int[dobbelsteen.getAantalVlakken()];
		
		for (int i = 0; i < dobbelsteen.getAantalVlakken() * AANTAL; i++) {
			int worp = dobbelsteen.gooi();
			Assertions.assertTrue(worp >= 1 && worp <= dobbelsteen.getAantalVlakken());
			resultaat[worp - 1] ++;
			
		}
		Arrays.stream(resultaat).forEach((e -> Assertions.assertTrue(e > 0)));
		
	}

	
}
