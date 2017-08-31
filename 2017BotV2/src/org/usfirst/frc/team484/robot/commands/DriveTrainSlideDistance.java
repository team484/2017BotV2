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
public class DriveTrainSlideDistance extends Command {
	PIDController frontPid;
	PIDController rearPid;
	double setpoint;
    public DriveTrainSlideDistance(double setpoint) {
    	this.setpoint = setpoint;
        // Use requires() here to declare subsystem dependencies
    	requires(Robot.driveTrain);
    	frontPid = new PIDController(RobotSettings.slidekP, RobotSettings.slidekI, RobotSettings.slidekD, new PIDSource() {
			
			@Override
			public void setPIDSourceType(PIDSourceType pidSource) {
				
			}
			
			@Override
			public double pidGet() {
				return RobotIO.frontWheelEnc.getDistance();
			}
			
			@Override
			public PIDSourceType getPIDSourceType() {
				// TODO Auto-generated method stub
				return PIDSourceType.kDisplacement;
			}
		}, new PIDOutput() {
			
			@Override
			public void pidWrite(double output) {
				Robot.driveTrain.frontWheelSpeed = output;
				
			}
		});
    	
    	rearPid = new PIDController(RobotSettings.slidekP, RobotSettings.slidekI, RobotSettings.slidekD, new PIDSource() {
			
			@Override
			public void setPIDSourceType(PIDSourceType pidSource) {
				
			}
			
			@Override
			public double pidGet() {
				return RobotIO.rearWheelEnc.getDistance();
			}
			
			@Override
			public PIDSourceType getPIDSourceType() {
				// TODO Auto-generated method stub
				return PIDSourceType.kDisplacement;
			}
		}, new PIDOutput() {
			
			@Override
			public void pidWrite(double output) {
				Robot.driveTrain.rearWheelSpeed = output;
				
			}
		});
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	RobotIO.frontWheelEnc.reset();
    	RobotIO.rearWheelEnc.reset();
    	frontPid.reset();
    	rearPid.reset();
    	frontPid.setSetpoint(setpoint);
    	rearPid.setSetpoint(setpoint);
    	frontPid.enable();
    	rearPid.enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.driveTrain.slideUsingSpeeds();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Math.abs(RobotIO.frontWheelEnc.getRate()) < RobotSettings.slideSpeedMargin
        	&& Math.abs(RobotIO.rearWheelEnc.getRate()) < RobotSettings.slideSpeedMargin
        	&& Math.abs(frontPid.getError()) < RobotSettings.slideErrorMargin
        	&& Math.abs(rearPid.getError()) < RobotSettings.slideErrorMargin;
    }

    // Called once after isFinished returns true
    protected void end() {
    	frontPid.disable();
    	rearPid.disable();
    	Robot.driveTrain.drive(0, 0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
