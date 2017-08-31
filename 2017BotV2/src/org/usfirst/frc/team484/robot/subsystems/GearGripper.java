package org.usfirst.frc.team484.robot.subsystems;

import org.usfirst.frc.team484.robot.RobotIO;
import org.usfirst.frc.team484.robot.commands.GearGripperGrip;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class GearGripper extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new GearGripperGrip());
    }
    public void grip() {
    	RobotIO.gearGripSolenoid.set(Value.kReverse);
    }
    public void release() {
    	RobotIO.gearGripSolenoid.set(Value.kForward);
    }
}

