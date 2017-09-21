
package org.usfirst.frc.team484.robot;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoMode.PixelFormat;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Relay.Value;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team484.robot.commands.autonomouscommands.AutoCenterGearVision;
import org.usfirst.frc.team484.robot.commands.autonomouscommands.AutoDoNothing;
import org.usfirst.frc.team484.robot.commands.autonomouscommands.AutoDriveAcrossLine;
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
	public static RobotIO io;

	public static Climber climber;
	public static Drivetrain driveTrain;
	public static GearElevator gearElevator;
	public static GearGripper gearGripper;
	public static GearShooter gearShooter;
	
	public static OI oi;
	
	//LocationTracker locTracker;

	Command autonomousCommand;
	SendableChooser<Command> chooser = new SendableChooser<>();

	PowerDistributionPanel pdp;

	@Override
	public void robotInit() {
		SmartDashboard.putBoolean("Vision", false);
		SmartDashboard.putString("VisionState", "Off");
		//Creates camera server
		try {
			UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
			camera.setFPS(30);
			camera.setVideoMode(PixelFormat.kMJPEG, 480, 360, 30);
		} catch (Exception e) {
			e.printStackTrace();
		}

		io = new RobotIO();
		climber = new Climber();
		driveTrain = new Drivetrain();
		gearElevator = new GearElevator();
		gearGripper = new GearGripper();
		gearShooter = new GearShooter();
		try {
			vision = new Vision(1000);
			vision.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
		oi = new OI();
		chooser.addDefault("Do Nothing", new AutoDoNothing());
		chooser.addDefault("Cross Line", new AutoDriveAcrossLine());
		chooser.addDefault("Center Gear", new AutoCenterGearVision());
		chooser.addDefault("Left Gear", new AutoLeftGearVision());
		chooser.addDefault("Right Gear", new AutoRightGearVision());
		SmartDashboard.putData("Auto mode", chooser);
		initializeIO(); //Sets values for all sensors and IO components
		setInverts();
		pdp = new PowerDistributionPanel();
		//locTracker = new LocationTracker();
		//locTracker.start();
	}

	private static void initializeIO() {
		RobotIO.compressor.setClosedLoopControl(true); //Enables Compressor

		//Set encoder distances per pulse
		RobotIO.leftWheelEnc.setDistancePerPulse(RobotSettings.leftWheelEncDPP);
		RobotIO.rightWheelEnc.setDistancePerPulse(RobotSettings.rightWheelEncDPP);

		RobotIO.topGyro.initGyro();
		RobotIO.bottomGyro.initGyro();
		//RobotIO.topGyro.calibrate();
		//RobotIO.bottomGyro.calibrate();
	}

	@Override
	public void disabledInit() {
		SmartDashboard.putBoolean("Vision", false);
	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		if (!vision.isAlive()) {
			vision = new Vision(1000);
			vision.start();
		}
		try {
			SmartDashboard.putNumber("targetY", Vision.results.targetY);
			SmartDashboard.putNumber("targetX", Vision.results.targetX);
			SmartDashboard.putNumber("targetRot", Vision.results.targetRot);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void autonomousInit() {
		SmartDashboard.putBoolean("Vision", true);
		RobotIO.leftWheelEnc.reset();
		RobotIO.rightWheelEnc.reset();
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
			vision = new Vision(1000);
			vision.start();
		}
		SmartDashboard.putNumber("targetY", Vision.results.targetY);
		SmartDashboard.putNumber("targetRot", Vision.results.targetRot);
	}

	@Override
	public void teleopInit() {
		SmartDashboard.putBoolean("Vision", false);
		if (autonomousCommand != null)
			autonomousCommand.cancel();
		RobotIO.compressor.start();
		setInverts();
	}

	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		SmartDashboard.putNumber("LeftClimber", pdp.getCurrent(15));
		SmartDashboard.putNumber("RightClimber", pdp.getCurrent(0));
		//SmartDashboard.putNumber("x pos: ", locTracker.x);
		//SmartDashboard.putNumber("y pos: ", locTracker.y);
	}


	private static void setInverts() {
		RobotIO.leftWheel.setInverted(RobotSettings.invertLeftMotor);
		RobotIO.rightWheel.setInverted(RobotSettings.invertRightMotor);
		RobotIO.leftClimber.setInverted(RobotSettings.invertLeftClimber);
		RobotIO.rightClimber.setInverted(RobotSettings.invertRightClimber);
	}

	@Override
	public void testInit() {
	}

	@Override
	public void testPeriodic() {
		LiveWindow.run();

	}

}
