package org.usfirst.frc.team484.robot;

public class LocationTracker extends Thread {
	public double x,y,dist = 0;
	public void run() {
		while(true) {
			double deltaDistance = (RobotIO.leftWheelEnc.getDistance() + RobotIO.rightWheelEnc.getDistance()) / 2.0 - dist;
			dist = (RobotIO.leftWheelEnc.getDistance() + RobotIO.rightWheelEnc.getDistance()) / 2.0;

			x += Math.cos(Math.toRadians(90 - RobotIO.getRot())) * deltaDistance;

			y += Math.sin(Math.toRadians(90 - RobotIO.getRot())) * deltaDistance;
		}
	}
}
