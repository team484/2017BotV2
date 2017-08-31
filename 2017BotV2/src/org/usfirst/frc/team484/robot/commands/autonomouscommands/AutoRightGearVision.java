package org.usfirst.frc.team484.robot.commands.autonomouscommands;

import org.usfirst.frc.team484.robot.commands.DriveTrainDriveDistance;
import org.usfirst.frc.team484.robot.commands.DriveTrainDriveToVision;
import org.usfirst.frc.team484.robot.commands.DriveTrainRotAngle;
import org.usfirst.frc.team484.robot.commands.GearShooterShootWhenGood;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoRightGearVision extends CommandGroup {

    public AutoRightGearVision() {
    	addSequential(new DriveTrainDriveDistance(70.5),3);
    	addSequential(new DriveTrainRotAngle(60), 2);
        addSequential(new DriveTrainDriveToVision(70.5),5);
        addSequential(new GearShooterShootWhenGood(), 5);
    }
}
