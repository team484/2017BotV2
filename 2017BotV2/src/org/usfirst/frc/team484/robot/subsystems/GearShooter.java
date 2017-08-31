package org.usfirst.frc.team484.robot.subsystems;

import org.usfirst.frc.team484.robot.RobotIO;
import org.usfirst.frc.team484.robot.commands.GearShooterRetract;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class GearShooter extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new GearShooterRetract());
    }
    
    public void retract() {
    	RobotIO.gearShootSolenoid.set(Value.kReverse);
    }
    public void shoot() {
    	RobotIO.gearShootSolenoid.set(Value.kForward);
    }
}

