package figuren2D;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import figuren2D.Kreis;

class KreisTest {
	Kreis k;
	@BeforeEach
	void setUp() throws Exception {
		k= new Kreis(1);
	}

	@Test
	void testIfCircumferenceAndAreaAreCorrect() {
		assertEquals(k.umfang(),2* Math.PI, 0.000001);
		assertEquals(k.flaeche(),Math.PI, 0.000001);
	}
	@Test void testIfCircleNameIsCorrect() {
		assertEquals(k.name(),"Kreis mit Radius 1.0");
	}
	@Test
	void ifChangingRadiusToNegativeInvalidExceptionIsThrown()throws IllegalArgumentException{
		assertThrows(IllegalArgumentException.class, ()->k.setRadius(-1));
	}
	@Test
	void ifChangingRadiusToZeroInvalidExceptionIsThrown()throws IllegalArgumentException{
		assertThrows(IllegalArgumentException.class, ()->k.setRadius(0));
	}
}
