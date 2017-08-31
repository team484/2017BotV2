package org.usfirst.frc.team484.robot.commands.autonomouscommands;

import org.usfirst.frc.team484.robot.commands.DriveTrainDriveDistance;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoDriveAcrossLine extends CommandGroup {

    public AutoDriveAcrossLine() {
    	addSequential(new DriveTrainDriveDistance(95),15);
    }
}
