package com.ual.actividad02.grupo05;



import java.util.ArrayList;

public class Polygon{
	
	public ArrayList<Vertex> vertexList;
	private ArrayList<Polygon> T;
	public Polygon() {
		
		this.vertexList=new ArrayList<Vertex>(2);
				
	}
	public Polygon(ArrayList<Vertex> vertexList){
		
		this.vertexList=vertexList;
		
	}
	
	/**
	 * @return the vertexList
	 */
	public ArrayList<Vertex> getVertexList() {
		
		return vertexList;
		
	}
	/**
	 * @param vertexList the vertexList to set
	 */
	public void setVertexList(ArrayList<Vertex> vertexList) {
		
		this.vertexList = vertexList;
		
	}
	/**
	 * @return true if the polygon is convex
	 */
	public boolean isConvex(){
		if(this.vertexList.size()<3) return false;
		
		double zcrossproduct = 0; 
		
		for(int i = 0;i<this.vertexList.size()-2;i++){
			if(i < this.vertexList.size()-3){
				double dx1 = this.vertexList.get(i+1).getX()-this.vertexList.get(i).getX();
				double dy1 = this.vertexList.get(i+1).getY()-this.vertexList.get(i).getY();
				double dx2 = this.vertexList.get(i+2).getX()-this.vertexList.get(i+1).getX();
				double dy2 = this.vertexList.get(i+2).getY()-this.vertexList.get(i+1).getY();
				if (((dx1*dy2) - (dy1*dx2)) >= 0 && zcrossproduct<0 || (((dx1*dy2) - (dy1*dx2))<= 0 && zcrossproduct > 0)) return false; 
				zcrossproduct = ((dx1*dy2) - (dy1*dx2));
			}else{
				double dx1 = this.vertexList.get(i+1).getX()-this.vertexList.get(i).getX();
				double dy1 = this.vertexList.get(i+1).getY()-this.vertexList.get(i).getY();
				double dx2 = this.vertexList.get(0).getX()-this.vertexList.get(i+1).getX();
				double dy2 = this.vertexList.get(0).getY()-this.vertexList.get(i+1).getY();
				if (((dx1*dy2) - (dy1*dx2)) >= 0 && zcrossproduct<0 || (((dx1*dy2) - (dy1*dx2))<= 0 && zcrossproduct > 0)) return false; 
				zcrossproduct = ((dx1*dy2) - (dy1*dx2));
			}
			
		}
		return true;
	}
	/**
	 * @return polygon area
	 */
	private double areaPoligono() {
		double area = 0;
		Vertex a, b;
		for (int i = 0; i < vertexList.size(); i++) {
			a = vertexList.get(i);
			if (vertexList.size() - i == 1)
				b = vertexList.get(0);
			else
				b = vertexList.get(i + 1);
			area += ((a.getX() * b.getY()) - (b.getX() * a.getY()));
		}
		return area / 2;
	}

	
	/**
	 * @return the Vertex El centroide del del poligono
	 */
	public Vertex centroidePoligono() {
		Vertex a, b;
		double x = 0;
		double y = 0;
		double area = areaPoligono();
		for (int i = 0; i < vertexList.size(); i++) {
			a = vertexList.get(i);
			if (vertexList.size() - i == 1)
				b = vertexList.get(0);
			else
				b = vertexList.get(i+1);
			x += (b.getX() + a.getX()) * (a.getX() * b.getY() - b.getX() * a.getY());
			y += (b.getY() + a.getY()) * (a.getX() * b.getY() - b.getX() * a.getY());
		}
		x = x / (6 * area);
		y = y / (6 * area);
		return (new Vertex(x, y));
	}
	
	/**
	 * @return the Vertex on the left v1
	 */
	public Vertex getLeft(Vertex v1) {
		int position = 0;
		position = getPosition(v1);
		if(position == 0)
			return vertexList.get(vertexList.size()-1);
		return vertexList.get(position-1);
	}
	
	/**
	 * @return the Vertex on the right v1
	 */
	public Vertex getRight(Vertex v1) {
		int position = 0;
		position = getPosition(v1);
		if(position == vertexList.size())
			return vertexList.get(0);
		return vertexList.get(position+1);
	}
	
	/**
	 * @return the position in the array
	 */
	private int getPosition(Vertex v1) {
		int i = 0;
		while(!vertexList.get(i).equals(v1))
			i++;
		if(i > vertexList.size())
			return -1;
		return i;
	}
	
	/**
	 * @return Triangulacion con el algoritmo de oreja
	 */
	public ArrayList<Polygon> algoritmoOreja() {
		T = new ArrayList<Polygon>();
		System.out.println(this.vertexList.size());
		Polygon auxiliar = new Polygon((ArrayList<Vertex>) this.vertexList.clone());
		int current = 0; // Comenzamos con el vertice 1.
		Boolean formaTriangulo;
		while((auxiliar.vertexList.size() >= 3)){		
			formaTriangulo = true;
			if(current+1> auxiliar.vertexList.size()-1){
				current=0;
			}
			Vertex verticeActual = auxiliar.vertexList.get(current);
			Vertex verticeLeft =  auxiliar.getLeft(verticeActual);
			Vertex verticeRight =  auxiliar.getRight(verticeActual);
			
			Polygon polyPeque = new Polygon();
			polyPeque.vertexList.add(verticeActual);
			polyPeque.vertexList.add(verticeLeft);
			polyPeque.vertexList.add(verticeRight);
			
			
			// Comprobamos si el polipeque esta completamente contenido en el poligono
			if(area_triangulo_signo(polyPeque) < 0)//son mayores de 180
			{
				//System.out.println("No esta completamente contenido");
				formaTriangulo = false;
			}
			
			
			// comprobamos que no hay ningun punto contenido dentro de polipeque.
			Vertex punto = new Vertex();
			for(int i=0;i<=auxiliar.vertexList.size()-1;i++){
				punto = auxiliar.vertexList.get(i);
				if(!(punto.equals(verticeActual) || punto.equals(verticeLeft) || punto.equals(verticeRight))){
					if(estaDentro(punto, polyPeque)){
						//System.out.println("Hay un punto dentro de Polipeque "+punto);
						formaTriangulo = false;
						break;
					}
				}
			}
			
			
			//Si finalmente no hay ningun punto dentro de polypeque y esta completamente contenido lo a�adimos a la soluci�n. 
			if(formaTriangulo){
				T.add(polyPeque);
				auxiliar.vertexList.remove(verticeActual);
				current=0;
			}
			else{
				current++;
			}		
		}
		muestraT();
		return T;
	}
	

	/**
	 * @Imprime por consola el contenido de la triangulacion
	 */
	
	public void muestraT(){
		System.out.println("----------------");
		System.out.println("La solucion tiene "+T.size());
		for(int j=0;j<T.size();j++){
			System.out.println(T.get(j).vertexList);
		}
		System.out.println("La solucion Tiene "+T.size() );
	}

	
	/**
	 * @return la orientaci�n del poligono
	 */
	public double orientacion(){
		double result = 0;
		
		Vertex A1, A2, A3 = new Vertex();
		A1 = vertexList.get(0);
		A2 = vertexList.get(1);
		A3 = vertexList.get(2);
		//(A1.x - A3.x) * (A2.y - A3.y) - (A1.y - A3.y) * (A2.x - A3.x)
		double result1 = A1.getX() - A3.getX();
		double result2 = A2.getY() - A3.getY();
		double result3 = A1.getY() - A3.getY();
		double result4 = A2.getX() - A3.getX();
		result = (result1 * result2) - (result3 * result4);
		return result;
	}
	
	
	/**
	 * Indica si el punto p esta contenido dentro de un poligono.  
	 *
	 */
	 
	
	public static boolean estaDentro(Vertex punto, Polygon p){
		Polygon subPoligonoA = new Polygon();
		Polygon subPoligonoB = new Polygon();
		Polygon subPoligonoC = new Polygon();
		
		subPoligonoA.vertexList.add(p.vertexList.get(0));
		subPoligonoA.vertexList.add(p.vertexList.get(1));
		subPoligonoA.vertexList.add(punto);
		
		subPoligonoB.vertexList.add(p.vertexList.get(0));
		subPoligonoB.vertexList.add(punto);
		subPoligonoB.vertexList.add(p.vertexList.get(2));
		
		subPoligonoC.vertexList.add(punto);
		subPoligonoC.vertexList.add(p.vertexList.get(1));
		subPoligonoC.vertexList.add(p.vertexList.get(2));
		
		if(p.orientacion() >= 0.0)
			if(subPoligonoA.orientacion() >= 0.0)
				if(subPoligonoB.orientacion() >= 0.0)
					if(subPoligonoC.orientacion() >= 0.0)
						return true;
		
		if(p.orientacion() <= 0.0)
			if(subPoligonoA.orientacion() <= 0.0)
				if(subPoligonoB.orientacion() <= 0.0)
					if(subPoligonoC.orientacion() <= 0.0)
						return true;
		return false;
	}


	/**
	 * Indica el signo del area de un poligono
	 * Area_tri�ngulo_signo=(ax*by-ay*bx+ay*cx-ax*cy+bx*cy-cx*by)/2.0
	 */

	public static double area_triangulo_signo(Polygon p){
		double area = 0;
		Vertex a, b, c = new Vertex();
		a = p.vertexList.get(1);
		b = p.vertexList.get(0);
		c = p.vertexList.get(2);
		//Area=(ax*by					-ay*bx					+ay*cx - 
		area = (a.getX() * b.getY()) - (a.getY() * b.getX()) + (a.getY() * c.getX()) - 
				//ax*cy					+bx*cy					-cx*by)
				(a.getX() * c.getY()) + (b.getX() * c.getY()) - (c.getX() * b.getY());
		return area / 2;
	}
	
}
