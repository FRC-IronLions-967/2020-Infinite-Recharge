/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.commands.*;
import frc.robot.subsystems.*;
import frc.robot.utils.values.Values;
import frc.robot.utils.logging.Logger;
import frc.robot.autonomous.*;

import java.io.File;
import java.io.IOException;

import com.revrobotics.CANPIDController;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private static final String kDefaultAuto = "Default";
  private static final String kTestAuto = "Test Auto";
  private String m_autoSelected;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();
  public static IO m_io;
  public static Values m_values;
  public static Values m_robotMap;
  public static NetworkTable visionTable;
  public static double tx;
  public static CANPIDController controllerRight;
  public static CANPIDController controllerLeft;
  public static Autonomous selectedAuto;
  public static int maxRPM = 3900;
  public static boolean beltsReversed = false;
  public static boolean intakeOn = false;
  public static boolean elevatorJammed = false;
  public static Logger logger;
  SubsystemsInstance inst;

  // public static int rpmLookup[] = {3250, 3300, 3350, 3525, 3575, 3650, 3700, 3750, 3825, 3950, 4125, 4300, 4400, 4575};
  public static int rpmLookup[] = {3900, 3900, 3900, 4100, 4200, 4400, 4450, 4600, 4800, 5000, 5200, 5400, 5600, 5700};

  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    inst = SubsystemsInstance.getInstance();
    m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    m_chooser.addOption("Test Auto", kTestAuto);
    SmartDashboard.putData("Auto choices", m_chooser);

    //initialize IO object and all used subsystems here to be created during the robot startup
    // m_io = new IO();
    try {
      m_values = new Values(new File("/home/lvuser/deploy/values.properties"), new String[] {"deadband"});
      m_robotMap = new Values(new File("/home/lvuser/deploy/robotMap.properties"), new String[] {"rightMaster", "rightSlave", "leftMaster", "leftSlave"});
      logger = new Logger("/home/lvuser/log.csv", ',', null);
    } catch (IOException e) {
      DriverStation.reportError(e.getMessage(), e.getStackTrace());
    }
    
    m_io = new IO();

    NetworkTableInstance inst = NetworkTableInstance.getDefault();
    //for some reason, the getter will create the table if it already exists
    //i have no idea why it would be written this way, but it is so just go with it, this doesn't seem to be present in the documentation on the docs page
    // NetworkTable table = inst.getTable("test");
    visionTable = inst.getTable("vision");
}

  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    // CameraServer.getInstance().startAutomaticCapture();
    CommandScheduler.getInstance().run();
    SmartDashboard.putNumber("Shooter RPM", inst.m_shooterSubsystem.getRPM());
    SmartDashboard.putNumber("Max RPM", maxRPM);
    SmartDashboard.putBoolean("Belts Reversed", beltsReversed);
    SmartDashboard.putBoolean("Intake On", intakeOn);
    SmartDashboard.putBoolean("Elevator Stopped", elevatorJammed);
    SmartDashboard.putNumber("Right Speed", inst.m_driveSubsystem.getRightSpeed());
    SmartDashboard.putNumber("Left Speed", inst.m_driveSubsystem.getLeftSpeed());
    try {
      logger.log(new String[] {Double.toString(inst.m_shooterSubsystem.getRPM()), Double.toString(maxRPM),
        Boolean.toString(beltsReversed), Boolean.toString(intakeOn), Boolean.toString(elevatorJammed),
        Double.toString(inst.m_driveSubsystem.getRightSpeed()), Double.toString(inst.m_driveSubsystem.getLeftSpeed())});
    } catch (IOException e) {
      DriverStation.reportError(e.getMessage(), e.getStackTrace());
    }
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the
   * LabVIEW Dashboard, remove all of the chooser code and uncomment the
   * getString line to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional comparisons to
   * the switch structure below with additional strings. If using the
   * SendableChooser make sure to add them to the chooser code above as well.
   */
  @Override
  public void autonomousInit() {
    tx = 0;
    m_autoSelected = m_chooser.getSelected();
    // m_autoSelected = SmartDashboard.getString("Auto Selector", kDefaultAuto);
    System.out.println("Auto selected: " + m_autoSelected);

    //TODO set this to pull from the dashboard for the autonomous selection
    selectedAuto = new TestAuto();

    double kP = 5e-6; 
    double kI = 1e-6;
    double kD = 0; 
    double kIz = 0; 
    double kFF = 0; 
    double kMaxOutput = 1; 
    double kMinOutput = -1;

    controllerRight = inst.m_driveSubsystem.rightMaster.getPIDController();
    controllerLeft = inst.m_driveSubsystem.leftMaster.getPIDController();

    controllerRight.setP(kP);
    controllerRight.setI(kI);
    controllerRight.setD(kD);
    controllerRight.setIZone(kIz);
    controllerRight.setFF(kFF);
    controllerRight.setOutputRange(kMinOutput, kMaxOutput);

    controllerLeft.setP(kP);
    controllerLeft.setI(kI);
    controllerLeft.setD(kD);
    controllerLeft.setIZone(kIz);
    controllerLeft.setFF(kFF);
    controllerLeft.setOutputRange(kMinOutput, kMaxOutput);

    selectedAuto.runAuto();
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    // selectedAuto.runAuto();
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
  }

  @Override
  public void teleopInit() {
    m_io.telopInit();
    CameraServer.getInstance().startAutomaticCapture();
    CommandScheduler.getInstance().setDefaultCommand(inst.m_driveSubsystem, new ArcadeDriveLookupCommand());
    CommandScheduler.getInstance().setDefaultCommand(inst.m_shooterSubsystem, new ShooterCommand());
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }

  public double getTX() {
    return NetworkTableInstance.getDefault().getTable("limelight").getEntry("tx").getDouble(0.0);
  }

  public double getTV() {
    return NetworkTableInstance.getDefault().getTable("limelight").getEntry("tv").getDouble(0.0);
  }
}
