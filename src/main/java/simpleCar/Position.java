/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simpleCar;

/**
 *
 * @author chensiyuan
 */
public class Position {
    
    private double xPos;
    private double yPos;
    
    public Position(double xPos, double yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
    }

    public double getXPosition() {
        return xPos;
    }

    public double getYPosition() {
        return yPos;
    }

    public void setXPosition(double xPos) {
        this.xPos = xPos;
    }

    public void setYPosition(double yPos) {
        this.yPos = yPos;
    }
    
}
