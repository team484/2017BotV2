package org.usfirst.frc.team484.robot.commands;

import org.usfirst.frc.team484.robot.Robot;
import org.usfirst.frc.team484.robot.RobotIO;
import org.usfirst.frc.team484.robot.RobotSettings;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveTrainDriveDistance extends Command {
	PIDController pid;
	double setpoint;
    public DriveTrainDriveDistance(double setpoint) {
    	this.setpoint = setpoint;
        // Use requires() here to declare subsystem dependencies
    	requires(Robot.driveTrain);
    	pid = new PIDController(RobotSettings.drivekP, RobotSettings.drivekI, RobotSettings.drivekD, new PIDSource() {
			
			@Override
			public void setPIDSourceType(PIDSourceType pidSource) {
				
			}
			
			@Override
			public double pidGet() {
				return (Math.abs(RobotIO.leftWheelEnc.getDistance()) > Math.abs(RobotIO.rightWheelEnc.getDistance())) ? RobotIO.leftWheelEnc.getDistance() : RobotIO.rightWheelEnc.getDistance();
			}
			
			@Override
			public PIDSourceType getPIDSourceType() {
				// TODO Auto-generated method stub
				return PIDSourceType.kDisplacement;
			}
		}, new PIDOutput() {
			
			@Override
			public void pidWrite(double output) {
				Robot.driveTrain.drive(output, 0);
				
			}
		});
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	RobotIO.leftWheelEnc.reset();
    	RobotIO.rightWheelEnc.reset();
    	pid.setOutputRange(-0.7, 0.7);
    	pid.reset();
    	pid.setSetpoint(setpoint);
    	pid.enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Math.abs(RobotIO.leftWheelEnc.getRate()) < RobotSettings.driveSpeedMargin
        		&& Math.abs(RobotIO.rightWheelEnc.getRate()) < RobotSettings.driveSpeedMargin
        		&& Math.abs(pid.getError()) < RobotSettings.driveErrorMargin;
    }

    // Called once after isFinished returns true
    protected void end() {
    	pid.disable();
    	Robot.driveTrain.drive(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
