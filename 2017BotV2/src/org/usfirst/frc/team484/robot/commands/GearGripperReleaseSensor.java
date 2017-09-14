package org.usfirst.frc.team484.robot.commands;

import org.usfirst.frc.team484.robot.Robot;
import org.usfirst.frc.team484.robot.RobotIO;
import org.usfirst.frc.team484.robot.RobotSettings;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class GearGripperReleaseSensor extends Command {
	boolean gearStart = false;
    public GearGripperReleaseSensor() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.gearGripper);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	gearStart = RobotIO.isGearIn();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.gearGripper.release();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (RobotIO.isGearIn() && !gearStart) || !RobotIO.operatorJoystick.getRawButton(RobotSettings.gearPickupButton);
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
