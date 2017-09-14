package org.usfirst.frc.team484.robot.commands;

import org.usfirst.frc.team484.robot.Robot;
import org.usfirst.frc.team484.robot.RobotIO;
import org.usfirst.frc.team484.robot.RobotSettings;
import org.usfirst.frc.team484.robot.vision.Vision;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveTrainRotateToVisionAngle extends Command {
	PIDController pid;
	public DriveTrainRotateToVisionAngle() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.driveTrain);

		pid = new PIDController(RobotSettings.visionRotkP, RobotSettings.visionRotkI, RobotSettings.visionRotkD, new PIDSource() {

			@Override
			public void setPIDSourceType(PIDSourceType pidSource) {
				// TODO Auto-generated method stub

			}

			@Override
			public double pidGet() {
				return RobotIO.getRot();
			}

			@Override
			public PIDSourceType getPIDSourceType() {
				return PIDSourceType.kDisplacement;
			}
		}, new PIDOutput() {

			@Override
			public void pidWrite(double output) {
				if (System.currentTimeMillis() - startTime > 1000) {
					if (output > 0) {
						Robot.driveTrain.drive(0.5, output);
					} else if (output < 0) {
						Robot.driveTrain.drive(0.5, output);
					}
				} else {
					if (output > 0) {
						Robot.driveTrain.drive(0, output + 0.25);
					} else if (output < 0) {
						Robot.driveTrain.drive(0, output - 0.25);
					}
				}

			}
		}, 0.01);
	}

	long startTime;
	// Called just before this Command runs the first time
	protected void initialize() {
		startTime = System.currentTimeMillis();
		RobotIO.topGyro.reset();
		RobotIO.bottomGyro.reset();
		pid.reset();
		try {
			pid.setSetpoint(-Vision.results.targetRot);
		} catch (Exception e) {
			System.err.println("Error with vision PID");
			pid.setSetpoint(0);
		}
		pid.setOutputRange(-0.3, 0.3);
		pid.enable();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
	}

	// Make this return true when this Command no longer needs to run execute()
	int i = 0;
	protected boolean isFinished() {
		double distance = 100;
		try {
			distance = Vision.results.targetY;
		} catch (Exception e) {
			distance = 100;
		}
		if (distance < 20) {
			i++;
			if (i > 7) { 
				return true;
			}
		} else {
			i = 0;
		}
		return false;
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
