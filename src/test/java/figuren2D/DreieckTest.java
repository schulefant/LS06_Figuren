package figuren2D;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DreieckTest {
	Dreieck d1 ;
	Dreieck d2 ;

	@BeforeEach
	void setUp() throws Exception {
		d1 = new Dreieck(14, 20, 8);
	}

	@Test
	void testIfCopiedTriangleIsATrueCopy() {
		d2 = new Dreieck(d1);
		assertTrue(d1.equals(d2),"Same Dimensions");
		assertNotSame(d1,d2);
	}
	@Test void testIfToStringIsCorrect() {
		String s = "Seite A: 14.0, Seite B: 20.0, Seite C: 8.0, Flaeche: " + d1.flaeche()
		+ ", Umfang: " + d1.umfang();
		assertEquals(d1.toString(),	s);
	}
	@Test void testIfTriangleNameIsCorrect() {
		assertEquals(d1.name(),"Dreieck mit den Seiten A: 14.0 B: 20.0 C: 8.0");
	}
	@Test void testIfSettingSidesChangesTriangle() {
		d1.setSeiteA(13);
		d2 = new Dreieck(13, 20, 8);
		assertTrue(d1.equals(d2));
		d1.setSeiteB(19);
		d2 = new Dreieck(13, 19, 8);
		assertTrue(d1.equals(d2));
		d1.setSeiteC(7.8);
		d2 = new Dreieck(13, 19, 7.8);
		assertTrue(d1.equals(d2));
	}
	@Test
	void testIfTrianglesCompareCorrectly() {
		d2 = new Dreieck(14, 20, 7.9);
		assertTrue(d1.compareTo(d2)>0);
		assertTrue(d2.compareTo(d1)<0);
		d2 = new Dreieck(14, 19.9, 8);		
		assertTrue(d1.compareTo(d2)>0);
		assertTrue(d2.compareTo(d1)<0);
		d2 = new Dreieck(13.9, 20, 8);		
		assertTrue(d1.compareTo(d2)>0);
		assertTrue(d2.compareTo(d1)<0);
		d2 = new Dreieck(14, 20, 8);
		assertTrue(d1.compareTo(d2) ==0);
	}
	@Test
	void testIfValidTriangleHasCorrectArea() {
		assertEquals(d1.flaeche(),43.7150,0.0001,"Area not correct");
	}
	@Test
	void testIfValidTriangleHasCorrectCircumference() {
		assertEquals(d1.umfang(),42.0,0.0001,"Circumference not correct");
	}
	@Test
	void testIfValidTriangleHasCorrectHeight() {
		assertEquals(d1.hoehe(),4.37149859887888,0.000001,"Height not correct");
	}
	@Test
	void ifTriangleIsNotValidThrowException()throws IllegalArgumentException{
		assertThrows(IllegalArgumentException.class, ()->d2 = new Dreieck(45,7,9),"Triangle not valid created");
	}
	@Test
	void ifChangingSideMakesTriangleInvalidExceptionIsThrown()throws IllegalArgumentException{
		assertThrows(IllegalArgumentException.class, ()->d1.setSeiteA(-1));
		assertThrows(IllegalArgumentException.class, ()->d1.setSeiteA(28));//Grenzwert getestet
		assertThrows(IllegalArgumentException.class, ()->d1.setSeiteB(22));//Grenzwert getestet
		assertThrows(IllegalArgumentException.class, ()->d1.setSeiteC(34));//Grenzwert getestet
	}
	@Test
	void ifEqualsOnTriangleIsCalledWithAWrongShapeItReturnsFalse(){
		assertFalse(d1.equals(new Kreis(3)),"A Circle can't be equal to a Triangle");
	}
	@Test
	void ifEqualsOnTriangleIsCalledWithNullItReturnsFalse(){
		assertFalse(d1.equals(null),"null cannnot be equal to any Figure.");
	}
}
