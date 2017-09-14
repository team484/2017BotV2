package org.usfirst.frc.team484.robot.vision;

public class VisionResults {
	public double targetX = 0; //positive is to the right of robot
	public double targetY = 0; //positive is in front of robot
	public double targetRot = 0; //radians Positive means robot needs to rotate counterclockwise to compensate
	public VisionResults() {
	}
	public VisionResults(String visionMode, double x, double y, double rot) {
		targetX = x;
		targetY = y;
		targetRot = rot;
	}
	public void updateTime() {
	}
}
