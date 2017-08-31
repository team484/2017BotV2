package org.usfirst.frc.team484.robot.commands;

import org.usfirst.frc.team484.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class GearShooterShootWhenGood extends Command {
	int counter = 0;
    public GearShooterShootWhenGood() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.gearShooter);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	counter = 0;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//TODO: Add sensors to allow this functionality
    	Robot.gearShooter.shoot();
    	counter++;
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return counter > 10;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
