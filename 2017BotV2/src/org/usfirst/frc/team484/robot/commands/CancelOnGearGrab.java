package org.usfirst.frc.team484.robot.commands;

import org.usfirst.frc.team484.robot.RobotIO;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class CancelOnGearGrab extends Command {
	Command cmd;
    public CancelOnGearGrab(Command cmdToCancel) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	cmd = cmdToCancel;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (RobotIO.isGearIn()) {
    		cmd.cancel();
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return !cmd.isRunning();
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
