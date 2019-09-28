/*
 *CS 2852 - 011
 *Fall 2017
 *Lab 1 - Dot 2 Dot Generator
 *Name: Donal Moloney
 *Created: 9/5/2017
 */
package Moloneyda;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/*
 * This class creates a Dot
 * This class extends Picture
 */
public class Dot extends Picture {
    private double xCoordinate;
    private double yCoordinate;

    /*
    * This is the Dot constructor and it passes the x, y coordinates of the new dot to its set methods
    * @param yCoordinate - the height of the dot as a double
    * @param yCoordinate - The dots vertical position from the origin
    */
    public Dot(double xCoordinate, double yCoordinate) {
        setHorizontalPosition(xCoordinate);
        setVerticalPoistion(yCoordinate);
    }

    /*
    *This method calculates the critical value of a dot in empty list
    * @param previous - the previous dot in empty list
    * @param next - the next dot in the empty list
     */
    public double calculateCriticalValue(Dot previous, Dot next) {
        double cv;
        double previousX = next.getHorizontalPosition();
        double previousY = previous.getVerticalPoisttion();
        double dotY = this.getVerticalPoisttion();
        double dotX = this.getHorizontalPosition();
        double nextY = next.getVerticalPoisttion();
        double nextX = next.getHorizontalPosition();

        double d12CV = Math.sqrt(Math.pow(previousX - dotX, 2) + Math.pow(previousY - dotY, 2));
        double d23CV = Math.sqrt(Math.pow(dotX - nextX, 2) + Math.pow(dotY - nextY, 2));
        double d13CV = Math.sqrt(Math.pow(previousX - nextX, 2) + Math.pow(previousY - nextY, 2));
        cv = d12CV + d23CV - d13CV;
        return cv;
    }

    /*
    * This class sets the horizontal position of a dot
    * @param xCoordinate - The dots horizontal position from the origin
    */
    public void setHorizontalPosition(double xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    /*
    * This class sets the vertical position of a dot
    * @param yCoordinate - The dots vertical position from the origin
    */
    public void setVerticalPoistion(double yCoordinate) {
        this.yCoordinate = yCoordinate;
    }

    /*
    * This class gets returns the horizontal position of a dot
    * @returns this.xCoordinate- the Dots instance of its x coordinate
    */
    public double getHorizontalPosition() {
        return this.xCoordinate;
    }

    /*
    * This class gets returns the vertical position of a dot
    * @returns this.yCoordinate- the Dots instance of its y coordinate
    */
    public double getVerticalPoisttion() {
        return this.yCoordinate;
    }


}
