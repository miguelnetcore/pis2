/**
 * 
 */
package com.ual.actividad02.grupo05;

import static org.junit.Assert.*;


import java.util.ArrayList;

import org.junit.*;

/**
 * @author Manel Mena
 *
 */
public class PolygonTest {

	/**
	 * @throws java.lang.Exception
	 * 
	 */
	Polygon polygonConcav;
	Polygon polygonConvex;
	Polygon polygonSquare;
	Polygon polygonTriangle;
	Polygon example;
	Polygon example2;
	Polygon flecha;
	
	@Before
	public void setUp() throws Exception {
		ArrayList<Vertex> aux = new ArrayList<Vertex>(2);
		aux.add(new Vertex(0,0));
		aux.add(new Vertex(-3,3));
		aux.add(new Vertex(0,5));
		aux.add(new Vertex(3,3));
		polygonConvex = new Polygon(aux);
		ArrayList<Vertex> aux1 = new ArrayList<Vertex>(2);
		aux1.add(new Vertex(0,0));
		aux1.add(new Vertex(-3,3));
		aux1.add(new Vertex(0,5));
		aux1.add(new Vertex(-1,3));
		aux1.add(new Vertex(3,5));
		aux1.add(new Vertex(3,3));
		polygonConcav = new Polygon(aux1);
		ArrayList<Vertex> aux2 = new ArrayList<Vertex>(2);
		aux2.add(new Vertex(-50,0));
		aux2.add(new Vertex(50,0));
		aux2.add(new Vertex(50,50));
		aux2.add(new Vertex(-50,50));	
		aux2.add(new Vertex(-100,100));
		polygonSquare = new Polygon(aux2);
		ArrayList<Vertex> aux3 = new ArrayList<Vertex>(2);
		aux3.add(new Vertex(0,0));
		aux3.add(new Vertex(0,5));
		aux3.add(new Vertex(5,0));
		polygonTriangle = new Polygon(aux3);
		ArrayList<Vertex> aux4 = new ArrayList<Vertex>(2);
		aux4.add(new Vertex(0,0));
		aux4.add(new Vertex(3,2));
		aux4.add(new Vertex(6,1));
		aux4.add(new Vertex(8,2));
		aux4.add(new Vertex(7,7));
		aux4.add(new Vertex(5,8));
		aux4.add(new Vertex(4,3));
		aux4.add(new Vertex(3,9));
		aux4.add(new Vertex(-1,7));
		aux4.add(new Vertex(2,6));
		aux4.add(new Vertex(-3,2));
		example = new Polygon(aux4);
		
		
		ArrayList<Vertex> aux5 = new ArrayList<Vertex>(2);
		aux5.add(new Vertex(2.6,1));
		aux5.add(new Vertex(4.9,2.2));
		aux5.add(new Vertex(6.6,1.4));
		aux5.add(new Vertex(7.6,1.5));
		aux5.add(new Vertex(7.6,4.2));
		aux5.add(new Vertex(5.5,5.6));
		aux5.add(new Vertex(5.1,2.7));
		aux5.add(new Vertex(4.4,5.5));
		aux5.add(new Vertex(1.9,4.2));
		aux5.add(new Vertex(3.3,3.9));
		aux5.add(new Vertex(1,2.7));
		example2 = new Polygon(aux5);	
		
		ArrayList<Vertex> aux6 = new ArrayList<Vertex>(2);
		
		aux6.add(new Vertex(0,0));
		aux6.add(new Vertex(30,0));
		aux6.add(new Vertex(20,10));
		aux6.add(new Vertex(70,60));
		aux6.add(new Vertex(60,70));
		aux6.add(new Vertex(10,20));
		aux6.add(new Vertex(0,30));	
		flecha = new Polygon(aux6);		
	}

	@Test
	public void isConvex() {
		
		assertTrue(polygonConvex.isConvex());
		assertFalse(polygonConcav.isConvex());
	}
	@Test
	public void centroide() {

		assertEquals(polygonConvex.centroidePoligono().toString(),((new Vertex(-0.0,2.6666666666666665)).toString()));
		
	}

	@Test
	public void getLeftTest(){
		Vertex v1 = new Vertex(0, 0), v2Expected = polygonConvex.getVertexList().get(3);
		Vertex actual = new Vertex();
		actual = polygonConvex.getLeft(v1);
		assertEquals(v2Expected, actual);
	}
	
	@Test
	public void getLeftTest1(){
		Vertex v1 = new Vertex(0, 5), v2Expected = polygonConcav.getVertexList().get(1);
		Vertex actual = new Vertex();
		actual = polygonConcav.getLeft(v1);
		assertEquals(v2Expected, actual);
	}
	
	@Test
	public void getRightTest(){
		Vertex v1 = new Vertex(0, 0), v2Expected = polygonConvex.getVertexList().get(1);
		assertEquals(v2Expected, polygonConvex.getRight(v1));
	}
	
	@Test
	public void getRightTest1(){
		Vertex v1 = new Vertex(0, 5), v2Expected = polygonConcav.getVertexList().get(3);
		assertEquals(v2Expected, polygonConcav.getRight(v1));
	}
	
	@Test 
	public void orientacionTest(){
		double expected = -25.0;
		double actual = 0.0;
		actual = polygonTriangle.orientacion();
		assertEquals(expected, actual, 0.0);
	}
	
	
	@Test
	public void algoritmoOrejaTest(){
		//creamos las listas de poligonos que vamos a comparar
		ArrayList<Polygon> expected = new ArrayList<Polygon>();
		ArrayList<Polygon> actual = new ArrayList<Polygon>();
		//creamos los triangulos que vamos a añadir a expected
		Polygon triangleA = new  Polygon();
		Polygon triangleB = new Polygon();
		Polygon triangleC = new Polygon();
		Polygon triangleD = new Polygon();
		Polygon triangleE = new Polygon();
		Polygon triangleF = new Polygon();
		Polygon triangleG = new Polygon();
		Polygon triangleH = new Polygon();
		Polygon triangleI = new Polygon();
		
		// Creamos los vertices;
		Vertex auxVertex1 =  new Vertex(2.6,1);
		Vertex auxVertex2 =  new Vertex(4.9,2.2);
		Vertex auxVertex3 =  new Vertex(6.6,1.4);
		Vertex auxVertex4 =  new Vertex(7.6,1.5);
		Vertex auxVertex5 =  new Vertex(7.6,4.2);
		Vertex auxVertex6 =  new Vertex(5.5,5.6);
		Vertex auxVertex7 =  new Vertex(5.1,2.7);
		Vertex auxVertex8 =  new Vertex(4.4,5.5);
		Vertex auxVertex9 =  new Vertex(1.9,4.2);
		Vertex auxVertex10 =  new Vertex(3.3,3.9);
		Vertex auxVertex11 =  new Vertex(1,2.7);
		
		
		// Añadimos los vertices esperados en A
		triangleA.vertexList.add(auxVertex1); triangleA.vertexList.add(auxVertex11); triangleA.vertexList.add(auxVertex2);
		// Añadimos los vertices esperados en B
		triangleB.vertexList.add(auxVertex3); triangleA.vertexList.add(auxVertex2); triangleA.vertexList.add(auxVertex4); 
		// Añadimos los vertices esperados en C
		triangleC.vertexList.add(auxVertex4); triangleA.vertexList.add(auxVertex2); triangleA.vertexList.add(auxVertex5);
		// Añadimos los vertices esperados en D
		triangleD.vertexList.add(auxVertex6); triangleA.vertexList.add(auxVertex5); triangleA.vertexList.add(auxVertex7);	
		// Añadimos los vertices esperados en E
		triangleE.vertexList.add(auxVertex5); triangleA.vertexList.add(auxVertex2); triangleA.vertexList.add(auxVertex7); 		
		// Añadimos los vertices esperados en F
		triangleF.vertexList.add(auxVertex2); triangleA.vertexList.add(auxVertex11); triangleA.vertexList.add(auxVertex7); 				
		// Añadimos los vertices esperados en G
		triangleG.vertexList.add(auxVertex9); triangleA.vertexList.add(auxVertex8); triangleA.vertexList.add(auxVertex10);
		// Añadimos los vertices esperados en H
		triangleH.vertexList.add(auxVertex8); triangleA.vertexList.add(auxVertex7); triangleA.vertexList.add(auxVertex10); 
		// Añadimos los vertices esperados en I
		triangleI.vertexList.add(auxVertex7); triangleA.vertexList.add(auxVertex11); triangleA.vertexList.add(auxVertex10);
		
		
		expected.add(triangleA);
		expected.add(triangleB);
		expected.add(triangleC);
		expected.add(triangleD);
		expected.add(triangleE);
		expected.add(triangleF);
		expected.add(triangleG);
		expected.add(triangleH);
		expected.add(triangleI);
		
		
		actual = example2.algoritmoOreja();

		assertEquals(expected.size(), actual.size());
		//assertEquals(actual.get(i).vertexList.get(0), expected.get(i).vertexList.get(0));
		for(int i=0;i<actual.size();i++){
			assertEquals(actual.get(i).vertexList.get(0), expected.get(i).vertexList.get(0));
		}
		
		
		
		
	}
	
	@Test
	public void algoritmoOrejaTest1(){
		//creamos las listas de poligonos que vamos a comparar
		ArrayList<Polygon> actual = new ArrayList<Polygon>();
		int expected = 9;
		//utilizamos el algoritmoOreja para calcular la solucion
		actual = example.algoritmoOreja();
		
		assertEquals(expected, actual.size());
	}
	
	@Test
	public void algoritmoOrejaTest2(){
		//creamos las listas de poligonos que vamos a comparar
		ArrayList<Polygon> T = new ArrayList<Polygon>();
		int expected = 9;
		//utilizamos el algoritmoOreja para calcular la solucion
		//actual = example2.algoritmoOrejaPrueba();
		T = example2.algoritmoOreja();
	
		assertEquals(expected, T.size());
	}
	
	@Test
	public void algoritmoOrejaTestSquare(){
		//creamos las listas de poligonos que vamos a comparar
		ArrayList<Polygon> T = new ArrayList<Polygon>();
		int expected = 3;
		//utilizamos el algoritmoOreja para calcular la solucion
		//actual = example2.algoritmoOrejaPrueba();
		T = polygonSquare.algoritmoOreja();
	
		assertEquals(expected, T.size());
	}
	
	@Test
	public void algoritmoOrejaTestFlecha(){
		//creamos las listas de poligonos que vamos a comparar
		ArrayList<Polygon> T = new ArrayList<Polygon>();
		int expected = 5;
		//utilizamos el algoritmoOreja para calcular la solucion
		//actual = example2.algoritmoOrejaPrueba();
		T = flecha.algoritmoOreja();
	
		assertEquals(expected, T.size());
	}
}
