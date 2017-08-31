package org.usfirst.frc.team484.robot.commands.commandgroups;

import org.usfirst.frc.team484.robot.commands.GearGripperRelease;
import org.usfirst.frc.team484.robot.commands.GearShooterShoot;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class ShootGearCommands extends CommandGroup {

    public ShootGearCommands() {
    	addParallel(new GearGripperRelease());
    	addSequential(new WaitCommand(0.5), 0.5);
    	addParallel(new GearShooterShoot());

    }
}
