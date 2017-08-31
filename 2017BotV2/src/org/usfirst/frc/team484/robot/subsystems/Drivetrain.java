package org.usfirst.frc.team484.robot.subsystems;

import org.usfirst.frc.team484.robot.RobotIO;
import org.usfirst.frc.team484.robot.RobotSettings;
import org.usfirst.frc.team484.robot.commands.DriveTrainDriveWithJoystick;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Drivetrain extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new DriveTrainDriveWithJoystick());
    }
    
    public void drive(double x, double y, double rot) {
    	RobotIO.robotDrive.arcadeDrive(y, rot);
    	double frontRot = rot * RobotSettings.frontRotWheelRotMultiplier + x;
    	double rearRot = -rot * RobotSettings.rearRotWheelRotMultiplier + x;
    	if (frontRot > 1) frontRot = 1;
    	if (frontRot < -1) frontRot = -1;
    	if (rearRot > 1) rearRot = 1;
    	if (rearRot < -1) rearRot = -1;
    	
    	RobotIO.frontWheel.set(frontRot);
    	RobotIO.rearWheel.set(rearRot);
    }
    
    
    //Slide PID
    public double frontWheelSpeed = 0;
    public double rearWheelSpeed = 0;
    public double rotationModifier = 0;
    public void slideUsingSpeeds() {
    	RobotIO.robotDrive.arcadeDrive(0,0);
    	RobotIO.frontWheel.set(frontWheelSpeed);
    	RobotIO.rearWheel.set(rearWheelSpeed - rotationModifier);
    }
    
    
    //Vision Drive
    public static double visionX = 0;
    public static double visionY = 0;
    public static double visionRot = 0;
    public void visionDrive() {
    	drive(visionX, visionY, visionRot);
    }
}

