package org.usfirst.frc.team484.robot.commands.autonomouscommands;

import org.usfirst.frc.team484.robot.commands.DriveTrainDriveAtSpeed;
import org.usfirst.frc.team484.robot.commands.DriveTrainRotateToVisionAngle;
import org.usfirst.frc.team484.robot.commands.PauseCompressor;
import org.usfirst.frc.team484.robot.commands.RobotWaitForVision;
import org.usfirst.frc.team484.robot.commands.commandgroups.ShootGearCommands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class AutoCenterGearVision extends CommandGroup {

    public AutoCenterGearVision() {
    	addSequential(new WaitCommand(0.5));
    	addParallel(new PauseCompressor(), 3);
    	addSequential(new RobotWaitForVision());
        addSequential(new DriveTrainRotateToVisionAngle(),5);
        addSequential(new WaitCommand(0.5));
        addParallel(new ShootGearCommands(), 1);
        addSequential(new WaitCommand(0.5), 0.5);
        addSequential(new DriveTrainDriveAtSpeed(-0.5), 1);
    }
}
