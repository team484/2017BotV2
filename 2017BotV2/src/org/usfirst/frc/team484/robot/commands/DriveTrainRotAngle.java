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
public class DriveTrainRotAngle extends Command {
	PIDController pid;
	double setpoint;
    public DriveTrainRotAngle(double setpoint) {
    	this.setpoint = setpoint;
        // Use requires() here to declare subsystem dependencies
    	requires(Robot.driveTrain);
    	pid = new PIDController(RobotSettings.rotkP, RobotSettings.rotkI, RobotSettings.rotkD, new PIDSource() {
			
			@Override
			public void setPIDSourceType(PIDSourceType pidSource) {
				
			}
			
			@Override
			public double pidGet() {
				return RobotIO.getRot();
			}
			
			@Override
			public PIDSourceType getPIDSourceType() {
				// TODO Auto-generated method stub
				return PIDSourceType.kDisplacement;
			}
		}, new PIDOutput() {
			
			@Override
			public void pidWrite(double output) {
				Robot.driveTrain.drive(0, output);
				
			}
		}, 0.01);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	pid.setOutputRange(-0.6, 0.6);
    	RobotIO.topGyro.reset();
    	RobotIO.bottomGyro.reset();
    	pid.reset();
    	pid.setSetpoint(setpoint);
    	pid.enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    	//return Math.abs(RobotIO.topGyro.getRate()) < RobotSettings.rotSpeedMargin
        //		&& Math.abs(RobotIO.bottomGyro.getRate()) < RobotSettings.rotSpeedMargin
        //		&& Math.abs(pid.getError()) < RobotSettings.rotErrorMargin;
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
