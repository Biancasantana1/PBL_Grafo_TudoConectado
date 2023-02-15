/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.util.Arrays;

/**
 *
 * @author Paulo
 */
public class Point {
    private double coordinateX;
    private double coordinateY;
    
    public Point(){
    }
    
    public Point(double x, double y){
        this.coordinateX = x;
        this.coordinateY = y;
    }

    public double getX() {
        return coordinateX;
    }

    public void setX(double coordinateX) {
        this.coordinateX = coordinateX;
    }

    public void setY(double coordinateY) {
        this.coordinateY = coordinateY;
    }

    public double getY() {
        return coordinateY;
    }
    
    public double distanceTo(Point point){
        double distance = Math.pow((Math.pow((this.coordinateX - point.getX()), 2) + Math.pow((this.coordinateY - point.getY()), 2)), 0.5);
        return distance;
    }
}
