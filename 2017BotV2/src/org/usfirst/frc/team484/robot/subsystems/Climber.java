package org.usfirst.frc.team484.robot.subsystems;

import org.usfirst.frc.team484.robot.RobotIO;
import org.usfirst.frc.team484.robot.RobotSettings;
import org.usfirst.frc.team484.robot.commands.ClimberDoNothing;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Climber extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new ClimberDoNothing());
    }
    public void climbSpeed(double speed) {
    	RobotIO.leftClimber.set(Math.abs(speed * RobotSettings.leftClimbSpeedMultiplier));
    	RobotIO.rightClimber.set(Math.abs(speed * RobotSettings.rightClimbSpeedMultiplier));
    }
}

