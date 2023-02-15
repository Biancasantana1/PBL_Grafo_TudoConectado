/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.Border;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

/**
 *
 * @author Paulo
 */
public class Conection extends StackPane{
    private String conected1, conected2;
    private Line line;
    private Point anchorPoint;
    private Point pointM;
    private int weight;
    private Component txcWeigh;
    
    /**
    * Construtor da classe Conecrion
    * @param tc1 Componente que se liga com o componente 2
    * @param tc2 Componente que se liga com o componente 1
    * @param weight Peso da conexão
    */
    public Conection (Component tc1, Component tc2, int weight){
        super();
        
        if(tc1.getPoint().getY() < tc2.getPoint().getY() || (tc1.getPoint().getY() == tc2.getPoint().getY() && tc1.getPoint().getX() < tc1.getPoint().getY())){
            this.conected1 = tc1.getAcronym();
            this.conected2 = tc2.getAcronym();

        }else{
            this.conected1 = tc2.getAcronym();
            this.conected2 = tc1.getAcronym();
        }
        
        this.line = new Line(tc1.getPoint().getX(),tc1.getPoint().getY(),tc2.getPoint().getX(),tc2.getPoint().getY());
        
        this.weight = weight;
        
        this.pointM = new Point((tc1.getPoint().getX()+tc2.getPoint().getX())/2,(tc1.getPoint().getY()+tc2.getPoint().getY())/2);
        
        this.txcWeigh = new Component(Integer.toString(weight), this.pointM, Paint.valueOf("white"), 1, Paint.valueOf("black"));
        
        
        double minX = this.pointM.getX()-Math.abs((tc1.getPoint().getX()-tc2.getPoint().getX())/2);
        double minY = this.pointM.getY()-Math.abs((tc1.getPoint().getY()-tc2.getPoint().getY())/2);
        
        if (Math.abs(this.pointM.getX() - minX) >= 15)
            this.setLayoutX(minX);
        else
            this.setLayoutX(minX - (15 - (this.pointM.getX() - minX)));
        
        if (Math.abs(this.pointM.getY() - minY)  >= 15)
            this.setLayoutY(minY);
        else
            this.setLayoutY(minY - (15 - (this.pointM.getY() - minY)));
        
        this.getChildren().add(this.line);
        this.getChildren().add(this.txcWeigh);
    }
    
    /**
    * Pega as siglas dos coponentes que a conexão conecta
    * @return vetor que contem as siglas dos conectados
    */
    public String[] getConecteds(){
        String[] conected = new String[2];
        conected[0] = this.conected1;
        conected[1] = this.conected2;
        return conected;
    }
    
    /**
    * Colore a conexão de vermelho para indicar que e um caminho
    */
    public void isWay(){
        this.line.setStroke(Paint.valueOf("red"));
        this.txcWeigh.setStrokeCircle(Paint.valueOf("red"));
    }
    /**
    * Colore a conexão de preto para indicar que não e um caminho
    */
    public void notWay(){
    this.line.setStroke(Paint.valueOf("black"));
    this.txcWeigh.setStrokeCircle(Paint.valueOf("black"));}
}
