/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

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
import java.io.File;
import java.io.IOException;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private static final String kDefaultAuto = "Default";
  private static final String kCustomAuto = "My Auto";
  private String m_autoSelected;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();
  public static IO m_io = new IO();
  public static DriveSubsystem m_driveSubsystem;
  public static IntakeSubsystem m_intakeSubsystem;
  public static Values m_values;
  public static Values m_robotMap;
  public static NetworkTable visionTable;

  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    m_chooser.addOption("My Auto", kCustomAuto);
    SmartDashboard.putData("Auto choices", m_chooser);

    //initialize IO object and all used subsystems here to be created during the robot startup
    // m_io = new IO();
    try {
      m_values = new Values(new File("/home/lvuser/deploy/values.properties"), new String[] {"deadband"});
      m_robotMap = new Values(new File("/home/lvuser/deploy/robotMap.properties"), new String[] {"rightMaster", "rightSlave", "leftMaster", "leftSlave"});
    } catch (IOException e) {
      DriverStation.reportError(e.getMessage(), e.getStackTrace());
    }

    m_driveSubsystem = new DriveSubsystem();
    m_intakeSubsystem = new IntakeSubsystem();

    //set the default commands for the various subsystems
    CommandScheduler.getInstance().setDefaultCommand(m_driveSubsystem, new ArcadeDriveLookupCommand());
    // CommandScheduler.getInstance().setDefaultCommand(m_intakeSubsystem, new IntakeCommand());

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
    CommandScheduler.getInstance().run();
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
    m_autoSelected = m_chooser.getSelected();
    // m_autoSelected = SmartDashboard.getString("Auto Selector", kDefaultAuto);
    System.out.println("Auto selected: " + m_autoSelected);
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    switch (m_autoSelected) {
      case kCustomAuto:
        // Put custom auto code here
        break;
      case kDefaultAuto:
      default:
        // Put default auto code here
        break;
    }
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
