package com.ual.actividad02.grupo05;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class LineTest {

	Line linea;

	@Before
	public void setUp() throws Exception {

		this.linea = new Line(new Vertex(2, 0), new Vertex(2, 4));

	}

	@Test
	public void dosPuntosTest() {
		this.linea = new Line(new Vertex(1, 4), new Vertex(5, 2));

		assertTrue(this.linea.getA().equals(new Vertex(1, 4)));
		assertTrue(this.linea.getB().equals(new Vertex(5, 2)));
	}

	@Test
	public void pendienteOrdenadaTest() {
		this.linea = new Line(5, 4);

		assertTrue(this.linea.getA().equals(new Vertex(0, 5)));
		assertTrue(this.linea.getB().equals(new Vertex(2, 13)));
	}

	@Test
	public void pendientePuntoTest() {
		this.linea = new Line(new Vertex(0, 5), 4);

		assertTrue(this.linea.getA().equals(new Vertex(0, 5)));
		assertTrue(this.linea.getB().equals(new Vertex(1, 9)));
	}

	@Test
	public void coefRepresentImplicitaTest() {
		this.linea = new Line(4,2,3);
		assertTrue(this.linea.getB().equals(new Vertex(1, -2)));
	}

	@Test
	public void abcisaTest() {
		this.linea = new Line(4, true);

		assertTrue(this.linea.getB().equals(new Vertex(4, 1)));
	}

	@Test
	public void ordenadaTest() {
		this.linea = new Line(5, false);
		assertTrue(this.linea.getB().equals(new Vertex(1, 5)));
	}

	@Test
	public void representImplicTest(){
		this.linea = new Line(new Vertex(1, 5), -2);
		assertEquals("-2.0x+-1.0y+7.0= 0",Line.representacionImplicita(this.linea));
		
	}
	@Test
	public void intersecan(){
		this.linea = new Line(new Vertex(0, 0), new Vertex(1, 1));
		Line linea2 = new Line(new Vertex(1, 0), new Vertex(2, 1));
		assertFalse(Line.interseccion(linea, linea2));
		linea2 = new Line(new Vertex(0, 1), new Vertex(1,0 ));
		assertTrue(Line.interseccion(linea, linea2));
		
	}
}
