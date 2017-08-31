package org.usfirst.frc.team484.robot.vision;

import java.util.ArrayList;

public class StereoVisionCalculator {
	public static VisionResults run(ArrayList<Contour> leftContours, ArrayList<Contour> rightContours) {
		if (!verifyTargetLock(leftContours)) {
			if (!verifyTargetLock(rightContours)) {
				System.err.println("failed to get stereo lock on left and right cameras");
				return MinimalVisionCalculator.run(leftContours, rightContours);
			}
			System.err.println("failed to get stereo lock on left camera");
				return RightVisionCalculator.run(leftContours, rightContours);
		}
		if (!verifyTargetLock(rightContours)) {
			System.err.println("failed to get stereo lock on right camera");
			return LeftVisionCalculator.run(leftContours, rightContours);
		}
		
		leftContours.sort((c1,c2) -> (int)(c2.area - c1.area));
		Contour leftCamLeftContour;
		Contour leftCamRightContour;
		if (leftContours.get(0).centerX < leftContours.get(1).centerX) {
			leftCamLeftContour = leftContours.get(0);
			leftCamRightContour = leftContours.get(1);
		} else {
			leftCamLeftContour = leftContours.get(1);
			leftCamRightContour = leftContours.get(0);
		}
		
		rightContours.sort((c1,c2) -> (int)(c2.area - c1.area));
		Contour rightCamLeftContour;
		Contour rightCamRightContour;
		if (rightContours.get(0).centerX < rightContours.get(1).centerX) {
			rightCamLeftContour = rightContours.get(0);
			rightCamRightContour = rightContours.get(1);
		} else {
			rightCamLeftContour = rightContours.get(1);
			rightCamRightContour = rightContours.get(0);
		}
		
		double centerXLeftCam = (leftCamLeftContour.centerX + leftCamRightContour.centerX)/2.0;
		double centerXRightCam = (rightCamLeftContour.centerX + rightCamRightContour.centerX)/2.0;
		
		double leftAngle = Math.PI / 2.0 - Vision.radiansPerPixel * (centerXLeftCam - Vision.imgWidth/2.0);
		double rightAngle = Math.PI / 2.0 - Vision.radiansPerPixel * (Vision.imgWidth/2.0 - centerXRightCam);
		double xDistFromLeftCam = Vision.distanceBetweenCameras * sin(rightAngle) * cos(leftAngle)/(sin(rightAngle)*cos(leftAngle)+cos(rightAngle)*sin(leftAngle));
		double targetY = tan(leftAngle) * xDistFromLeftCam;
		double targetX = xDistFromLeftCam - Vision.distanceBetweenCameras / 2.0;
		
		double robotRot = Math.PI/4.0 - Math.atan(rightCamRightContour.height/leftCamLeftContour.height);
		return new VisionResults("Stereo Vision", targetX, targetY, robotRot);
	}
	private static boolean verifyTargetLock(ArrayList<Contour> contours) {
		contours.sort((c1,c2) -> (int)(c2.area - c1.area));
		Contour left;
		Contour right;
		if (contours.get(0).centerX < contours.get(1).centerX) {
			left = contours.get(0);
			right = contours.get(1);
		} else {
			left = contours.get(1);
			right = contours.get(0);
		}
		
		if (left.height * 2.0 < right.height || left.height / 2.0 > right.height) {
			return false;
		}
		
		if (left.area * 2.0 < right.area || left.area / 2.0 > right.area) {
			return false;
		}
		
		if (left.centerY + left.height / 2.0 < right.centerY || left.centerY - left.height / 2.0 > right.centerY) {
			return false;
		}
		
		return true;
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
