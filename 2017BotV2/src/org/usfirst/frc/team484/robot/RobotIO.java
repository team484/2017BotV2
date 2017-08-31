package org.usfirst.frc.team484.robot;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Talon;

public class RobotIO {
	
	//Drivetrain
	public static Talon leftWheel;
	public static Talon rightWheel;
	public static Talon frontWheel;
	public static Talon rearWheel;
	public static RobotDrive robotDrive;
	
	//Pneumatics
	public static DoubleSolenoid gearRotSolenoid;
	public static DoubleSolenoid gearGripSolenoid;
	public static DoubleSolenoid gearShootSolenoid;
	public static Compressor compressor;
	
	//Climber
	public static Talon leftClimber;
	public static Talon rightClimber;
	
	//HID
	public static Joystick driverJoystick;
	public static Joystick operatorJoystick;
	
	//Encoders
	public static Encoder leftWheelEnc;
	public static Encoder rightWheelEnc;
	public static Encoder frontWheelEnc;
	public static Encoder rearWheelEnc;
	
	//Gyros
	public static AnalogGyro topGyro;
	public static AnalogGyro bottomGyro;
	
	//IR Sensors
	public static AnalogInput gearSensor;
	
	public RobotIO() {
		//Drivetrain
		leftWheel = new Talon(RobotMap.leftWheel);
		rightWheel = new Talon(RobotMap.rightWheel);
		frontWheel = new Talon(RobotMap.frontWheel);
		rearWheel = new Talon(RobotMap.rearWheel);
		robotDrive = new RobotDrive(leftWheel, rightWheel);
		
		//Pneumatics, forward is considered the extended stae of the piston
		gearRotSolenoid = new DoubleSolenoid(RobotMap.gearDownSolenoid, RobotMap.gearUpSolenoid);
		gearGripSolenoid = new DoubleSolenoid(RobotMap.gearOpenSolenoid, RobotMap.gearCloseSolenoid);
		gearShootSolenoid = new DoubleSolenoid(RobotMap.gearOutSolenoid, RobotMap.gearInSolenoid);
		compressor = new Compressor(0);
		
		//Cliumber
		leftClimber = new Talon(RobotMap.leftClimber);
		rightClimber = new Talon(RobotMap.rightClimber);
		
		//HID
		driverJoystick = new Joystick(RobotMap.driverJoystick);
		operatorJoystick = new Joystick(RobotMap.operatorJoystick);
		
		//Encoders
		leftWheelEnc = new Encoder(RobotMap.leftWheelEncA, RobotMap.leftWheelEncB);
		rightWheelEnc = new Encoder(RobotMap.rightWheelEncA, RobotMap.rightWheelEncB);
		frontWheelEnc = new Encoder(RobotMap.frontWheelEncA, RobotMap.frontWheelEncB);
		rearWheelEnc = new Encoder(RobotMap.rearWheelEncA, RobotMap.rearWheelEncB);
		
		//Gyros
		topGyro = new AnalogGyro(RobotMap.topGyro);
		bottomGyro = new AnalogGyro(RobotMap.bottonGyro);
		
		gearSensor = new AnalogInput(RobotMap.gearSensor);
	}
	
	//Premade Equations
	public static double getRot() {
		return (topGyro.getAngle() - bottomGyro.getAngle()) / 2.0;
	}
	
	public static boolean isGearIn() {
		return gearSensor.getAverageValue() > RobotSettings.gearSensorMinValue && gearSensor.getAverageValue() < RobotSettings.gearSensorMaxValue;
	}
}
