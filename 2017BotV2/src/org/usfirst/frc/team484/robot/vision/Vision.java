package org.usfirst.frc.team484.robot.vision;

import java.util.ArrayList;

import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.tables.ITable;
import edu.wpi.first.wpilibj.tables.ITableListener;

public class Vision extends Thread {
	//Settings
	private static final String leftCameraTableName = "left";
	private static final String rightCameraTableName = "right";
	public static final int imgWidth = 1279;
	public static final int imgHeight = 359;
	public static final double radiansPerPixel = 0.00092;
	public static final double distanceBetweenCameras = 8.25;
	
	//Privately used variables
	private static long period;
	private static ArrayList<Contour> leftContours = new ArrayList<Contour>();
	private static ArrayList<Contour> rightContours = new ArrayList<Contour>();
	private static NetworkTable leftCameraTable; 
	private static NetworkTable rightCameraTable;
	
	//Publicly accessible variables
	public static VisionResults results = new VisionResults();
	
	public Vision(long updateInterval) {
		period = updateInterval;
		leftCameraTable = NetworkTable.getTable("Vision/" + leftCameraTableName);
		rightCameraTable = NetworkTable.getTable("Vision/" + rightCameraTableName);
		NetworkTable.setUpdateRate(0.01);
	}
	
	public void run() {
		ITableListener tableListener = new ITableListener() {
			@Override
			public void valueChanged(ITable source, String key, Object value, boolean isNew) {
				updateCalculations();
			}
		};
		leftCameraTable.addTableListener(tableListener, true);
		rightCameraTable.addTableListener(tableListener, true);
		while(true) {
			//long startTime = System.currentTimeMillis();
			//updateCalculations();
			//long delta = System.currentTimeMillis() - startTime;
			//if (delta < period) {
				pause(period);
			//}
		}
	}
	
	private static void updateCalculations() {
		leftContours = Contour.getContoursFromTable(leftCameraTable);
		rightContours = Contour.getContoursFromTable(rightCameraTable);
		
		if (leftContours.size() >= 1 && rightContours.size() >= 1) {
			results = StereoVisionCalculator.run(leftContours, rightContours);
		} else {
			results = new VisionResults();
		}
	}
	
	private static void pause(long millis) {
		try {
			Thread.sleep(millis);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
