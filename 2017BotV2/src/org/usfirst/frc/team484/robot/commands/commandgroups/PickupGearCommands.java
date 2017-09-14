package org.usfirst.frc.team484.robot.commands.commandgroups;

import org.usfirst.frc.team484.robot.commands.GearElevatorLower;
import org.usfirst.frc.team484.robot.commands.GearElevatorLowerSensor;
import org.usfirst.frc.team484.robot.commands.GearGripperGrip;
import org.usfirst.frc.team484.robot.commands.GearGripperReleaseSensor;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class PickupGearCommands extends CommandGroup {

    public PickupGearCommands() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    	addSequential(new GearGripperGrip(), 0.05);
    	addSequential(new GearElevatorLower(), 0.8);
    	addParallel(new GearElevatorLowerSensor());
    	addSequential(new GearGripperReleaseSensor());
    	addSequential(new EndPickupGearCommands(), 1);
    }
}
