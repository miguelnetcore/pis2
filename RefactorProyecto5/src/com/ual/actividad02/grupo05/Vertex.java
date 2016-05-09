/**
 * 
 */
package com.ual.actividad02.grupo05;

/**
 * @author Manel
 *
 */
public class Vertex{

	private double x;
	private double y;
	
	public Vertex() {
		this.x=0;
		this.y=0;
	}
	
	public Vertex(double x, double y){
		this.x=x;
		this.y=y;
	}

	/**
	 * @return the x
	 */
	public double getX() {
		return x;
	}

	/**
	 * @param x the x to set
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * @return the y
	 */
	public double getY() {
		return y;
	}

	/**
	 * @param y the y to set
	 */
	public void setY(int y) {
		this.y = y;
	}

	
	@Override
	public String toString() {
		return "Vertex [x=" + x + ", y=" + y + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Vertex){
			if( this.x == ((Vertex) obj).getX() && this.y == ((Vertex) obj).getY())
				return true;
		}
		return false;
	}
	
	
}
