package org.usfirst.frc.team484.robot;

public class RobotSettings {
	
	//Go Distance PID Values
		public static double drivekP = 1;
		public static double drivekI = 0;
		public static double drivekD = 0;
		public static double driveErrorMargin = 2;
		public static double driveSpeedMargin = 1;
		
	//Go Distance PID Values
		public static double slidekP = 1;
		public static double slidekI = 0;
		public static double slidekD = 0;
		public static double slideErrorMargin = 2;
		public static double slideSpeedMargin = 1;
		
	//Rotate Angle PID Values
		public static double rotkP = 1;
		public static double rotkI = 0;
		public static double rotkD = 0;
		public static double rotErrorMargin = 5;
		public static double rotSpeedMargin = 2;
		
	//Vision Slide PID Values
		public static double visionSlidekP = 1;
		public static double visionSlidekI = 0;
		public static double visionSlidekD = 0;
		
	//Climber Motor Speed Multipliers
		public static double leftClimbSpeedMultiplier = 1;
		public static double rightClimbSpeedMultiplier = leftClimbSpeedMultiplier;
	
	//Horizontal Wheel Rotation Speed Multipliers
		public static double frontRotWheelRotMultiplier = 0.0;
		public static double rearRotWheelRotMultiplier = 1;
	
	//Buttons
		public static int climberButton = 1;
		public static int gearPickupButton = 2;
		public static int gearShootButton = 3;
	
	//Encoders
		//Sets distance per pulse of each encoder in inches. Positive is right or forward
		public static double rlWheelEncDPP = 1;
		public static double rrWheelEncDPP = 1;
		public static double fRotWheelEncDPP = 1;
		public static double rRotWheelEncDPP = 1;
		
	//Gear Sensor
		public static double gearSensorMaxValue = 200;
		public static double gearSensorMinValue = 120;
		
	//Invert Motor
		public static boolean invertLeftMotor = false;
		public static boolean invertRightMotor = false;
		public static boolean invertFrontMotor = false;
		public static boolean invertRearMotor = true;
		public static boolean invertLeftClimber = false;
		public static boolean invertRightClimber = false;
}
