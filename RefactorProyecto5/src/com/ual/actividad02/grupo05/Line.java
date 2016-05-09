package com.ual.actividad02.grupo05;

import java.util.ArrayList;

// Linea

public class Line {

	private Vertex a;
	private Vertex b;

	public Line() {

		this.a = new Vertex();
		this.b = new Vertex();
	}

	/**
	 * @Crea linea a traves de dos vertices
	 */
	public Line(Vertex x, Vertex y) {

		this.a = x;
		this.b = y;

	}

	/**
	 * @Crea linea a traves ordenada en el origen y pendiente
	 */
	public Line(double b, double pendiente) {

		this.a = new Vertex(0, b);
		this.b = new Vertex(2, 2 * pendiente + b);

	}

	/**
	 * @Crea linea a traves de vertice y pendiente
	 */
	public Line(Vertex x, double pendiente) {

		this.a = x;
		this.b = new Vertex(x.getX() + 1, pendiente + x.getY());

	}

	/**
	 * @Crea linea a traves de 3 componentes
	 */
	public Line(double a, double b, double c) {

		this.a = new Vertex(0, 0);
		this.b = new Vertex(1, (-a / b));

	}

	/**
	 * @Crea linea a traves de una cordenada, siendo el flag el que distingue
	 *       entre cordenada en abscisa u ordenadas
	 */
	public Line(double cordenada, boolean flag) {

		if (flag == true) {

			this.a = new Vertex(cordenada, 0);
			this.b = new Vertex(cordenada, 1);
		} else {

			this.a = new Vertex(0, cordenada);
			this.b = new Vertex(1, cordenada);

		}
	}

	/**
	 * @Return Un string con la ecuacion de la recta en forma implicita
	 */
	public static String representacionImplicita(Line linea) {

		Vertex vectorDireccional = new Vertex(linea.getB().getX() - linea.getA().getX(),
				linea.getB().getY() - linea.getA().getY());

		double componenteX = vectorDireccional.getY();
		double componenteY = -vectorDireccional.getX();
		double libre = ((-linea.getA().getX()) * vectorDireccional.getY())
				+ ((linea.getA().getY()) * vectorDireccional.getX());

		return componenteX + "x+" + componenteY + "y+" + libre + "= 0";

	}

	/**
	 * @Return True si las rectas se intersecan.
	 */
	public static Boolean interseccion(Line line1, Line line2) {

		if (((line1.getB().getY() - line1.getA().getY())
				/ (line1.getB().getX() - line1.getA().getX())) == ((line2.getB().getY() - line2.getA().getY())
						/ (line2.getB().getX() - line2.getA().getX()))) {
			return false;
		} else {
			return true;
		}

	}

	/**
	 * @return the a
	 */
	public Vertex getA() {
		return a;
	}

	/**
	 * @param a
	 *            the a to set
	 */
	public void setA(Vertex a) {
		this.a = a;
	}

	/**
	 * @return the b
	 */
	public Vertex getB() {
		return b;
	}

	/**
	 * @param b
	 *            the b to set
	 */
	public void setB(Vertex b) {

		this.b = b;

	}

}
