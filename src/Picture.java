/*
 *CS 2852 - 011
 *Fall 2017
 *Lab 1 - Dot 2 Dot Generator
 *Name: Donal Moloney
 *Created: 9/5/2017
 */
package Moloneyda;

import javafx.scene.canvas.Canvas;
import javafx.scene.control.Alert;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
*This class has methods that draw manipulate the .dot file
*/
public class Picture {
    private List<Dot> emptyList;
    private double dotXPosition;
    private double dotYPosition;
    private static final int enlargmentFactor = 600;
    private static final int dotOffSetFactor = 5;


    /*
    *This is the default constructor for the picture class
    */
    public Picture() {
    }

    /*
    *This one of the constructors for the picture class it initializes the type of data collection emptyList is
    * @param emptyList - the type of data collection emptyList is
    */
    public Picture(List<Dot> emptyList) {
        this.emptyList = emptyList;
    }

    /*
    *This one of the constructors for the picture class it initializes the type of data collection emptyList is
    * @param original - instance of the picture with the original dots picture in it
    * @param emptyList - a clone of the emptyList in original
    */
    public Picture(Picture original, List<Dot> emptyList) {
        this.emptyList = emptyList;
        for (int i = 0; i < original.emptyList.size(); i++) {
            this.emptyList.add(i, original.emptyList.get(i));
        }
    }

    /*
    *This method returns the index of the lowest critical value in the array of critical values
    *@param file - the file dots image will be written to
    *@ returns void - this method does not return anything
    */
    public void save(File file) throws IOException {
        try {
            File newFile = file;
            FileWriter writer = new FileWriter(newFile, true);
            for (int i = 0; i < emptyList.size(); i++) {
                String horizontal = Double.toString(emptyList.get(i).getHorizontalPosition());
                String vertical = Double.toString(emptyList.get(i).getVerticalPoisttion());
                writer.write(horizontal);
                writer.write(",");
                writer.write(" ");
                writer.write(vertical);
                writer.write("\n");
            }
            writer.close();
        } catch (IOException e) {
            throw new IOException();
        }
    }

    /*
    *This method removes dots from the picture depending on their critical value and that the user specified
    * @param numberDesired - the number of dots the user wants to keep in the picture
    *@ returns void - the method does not return anything
    */
    public void removeDots(int numberDesired) {
        int previous;
        int next;
        if (emptyList.size() > numberDesired) {
            ArrayList<Double> cV = new ArrayList<Double>();
            for (int i = 0; i < emptyList.size(); i++) {
                previous = i - 1;
                next = i + 1;
                if (previous < 0) {
                    previous = emptyList.size() - 1;
                } else if (next >= emptyList.size()) {
                    next = 0;
                }
                cV.add(emptyList.get(i).calculateCriticalValue(emptyList.get(previous), emptyList.get(next)));
            }
            int dotsRemove = emptyList.size() - numberDesired;
            for (int j = 0; j < dotsRemove; j++) {
                int lowestValue = getLowestCv(cV);

                cV.remove(lowestValue);
                emptyList.remove(lowestValue);
            }
        } else if (numberDesired < 3) {
            throw new IllegalArgumentException();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText("Check, input!");
            alert.setContentText("The input value is larger than the number of dots!");
            alert.showAndWait();
        }
    }

    /*
    *This method loads a .dot file
    *@param file - the file the user chose to load
    *@throws IOException - if an error occurs setting the file's data into the scanner
    *@ returns void - this method does not return anything
    */
    public void load(File file) throws IOException {
        Scanner fileData = new Scanner(new FileReader(file));
        String aLine;
        while (fileData.hasNextLine()) {
            aLine = fileData.nextLine();
            String[] parts = aLine.split(",");
            String xString = parts[0];
            String yString = parts[1];
            if (yString.substring(0, 1).equals(" ")) {
                yString = yString.replace(" ", "");
            }
            double xCordinate = Double.parseDouble(xString);
            double yCoordinate = Double.parseDouble(yString);
            Dot newDots = new Dot(xCordinate, yCoordinate);
            emptyList.add(newDots);
        }
        fileData.close();
    }

    /*
    *This method draws the dots of the image
    * @param canvas - where the dots are drawn upon
    * @returns void - this method does not return anything
    */
    public void drawDots(Canvas canvas) {
        for (int i = 0; i < emptyList.size(); i++) {
            dotXPosition = emptyList.get(i).getHorizontalPosition() * enlargmentFactor - dotOffSetFactor;
            dotYPosition = (1 - emptyList.get(i).getVerticalPoisttion()) * enlargmentFactor - dotOffSetFactor;
            canvas.getGraphicsContext2D().fillOval(dotXPosition, dotYPosition, 10, 10);
        }
    }

    /*
    * This method draws the lines of lines of the image
    * @param canvas - where the dots are drawn upon
    * @returns void - this method does not return anything
    */
    public void drawLines(Canvas canvas) {
        double beginXLocation = emptyList.get(0).getHorizontalPosition() * enlargmentFactor;
        double beginYLocation = (1 - emptyList.get(0).getVerticalPoisttion()) * enlargmentFactor;
        canvas.getGraphicsContext2D().setLineWidth(2.0);
        canvas.getGraphicsContext2D().beginPath();
        canvas.getGraphicsContext2D().moveTo(beginXLocation, beginYLocation);
        for (int i = 1; i < emptyList.size(); i++) {
            double xPos = emptyList.get(i).getHorizontalPosition() * enlargmentFactor;
            double yPos = (1 - emptyList.get(i).getVerticalPoisttion()) * enlargmentFactor;
            canvas.getGraphicsContext2D().lineTo(xPos, yPos);
        }
        canvas.getGraphicsContext2D().lineTo(beginXLocation, beginYLocation);
        canvas.getGraphicsContext2D().closePath();
        canvas.getGraphicsContext2D().stroke();
    }

    /*
    *This method alerts the user to the number of dots in the uploaded file
    *@ returns void - this method does not return anything
    */
    public void getNumberOfDots() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText("File's Total Dots");
        alert.setContentText("The file has " + emptyList.size() + " dots in it");
        alert.showAndWait();
    }

    /*
    *This method returns the index of the lowest critical value in the array of critical values
    * @param cV - the array of critical values
    *@ returns int - the index of the lowest critical value
    */
    public int getLowestCv(ArrayList<Double> cV) {
        int lowestValueIndex = 0;
        for (int k = 0; k < cV.size(); k++) {
            if (cV.get(lowestValueIndex) > cV.get(k)) {
                lowestValueIndex = k;
            }
        }
        return lowestValueIndex;
    }
}

