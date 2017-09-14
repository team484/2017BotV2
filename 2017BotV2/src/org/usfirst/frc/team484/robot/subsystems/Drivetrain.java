package org.usfirst.frc.team484.robot.subsystems;

import org.usfirst.frc.team484.robot.RobotIO;
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
    
    public void drive(double y, double rot) {
    	RobotIO.robotDrive.arcadeDrive(-y, rot);
    }
     
    //Vision Drive
    public static double visionY = 0;
    public static double visionRot = 0;
    public void visionDrive() {
    	drive(visionY, visionRot);
    }
}

