package org.usfirst.frc.team484.robot.vision;

import java.util.ArrayList;

import edu.wpi.first.wpilibj.networktables.NetworkTable;

public class Vision extends Thread {
	//Settings
	private String leftCameraTableName = "leftCam";
	private String rightCameraTableName = "rightCam";
	public static final int imgWidth = 320;
	public static final int imgHeight = 240;
	public static final double radiansPerPixel = 0.00218;
	public static final double distanceBetweenCameras = 8.25;
	
	//Privately used variables
	private long period;
	private ArrayList<Contour> leftContours = new ArrayList<Contour>();
	private ArrayList<Contour> rightContours = new ArrayList<Contour>();
	private NetworkTable leftCameraTable; 
	private NetworkTable rightCameraTable;
	
	//Publicly accessible variables
	public VisionResults results;
	
	public Vision(long updateInterval) {
		period = updateInterval;
		leftCameraTable = NetworkTable.getTable("GRIP/" + leftCameraTableName);
		rightCameraTable = NetworkTable.getTable("GRIP/" + rightCameraTableName);
	}
	
	public void run() {
		while(true) {
			long startTime = System.currentTimeMillis();
			updateCalculations();
			long delta = System.currentTimeMillis() - startTime;
			if (delta < period) {
				pause(period - delta);
			}
		}
	}
	
	private void updateCalculations() {
		leftContours = Contour.getContoursFromTable(leftCameraTable);
		rightContours = Contour.getContoursFromTable(rightCameraTable);
		
		if (leftContours.size() > 1 && rightContours.size() > 1) {
			results = StereoVisionCalculator.run(leftContours, rightContours);
		} else if (leftContours.size() > 1) {
			results = LeftVisionCalculator.run(leftContours, rightContours);
		} else if (rightContours.size() > 1) {
			results = RightVisionCalculator.run(leftContours, rightContours);
		} else if (leftContours.size() > 0 || rightContours.size() > 0) {
			results = MinimalVisionCalculator.run(leftContours, rightContours);
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
