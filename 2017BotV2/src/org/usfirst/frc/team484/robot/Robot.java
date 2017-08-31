
package org.usfirst.frc.team484.robot;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team484.robot.commands.autonomouscommands.AutoDoNothing;
import org.usfirst.frc.team484.robot.commands.autonomouscommands.AutoDriveAcrossLine;
import org.usfirst.frc.team484.robot.commands.autonomouscommands.AutoDriveFarAcrossLine;
import org.usfirst.frc.team484.robot.commands.autonomouscommands.AutoLeftGearVision;
import org.usfirst.frc.team484.robot.commands.autonomouscommands.AutoRightGearVision;
import org.usfirst.frc.team484.robot.subsystems.Climber;
import org.usfirst.frc.team484.robot.subsystems.Drivetrain;
import org.usfirst.frc.team484.robot.subsystems.GearElevator;
import org.usfirst.frc.team484.robot.subsystems.GearGripper;
import org.usfirst.frc.team484.robot.subsystems.GearShooter;
import org.usfirst.frc.team484.robot.vision.Vision;

public class Robot extends IterativeRobot {
	public static Vision vision;
	public static RobotIO io = new RobotIO();

	public static final Climber climber = new Climber();
	public static final Drivetrain driveTrain = new Drivetrain();
	public static final GearElevator gearElevator = new GearElevator();
	public static final GearGripper gearGripper = new GearGripper();
	public static final GearShooter gearShooter = new GearShooter();

	public static OI oi;
	
	Command autonomousCommand;
	SendableChooser<Command> chooser = new SendableChooser<>();

	@Override
	public void robotInit() {
		try {
		vision = new Vision(50);
		vision.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
		oi = new OI();
		chooser.addDefault("Do Nothing", new AutoDoNothing());
		chooser.addDefault("Cross Line", new AutoDriveAcrossLine());
		chooser.addDefault("Go to Midfield", new AutoDriveFarAcrossLine());
		chooser.addDefault("Left Gear", new AutoLeftGearVision());
		chooser.addDefault("Right Gear", new AutoRightGearVision());
		// chooser.addObject("My Auto", new MyAutoCommand());
		SmartDashboard.putData("Auto mode", chooser);
		initializeIO(); //Sets values for all sensors and IO components
		setInverts();
	}
	
	private static void initializeIO() {
		RobotIO.compressor.setClosedLoopControl(true); //Enables Compressor
		
		//Set encoder distances per pulse
		RobotIO.leftWheelEnc.setDistancePerPulse(RobotSettings.rlWheelEncDPP);
		RobotIO.rightWheelEnc.setDistancePerPulse(RobotSettings.rrWheelEncDPP);
		RobotIO.frontWheelEnc.setDistancePerPulse(RobotSettings.fRotWheelEncDPP);
		RobotIO.rearWheelEnc.setDistancePerPulse(RobotSettings.rRotWheelEncDPP);
		
		RobotIO.topGyro.initGyro();
		RobotIO.bottomGyro.initGyro();
		RobotIO.topGyro.calibrate();
		RobotIO.bottomGyro.calibrate();
	}

	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		if (!vision.isAlive()) {
			vision = new Vision(50);
			vision.start();
		}
	}

	@Override
	public void autonomousInit() {
		autonomousCommand = chooser.getSelected();

		if (autonomousCommand != null)
			autonomousCommand.start();
		RobotIO.compressor.start();
		setInverts();
	}

	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		if (!vision.isAlive()) {
			vision = new Vision(50);
			vision.start();
		}
	}

	@Override
	public void teleopInit() {
		if (autonomousCommand != null)
			autonomousCommand.cancel();
		RobotIO.compressor.start();
		setInverts();
	}

	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		if (!vision.isAlive()) {
			vision = new Vision(50);
			vision.start();
		}
	}

	
	private static void setInverts() {
		RobotIO.leftWheel.setInverted(RobotSettings.invertLeftMotor);
		RobotIO.rightWheel.setInverted(RobotSettings.invertRightMotor);
		RobotIO.frontWheel.setInverted(RobotSettings.invertFrontMotor);
		RobotIO.rearWheel.setInverted(RobotSettings.invertRearMotor);
		RobotIO.leftClimber.setInverted(RobotSettings.invertLeftClimber);
		RobotIO.rightClimber.setInverted(RobotSettings.invertRightClimber);
	}
	
	
	
	//Testing code
	
	private int testState = 0;
	private boolean wasTrigger = false;
	@Override
	public void testInit() {
		testState = 0;
		wasTrigger = false;
		msg("Hold trigger to start test");
		RobotIO.compressor.stop();
	}
	
	@Override
	public void testPeriodic() {
		LiveWindow.run();
		boolean isTrigger = RobotIO.driverJoystick.getTrigger();

		if (isTrigger) {
			if (!wasTrigger) {
				testState++;
				wasTrigger = true;
			}
			
			switch (testState) {
			case 0:
				msg("Hold trigger to start test");
				break;
				
			case 1:
				msg("Left wheels set to forward (0.5)");
				RobotIO.leftWheel.set(0.5);
				break;
				
			case 2:
				msg("Right wheels set to forward (0.5)");
				RobotIO.rightWheel.set(0.5);
				break;
				
			case 3:
				msg("Fromt wheel set to right (0.5)");
				RobotIO.frontWheel.set(0.5);
				break;
				
			case 4:
				msg("Rear wheel set to right (0.5)");
				RobotIO.rearWheel.set(0.5);
				break;
			
			case 5:
				msg("Left climber set to forward (0.5)");
				RobotIO.leftClimber.set(0.5);
				break;
				
			case 6:
				msg("Right climber set to forward (0.5)");
				RobotIO.rightClimber.set(0.5);
				break;
				
			case 7:
				msg("Enabling compressor control loop");
				RobotIO.compressor.start();
				break;
			
			case 8:
				msg("Extending shooter");
				RobotIO.gearShootSolenoid.set(Value.kForward);
				break;
				
			case 9:
				msg("Lowering gear pickup");
				RobotIO.gearRotSolenoid.set(Value.kForward);
				break;
				
			case 10:
				msg("Opening gripper");
				RobotIO.gearGripSolenoid.set(Value.kForward);
				break;
				
			case 11:
				msg("Testing left encoder (" + RobotIO.leftWheelEnc.getDistance() + ")");
				RobotIO.leftWheel.set(0.5);
				break;
				
			case 12:
				msg("Testing right encoder (" + RobotIO.rightWheelEnc.getDistance() + ")");
				RobotIO.rightWheel.set(0.5);
				break;
				
			case 13:
				msg("Testing front encoder (" + RobotIO.frontWheelEnc.getDistance() + ")");
				RobotIO.frontWheel.set(0.5);
				break;
				
			case 14:
				msg("Testing rear encoder (" + RobotIO.rearWheelEnc.getDistance() + ")");
				RobotIO.rearWheel.set(0.5);
				break;
				
			case 15:
				msg("Checking gyro (" + RobotIO.getRot() + ")");
				break;
				
			default:
				msg("Test over");
				break;
			}
		} else {
			wasTrigger = false;
			disableMotion();
		}
		
	}
	private static void msg(String message) {
		SmartDashboard.putString("Test MSG:", message);
	}
	private static void disableMotion() {
		RobotIO.compressor.stop();
		RobotIO.frontWheel.set(0);
		RobotIO.gearGripSolenoid.set(Value.kReverse);
		RobotIO.gearRotSolenoid.set(Value.kReverse);
		RobotIO.gearShootSolenoid.set(Value.kReverse);
		RobotIO.leftClimber.set(0);
		RobotIO.leftWheel.set(0);
		RobotIO.rearWheel.set(0);
		RobotIO.rightClimber.set(0);
		RobotIO.rightWheel.set(0);
		RobotIO.robotDrive.arcadeDrive(0, 0);
	}
}
