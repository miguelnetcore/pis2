package com.ual.actividad02.grupo05;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.*;

import javax.swing.text.html.ListView;



public class MainOverviewController {

	 // fx:id="lineTF1"
    private TextField lineTF1; // Value injected by FXMLLoader

     // fx:id="resetPolygonButton"
    private Button resetPolygonButton; // Value injected by FXMLLoader

     // fx:id="polygonXTextField"
    private TextField polygonXTextField; // Value injected by FXMLLoader

     // fx:id="lineTF4"
    private TextField lineTF4; // Value injected by FXMLLoader

     // fx:id="lineTF3"
    private TextField lineTF3; // Value injected by FXMLLoader

     // fx:id="lineTF2"
    private TextField lineTF2; // Value injected by FXMLLoader

     // fx:id="lineLabel1"
    private Label lineLabel1; // Value injected by FXMLLoader

     // fx:id="polygonList"
    private ListView<String> polygonList; // Value injected by FXMLLoader

     // fx:id="lineLabel3"
    private Label lineLabel3; // Value injected by FXMLLoader

     // fx:id="lineLabel2"
    private Label lineLabel2; // Value injected by FXMLLoader

     // fx:id="lineLabel4"
    private Label lineLabel4; // Value injected by FXMLLoader

     // fx:id="centroideButton"
    private Button centroideButton; // Value injected by FXMLLoader

     // fx:id="lineOkButton"
    private Button lineOkButton; // Value injected by FXMLLoader

     // fx:id="triangButton"
    private Button triangButton; // Value injected by FXMLLoader

     // fx:id="polygonCanvas"
    private Canvas polygonCanvas; // Value injected by FXMLLoader

     // fx:id="lineResultadoLabel"
    private Label lineResultadoLabel; // Value injected by FXMLLoader

     // fx:id="lineCB"
    private ComboBox<String> lineCB; // Value injected by FXMLLoader

     // fx:id="polygonMensajeLabel"
    private Label polygonMensajeLabel; // Value injected by FXMLLoader

     // fx:id="lineResetButton"
    private Button lineResetButton; // Value injected by FXMLLoader

     // fx:id="addVertexPolygonButton"
    private Button addVertexPolygonButton; // Value injected by FXMLLoader

     // fx:id="polygonYTextField"
    private TextField polygonYTextField; // Value injected by FXMLLoader

     // fx:id="isConvexButton"
    private Button isConvexButton; // Value injected by FXMLLoader

     // fx:id="polyErrorLabel"
    private Label polyErrorLabel; // Value injected by FXMLLoader
    
     // fx:id="linea1Button"
    private Button linea1Button; // Value injected by FXMLLoader
    
     // fx:id="linea2Button"
    private Button linea2Button; // Value injected by FXMLLoader

    
    private Polygon poligono;
    
    private Line linea;
    
    private Line linea1;
    private Line linea2;
    
    private ArrayList<Vertex> vertexList;
    
    

	// Reference to the main application.
	private Main mainApp;

	public MainOverviewController() {
		
		this.vertexList = new ArrayList<Vertex>(2);
		this.polygonList = new ListView<String>();
		ObservableList<String> items = FXCollections.observableArrayList();
		polygonList.setItems(items);
	}
	public void setMainApp(Main mainApp) {
        
		this.mainApp = mainApp;
		
    }
	
	
	void introCord(ActionEvent event) {
		
		if(this.isNumeric(this.polygonXTextField.getText()) && this.isNumeric(this.polygonYTextField.getText())){
			double x = Double.parseDouble(this.polygonXTextField.getText());
			double y = Double.parseDouble(this.polygonYTextField.getText());
			if(x>=-100 && x<=100 && y>=-100 && y<=100){
				this.polyErrorLabel.setText("");
				
				this.polygonXTextField.setText("");
				this.polygonYTextField.setText("");
				
				Vertex v = new Vertex(x, y);
				this.vertexList.add(v);
				polygonList.getItems().add("("+x+","+y+")");
				if(isPolygon()){
					this.poligono = new Polygon(vertexList);
					this.isConvexButton.setDisable(false);
					this.centroideButton.setDisable(false);
					this.triangButton.setDisable(false);
					drawPolygon();
				}
			}else{
				this.polyErrorLabel.setText("Introduce numeros entre -100 y 100");
			}
		}else{
			this.polyErrorLabel.setText("Introduce numeros entre -100 y 100");
		}
		
		
	}

	
	void resetPolygon(ActionEvent event) {
		
		ObservableList<String> items = FXCollections.observableArrayList();
		polygonList.setItems(items);
		this.vertexList = new ArrayList<Vertex>(2);
		this.polygonCanvas.getGraphicsContext2D().clearRect(0, 0, this.polygonCanvas.getWidth(), this.polygonCanvas.getHeight());
		this.polygonMensajeLabel.setText("");
		
		
	}
	   
	  
	void isConvex(ActionEvent event) {
		if(this.poligono.isConvex()) this.polygonMensajeLabel.setText("Este poligono es convexo");
		else this.polygonMensajeLabel.setText("Este poligono es concavo");
	}

	
	void drawCentroid(ActionEvent event) {
		
		GraphicsContext gc = this.polygonCanvas.getGraphicsContext2D();
		gc.strokeOval((this.poligono.centroidePoligono().getX()*2)+250, (this.poligono.centroidePoligono().getY()*-2)+250, 2, 2);
		
	}
	
	void drawTriangulacion(ActionEvent event) {
		
		GraphicsContext gc = this.polygonCanvas.getGraphicsContext2D();
		gc.clearRect(0, 0, this.polygonCanvas.getWidth(), this.polygonCanvas.getHeight());
		gc.setFill(Color.GREEN);
        gc.setStroke(Color.GREEN);
        gc.setLineWidth(3);
        for (Polygon poligonoAux  : this.poligono.algoritmoOreja()) {
        	ArrayList<Vertex> vertexListAux = poligonoAux.getVertexList();
        	
        	for(int i = 0; i<vertexListAux.size();i++){
            	if(i==vertexListAux.size()-1){
            		gc.strokeLine((vertexListAux.get(i).getX()*2)+250, (vertexListAux.get(i).getY()*-2)+250, (vertexListAux.get(0).getX()*2)+250, (vertexListAux.get(0).getY()*-2)+250);
            		break;
            	}
            	gc.strokeLine((vertexListAux.get(i).getX()*2)+250, (vertexListAux.get(i).getY()*-2)+250, (vertexListAux.get(i+1).getX()*2)+250, (vertexListAux.get(i+1).getY()*-2)+250); 	
            }
        	
		}
        
        
	}
	
	
	private boolean isPolygon(){
		
		if(this.vertexList.size()>=3) return true;
		
		return false;
		
	}
	
	private boolean isNumeric(String str)  
	{  
	  try  
	  {  
	    double d = Double.parseDouble(str);  
	  }  
	  catch(NumberFormatException nfe)  
	  {  
	    return false;  
	  }  
	  return true;  
	}
	
	private void drawPolygon(){
		
		GraphicsContext gc = this.polygonCanvas.getGraphicsContext2D();
		gc.clearRect(0, 0, this.polygonCanvas.getWidth(), this.polygonCanvas.getHeight());
		gc.setFill(Color.GREEN);
        gc.setStroke(Color.BLUE);
        gc.setLineWidth(3);
        for(int i = 0; i<this.vertexList.size();i++){
        	if(i==vertexList.size()-1){
        		gc.strokeLine((vertexList.get(i).getX()*2)+250, (vertexList.get(i).getY()*-2)+250, (vertexList.get(0).getX()*2)+250, (vertexList.get(0).getY()*-2)+250);
        		break;
        	}
        	gc.strokeLine((vertexList.get(i).getX()*2)+250, (vertexList.get(i).getY()*-2)+250, (vertexList.get(i+1).getX()*2)+250, (vertexList.get(i+1).getY()*-2)+250); 	
        } 
        
	}
	
    void okLine(ActionEvent event) {
		try{
			if(this.lineCB.getValue().equals("Selecciona tipo de introducción")){
	    		
	    	}else if(this.lineCB.getValue().equals("Dados 2 puntos")){
	    		this.linea = new Line(new Vertex(Double.parseDouble(this.lineTF1.getText()),Double.parseDouble(this.lineTF2.getText())),new Vertex(Double.parseDouble(this.lineTF3.getText()),Double.parseDouble(this.lineTF4.getText())));
	    		this.lineResultadoLabel.setText(Line.representacionImplicita(this.linea));
	    	}else if(this.lineCB.getValue().equals("Dada pendiente M y ordenada en origen B")){
	    		this.linea = new Line(Double.parseDouble(this.lineTF1.getText()),Double.parseDouble(this.lineTF2.getText()));
	    		this.lineResultadoLabel.setText(Line.representacionImplicita(this.linea));	
	    	}else if(this.lineCB.getValue().equals("Dado punto pendiente")){
	    		this.linea = new Line(new Vertex(Double.parseDouble(this.lineTF1.getText()),Double.parseDouble(this.lineTF2.getText())),Double.parseDouble(this.lineTF3.getText()));
	    		this.lineResultadoLabel.setText(Line.representacionImplicita(this.linea));
	    	}else if(this.lineCB.getValue().equals("Dados Coeficientes ABC")){
	    		this.linea = new Line(Double.parseDouble(this.lineTF1.getText()),Double.parseDouble(this.lineTF2.getText()),Double.parseDouble(this.lineTF2.getText()));
	    		this.lineResultadoLabel.setText(Line.representacionImplicita(this.linea));
	    	}else if(this.lineCB.getValue().equals("Dado Valor de Abscisa")){
	    		this.linea = new Line(Double.parseDouble(this.lineTF1.getText()),true);
	    		this.lineResultadoLabel.setText(Line.representacionImplicita(this.linea));
	    	}else
	    	{
	    		this.linea = new Line(Double.parseDouble(this.lineTF1.getText()),false);
	    		this.lineResultadoLabel.setText(Line.representacionImplicita(this.linea));
	    	}   	
		}catch(Exception e){
			this.lineResultadoLabel.setText("Introduce Valores apropiados");
		}
		
    }

    
    void resetLine(ActionEvent event) {
    	
    	this.lineCB.getSelectionModel().select(0);
    	this.lineLabel1.setDisable(true);
    	this.lineLabel2.setDisable(true);
    	this.lineLabel3.setDisable(true);
    	this.lineLabel4.setDisable(true);
    	this.lineTF1.setText("");
    	this.lineTF2.setText("");
    	this.lineTF3.setText("");
    	this.lineTF4.setText("");
    	this.lineTF1.setDisable(true);
    	this.lineTF2.setDisable(true);
    	this.lineTF3.setDisable(true);
    	this.lineTF4.setDisable(true);
    	this.lineOkButton.setDisable(true);
    	this.lineResetButton.setDisable(true);
    	this.lineResultadoLabel.setText("");
    	
    }
    
    @FXML
    void lineSeleccion(ActionEvent event) {
    	
    	if(this.lineCB.getValue().equals("Selecciona tipo de introducción")){
    		this.lineLabel1.setDisable(true);
        	this.lineLabel2.setDisable(true);
        	this.lineLabel3.setDisable(true);
        	this.lineLabel4.setDisable(true);
        	this.lineTF1.setText("");
        	this.lineTF2.setText("");
        	this.lineTF3.setText("");
        	this.lineTF4.setText("");
        	this.lineTF1.setDisable(true);
        	this.lineTF2.setDisable(true);
        	this.lineTF3.setDisable(true);
        	this.lineTF4.setDisable(true);
        	this.lineOkButton.setDisable(true);
        	this.lineResetButton.setDisable(true);
    		
    	}else if(this.lineCB.getValue().equals("Dados 2 puntos")){
    		this.lineOkButton.setDisable(false);
        	this.lineResetButton.setDisable(false);
        	this.lineLabel1.setDisable(false);
        	this.lineLabel1.setText("x1");
        	this.lineLabel2.setDisable(false);
        	this.lineLabel2.setText("y1");
        	this.lineLabel3.setDisable(false);
        	this.lineLabel3.setText("x2");
        	this.lineLabel4.setDisable(false);
        	this.lineLabel4.setText("y2");
        	this.lineTF1.setDisable(false);
        	this.lineTF2.setDisable(false);
        	this.lineTF3.setDisable(false);
        	this.lineTF4.setDisable(false);
    		
    	}else if(this.lineCB.getValue().equals("Dada pendiente M y ordenada en origen B")){
    		this.lineOkButton.setDisable(false);
        	this.lineResetButton.setDisable(false);
        	this.lineLabel1.setDisable(false);
        	this.lineLabel1.setText("b");
        	this.lineLabel2.setDisable(false);
        	this.lineLabel2.setText("m");
        	this.lineLabel3.setDisable(true);
        	this.lineLabel3.setText("");
        	this.lineLabel4.setDisable(true);
        	this.lineLabel4.setText("");
        	this.lineTF1.setDisable(false);
        	this.lineTF2.setDisable(false);
        	this.lineTF3.setDisable(true);
        	this.lineTF4.setDisable(true);
        	
    	}else if(this.lineCB.getValue().equals("Dado punto pendiente")){
    		this.lineOkButton.setDisable(false);
        	this.lineResetButton.setDisable(false);
        	this.lineLabel1.setDisable(false);
        	this.lineLabel1.setText("x1");
        	this.lineLabel2.setDisable(false);
        	this.lineLabel2.setText("y1");
        	this.lineLabel3.setDisable(false);
        	this.lineLabel3.setText("m");
        	this.lineLabel4.setDisable(true);
        	this.lineLabel4.setText("");
        	this.lineTF1.setDisable(false);
        	this.lineTF2.setDisable(false);
        	this.lineTF3.setDisable(false);
        	this.lineTF4.setDisable(true);
        	
        	
    	}else if(this.lineCB.getValue().equals("Dados Coeficientes ABC")){
    		this.lineOkButton.setDisable(false);
        	this.lineResetButton.setDisable(false);
        	this.lineLabel1.setDisable(false);
        	this.lineLabel1.setText("a");
        	this.lineLabel2.setDisable(false);
        	this.lineLabel2.setText("b");
        	this.lineLabel3.setDisable(false);
        	this.lineLabel3.setText("c");
        	this.lineLabel4.setDisable(true);
        	this.lineLabel4.setText("");
        	this.lineTF1.setDisable(false);
        	this.lineTF2.setDisable(false);
        	this.lineTF3.setDisable(false);
        	this.lineTF4.setDisable(true);
        	
    	}else if(this.lineCB.getValue().equals("Dado Valor de Abscisa")){
    		this.lineOkButton.setDisable(false);
        	this.lineResetButton.setDisable(false);
        	this.lineLabel1.setDisable(false);
        	this.lineLabel1.setText("x0");
        	this.lineLabel2.setDisable(true);
        	this.lineLabel2.setText("");
        	this.lineLabel3.setDisable(true);
        	this.lineLabel3.setText("");
        	this.lineLabel4.setDisable(true);
        	this.lineLabel4.setText("");
        	this.lineTF1.setDisable(false);
        	this.lineTF2.setDisable(true);
        	this.lineTF3.setDisable(true);
        	this.lineTF4.setDisable(true);
        	
    	}else if(this.lineCB.getValue().equals("Dado Valor de ordenadas")){
    		this.lineOkButton.setDisable(false);
        	this.lineResetButton.setDisable(false);
        	this.lineLabel1.setDisable(false);
        	this.lineLabel1.setText("y0");
        	this.lineLabel2.setDisable(true);
        	this.lineLabel2.setText("");
        	this.lineLabel3.setDisable(true);
        	this.lineLabel3.setText("");
        	this.lineLabel4.setDisable(true);
        	this.lineLabel4.setText("");
        	this.lineTF1.setDisable(false);
        	this.lineTF2.setDisable(true);
        	this.lineTF3.setDisable(true);
        	this.lineTF4.setDisable(true);
        	
    	}   	
    }
    
    void guardarL1(ActionEvent event) {
    	
    	if(linea2 == null){
    		this.linea1 = new Line(this.linea.getA(),this.linea.getB());
    	}else{
    		this.linea1 = new Line(this.linea.getA(),this.linea.getB());
    		this.lineResultadoLabel.setText(Line.interseccion(linea1, linea2) ? "Las lineas se intersecan":"Las lineas no se intersecan");
    	}
    	
    }

    
    void guardarL2(ActionEvent event) {
    	if(linea1 == null){
    		this.linea2 = new Line(this.linea.getA(),this.linea.getB());
    	}else{
    		this.linea2 = new Line(this.linea.getA(),this.linea.getB());
    		this.lineResultadoLabel.setText(Line.interseccion(linea1, linea2) ? "Las lineas se intersecan":"Las lineas no se intersecan");
    		System.out.println(this.linea1.getA().toString());
    		System.out.println(this.linea2.getA().toString());
    	}
    }
}
