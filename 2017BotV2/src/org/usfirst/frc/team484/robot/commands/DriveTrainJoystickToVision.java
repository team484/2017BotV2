package org.usfirst.frc.team484.robot.commands;

import org.usfirst.frc.team484.robot.Robot;
import org.usfirst.frc.team484.robot.RobotIO;
import org.usfirst.frc.team484.robot.RobotSettings;
import org.usfirst.frc.team484.robot.subsystems.Drivetrain;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */

//TODO: Program this command
public class DriveTrainJoystickToVision extends Command {
	PIDController pid;
    public DriveTrainJoystickToVision() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveTrain);
    	
    	pid = new PIDController(RobotSettings.visionSlidekP, RobotSettings.visionSlidekI, RobotSettings.visionSlidekD, new PIDSource() {
			
			@Override
			public void setPIDSourceType(PIDSourceType pidSource) {				
			}
			
			@Override
			public double pidGet() {
				try {
				return -Robot.vision.results.targetX;
				} catch (Exception e) {
					System.err.println("Error with vision");
					return 0;
				}
			}
			
			@Override
			public PIDSourceType getPIDSourceType() {
				return PIDSourceType.kDisplacement;
			}
		}, new PIDOutput() {
			
			@Override
			public void pidWrite(double output) {
				Drivetrain.visionX = output;
			}
		});
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	pid.reset();
    	pid.setSetpoint(0);
    	pid.enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Drivetrain.visionY = -RobotIO.driverJoystick.getY();
    	Drivetrain.visionRot = RobotIO.driverJoystick.getX() / 4.0; //May want to disable
    	Robot.driveTrain.visionDrive();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	pid.disable();
    	Robot.driveTrain.drive(0, -RobotIO.driverJoystick.getY(), RobotIO.driverJoystick.getX());
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
