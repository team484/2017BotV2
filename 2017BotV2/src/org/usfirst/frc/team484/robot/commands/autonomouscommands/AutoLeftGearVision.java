package org.usfirst.frc.team484.robot.commands.autonomouscommands;

import org.usfirst.frc.team484.robot.commands.DriveTrainDriveAtSpeed;
import org.usfirst.frc.team484.robot.commands.DriveTrainDriveDistance;
import org.usfirst.frc.team484.robot.commands.DriveTrainRotAngle;
import org.usfirst.frc.team484.robot.commands.DriveTrainRotateToVisionAngle;
import org.usfirst.frc.team484.robot.commands.PauseCompressor;
import org.usfirst.frc.team484.robot.commands.commandgroups.ShootGearCommands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class AutoLeftGearVision extends CommandGroup {

    public AutoLeftGearVision() {
    	addSequential(new DriveTrainDriveDistance(70.5),3);
    	addParallel(new PauseCompressor(), 5);
    	addSequential(new DriveTrainRotAngle(60), 1);
    	addSequential(new DriveTrainDriveAtSpeed(0.0), 0.8);
        addSequential(new DriveTrainRotateToVisionAngle(),5);
        addSequential(new WaitCommand(0.5));
        addParallel(new ShootGearCommands(), 1);
        addSequential(new WaitCommand(0.5), 0.5);
        addSequential(new DriveTrainDriveAtSpeed(-0.5), 1);
        addSequential(new DriveTrainRotAngle(-60), 1);
        addSequential(new DriveTrainDriveDistance(120), 5);
    }
}
