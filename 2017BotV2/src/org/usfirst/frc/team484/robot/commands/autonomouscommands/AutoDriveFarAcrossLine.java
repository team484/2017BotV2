package org.usfirst.frc.team484.robot.commands.autonomouscommands;

import org.usfirst.frc.team484.robot.commands.DriveTrainDriveDistance;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoDriveFarAcrossLine extends CommandGroup {

    public AutoDriveFarAcrossLine() {
    	addSequential(new DriveTrainDriveDistance(280), 15);
    }
}
