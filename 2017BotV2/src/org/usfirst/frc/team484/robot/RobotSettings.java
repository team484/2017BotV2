package org.usfirst.frc.team484.robot;

public class RobotSettings {
	
	//Go Distance PID Values
		public static double drivekP = 0.02;
		public static double drivekI = 0;
		public static double drivekD = 0;
		public static double driveErrorMargin = 5;
		public static double driveSpeedMargin = 0.1;
		
	//Rotate Angle PID Values
		public static double rotkP = 0.04;
		public static double rotkI = 0;
		public static double rotkD = 0.4;
		public static double rotErrorMargin = 5;
		public static double rotSpeedMargin = 2;
		
	//Vision driving rotation PID Values (Not implemented)
		public static double visionSlidekP = 0.2;
		public static double visionSlidekI = 0.03;
		public static double visionSlidekD = 0.1;
		
	//Vision Rot PID Values (Needs precision)
		public static double visionRotkP = 0.15;
		public static double visionRotkI = 0.0;
		public static double visionRotkD = 1.0;
		
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
		//Sets distance per pulse of each encoder in inches. Positive is forward
		public static double leftWheelEncDPP = -0.06882;
		public static double rightWheelEncDPP = 0.068;
		
	//Gear Sensor
		public static double gearSensorMaxValue = 2700;
		public static double gearSensorMinValue = 1500;
		
	//Invert Motor
		public static boolean invertLeftMotor = true;
		public static boolean invertRightMotor = true;
		public static boolean invertFrontMotor = false;
		public static boolean invertRearMotor = false;
		public static boolean invertLeftClimber = false;
		public static boolean invertRightClimber = false;
}
