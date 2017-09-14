package org.usfirst.frc.team484.robot.subsystems;

import org.usfirst.frc.team484.robot.RobotIO;
import org.usfirst.frc.team484.robot.commands.GearElevatorRaise;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class GearElevator extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new GearElevatorRaise());
    }
    public void raise() {
    	RobotIO.gearRotSolenoid.set(Value.kForward);
    }
    public void lower() {
    	RobotIO.gearRotSolenoid.set(Value.kReverse);
    }
}

