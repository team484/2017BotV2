package org.usfirst.frc.team484.robot;

import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

import org.usfirst.frc.team484.robot.commands.CancelOnGearGrab;
import org.usfirst.frc.team484.robot.commands.ClimberClimbWithJoystick;
import org.usfirst.frc.team484.robot.commands.ClimberDoNothing;
import org.usfirst.frc.team484.robot.commands.commandgroups.EndPickupGearCommands;
import org.usfirst.frc.team484.robot.commands.commandgroups.PickupGearCommands;
import org.usfirst.frc.team484.robot.commands.commandgroups.ShootGearCommands;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	
	Button gearPickup = new JoystickButton(RobotIO.operatorJoystick, RobotSettings.gearPickupButton);
	Button climb = new JoystickButton(RobotIO.operatorJoystick, RobotSettings.climberButton);
	Button gearShoot = new JoystickButton(RobotIO.operatorJoystick, RobotSettings.gearShootButton);
	
	public OI() {
		
		PickupGearCommands gearPickupCommand = new PickupGearCommands();
		gearPickup.whileHeld(gearPickupCommand);
		gearPickup.whileHeld(new CancelOnGearGrab(gearPickupCommand));
		gearPickup.whenReleased(new EndPickupGearCommands());
		climb.whileHeld(new ClimberClimbWithJoystick());
		climb.whenReleased(new ClimberDoNothing());
		gearShoot.whileHeld(new ShootGearCommands());
	}
}
