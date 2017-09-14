package org.usfirst.frc.team484.robot.vision;

import java.util.ArrayList;

public class StereoVisionCalculator {
	public static VisionResults run(ArrayList<Contour> leftContours, ArrayList<Contour> rightContours) {

		Contour leftCamLeftContour;
		Contour leftCamRightContour;
		if (leftContours.size() > 1) {
			if (leftContours.get(0).centerX < leftContours.get(1).centerX) {
				leftCamLeftContour = leftContours.get(0);
				leftCamRightContour = leftContours.get(1);
			} else {
				leftCamLeftContour = leftContours.get(1);
				leftCamRightContour = leftContours.get(0);
			}
		} else {
			leftCamLeftContour = leftContours.get(0);
			leftCamRightContour = leftContours.get(0);
		}
		Contour rightCamLeftContour;
		Contour rightCamRightContour;
		if (leftContours.size() > 1) {
			if (rightContours.get(0).centerX < rightContours.get(1).centerX) {
				rightCamLeftContour = rightContours.get(0);
				rightCamRightContour = rightContours.get(1);
			} else {
				rightCamLeftContour = rightContours.get(1);
				rightCamRightContour = rightContours.get(0);
			}
		} else {
			rightCamLeftContour = rightContours.get(0);
			rightCamRightContour = rightContours.get(0);
		}
		double centerXLeftCam = (leftCamLeftContour.centerX + leftCamRightContour.centerX)/2.0;
		double centerXRightCam = (rightCamLeftContour.centerX + rightCamRightContour.centerX)/2.0;

		double leftAngle = Math.PI / 2.0 - Vision.radiansPerPixel * (centerXLeftCam - Vision.imgWidth/2.0);
		double rightAngle = Math.PI / 2.0 - Vision.radiansPerPixel * (Vision.imgWidth/2.0 - centerXRightCam);
		double xDistFromLeftCam = Vision.distanceBetweenCameras * sin(rightAngle) * cos(leftAngle)/(sin(rightAngle)*cos(leftAngle)+cos(rightAngle)*sin(leftAngle));
		double targetX,targetY;
		if (leftContours.size() > 1 && rightContours.size() > 1) {
			targetY = tan(leftAngle) * xDistFromLeftCam;
			targetX = xDistFromLeftCam - Vision.distanceBetweenCameras / 2.0;
			targetY = 0.0011 * Math.pow(targetY, 2) + 1.0313*targetY - 3.8513;
		} else {
			targetX = 0;
			targetY = 0;
		}
		double targetAngle = 28.648 * Vision.radiansPerPixel * (Vision.imgWidth - rightCamRightContour.centerX - leftCamLeftContour.centerX);
		//Adjust target angle using measured error
		targetAngle = 0.8558 * targetAngle + 0.0163;
		return new VisionResults("Stereo Vision", targetX, targetY, targetAngle);
	}

	//This makes life easier
	private static double sin(double a) {
		return Math.sin(a);
	}
	private static double cos(double a) {
		return Math.cos(a);
	}
	private static double tan(double a) {
		return Math.tan(a);
	}
}
