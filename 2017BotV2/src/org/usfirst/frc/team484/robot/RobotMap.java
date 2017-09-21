package org.usfirst.frc.team484.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {

	// Drivetrain
	public static final int leftWheel = 0;
	public static final int rightWheel = 1;

	// Pneumatics
	public static final int gearUpSolenoid = 0;
	public static final int gearDownSolenoid = 1;
	public static final int gearOpenSolenoid = 3;
	public static final int gearCloseSolenoid = 2;
	public static final int gearOutSolenoid = 5;
	public static final int gearInSolenoid = 4;

	// Climber
	public static final int leftClimber = 2;
	public static final int rightClimber = 3;

	// HID
	public static final int driverJoystick = 0;
	public static final int operatorJoystick = 1;
	public static final int programmerJoystick = 2;

	// Encoders
	public static final int leftWheelEncA = 0;
	public static final int leftWheelEncB = 1;

	public static final int rightWheelEncA = 2;
	public static final int rightWheelEncB = 3;

	public static final int frontWheelEncA = 4;
	public static final int frontWheelEncB = 5;

	public static final int rearWheelEncA = 6;
	public static final int rearWheelEncB = 7;

	// Gyros
	public static final int topGyro = 0;
	public static final int bottonGyro = 1;

	// IR Sensors
	public static final int gearSensor = 2;
}
