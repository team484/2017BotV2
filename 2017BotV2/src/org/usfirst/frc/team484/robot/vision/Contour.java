package org.usfirst.frc.team484.robot.vision;

import java.util.ArrayList;

import edu.wpi.first.wpilibj.tables.ITable;

public class Contour {
    
    public double centerX;
    
    public Contour(double centerX) {
        this.centerX = centerX;
    }
    
    
    public static ArrayList<Contour> getContoursFromTable(ITable table) {
        double[] centerX = table.getNumberArray("centerX", new double[0]);
        
        ArrayList<Contour> contours = new ArrayList<>();
        for(int i = 0; i < centerX.length; i++)
            contours.add(new Contour(centerX[i]));
        
        return contours;
    }
    
}
