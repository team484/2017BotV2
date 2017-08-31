package org.usfirst.frc.team484.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	
	//Drivetrain
	public static int leftWheel = 0;
	public static int rightWheel = 1;
	public static int frontWheel = 2;
	public static int rearWheel = 3;	
	
	//Pneumatics
	public static int gearUpSolenoid = 0;
	public static int gearDownSolenoid = 1;
	public static int gearOpenSolenoid = 2;
	public static int gearCloseSolenoid = 3;
	public static int gearOutSolenoid = 4;
	public static int gearInSolenoid = 5;
	
	//Climber
	public static int leftClimber = 6;
	public static int rightClimber = 7;
	
	//HID
	public static int driverJoystick = 0;
	public static int operatorJoystick = 1;
	
	//Encoders
	public static int leftWheelEncA = 0;
	public static int leftWheelEncB = 1;
	
	public static int rightWheelEncA = 2;
	public static int rightWheelEncB = 3;
	
	public static int frontWheelEncA = 4;
	public static int frontWheelEncB = 5;
	
	public static int rearWheelEncA = 4;
	public static int rearWheelEncB = 5;
	
	//Gyros
	public static int topGyro = 0;
	public static int bottonGyro = 1;
	
	//IR Sensors
	public static int gearSensor = 2;
}
