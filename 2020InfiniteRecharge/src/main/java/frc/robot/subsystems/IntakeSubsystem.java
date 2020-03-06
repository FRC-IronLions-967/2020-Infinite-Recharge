/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj2.command.Subsystem;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Robot;

public class IntakeSubsystem extends SubsystemBase implements Subsystem {
  /**
   * Creates a new IntakeSubsystem.
   */
  private TalonSRX intake;
  private TalonSRX outerIntake;
  private TalonSRX lower;
  private TalonSRX upper;

  double MAX = Double.parseDouble(Robot.m_values.getValue("MAX"));
  double deadband = Double.parseDouble(Robot.m_values.getValue("deadband2"));


  public IntakeSubsystem() {
    //initialize the Victor objects
    // intake = new VictorSPX(Integer.parseInt(Robot.m_robotMap.getValue("intake")));
    // lower = new VictorSPX(Integer.parseInt(Robot.m_robotMap.getValue("lowerBelt")));
    // upper = new VictorSPX(Integer.parseInt(Robot.m_robotMap.getValue("upperBelt")));
    intake = new TalonSRX(Integer.parseInt(Robot.m_robotMap.getValue("intake")));
    outerIntake = new TalonSRX(Integer.parseInt(Robot.m_robotMap.getValue("outerIntake")));
    lower = new TalonSRX(Integer.parseInt(Robot.m_robotMap.getValue("lowerBelt")));
    upper = new TalonSRX(Integer.parseInt(Robot.m_robotMap.getValue("upperBelt")));

    intake.setInverted(false);
    outerIntake.setInverted(false);
    lower.setInverted(true);
    upper.setInverted(true);

    outerIntake.follow(intake);
  }

  public void intake(double x) {
    //defensive code to make sure values do not exceed -1.0 to 1.0
    x = (x > MAX) ? MAX : x;
    x = (x < -MAX) ? -MAX : x;

    x = (Robot.beltsReversed) ? -x : x;

    intake.set(ControlMode.PercentOutput, x);
    outerIntake.set(ControlMode.PercentOutput, x);
  }

  public void lower(double x) {
    //defensive code to make sure values do not exceed -1.0 to 1.0
    x = (x > MAX) ? MAX : x;
    x = (x < -MAX) ? -MAX : x;

    x = (Robot.beltsReversed) ? -x : x;

    lower.set(ControlMode.PercentOutput, x);
  }

  public void upper(double x) {
    //defensive code to make sure values do not exceed -1.0 to 1.0
    x = (x > MAX) ? MAX : x;
    x = (x < -MAX) ? -MAX : x;

    x = (Robot.beltsReversed) ? -x : x;

    upper.set(ControlMode.PercentOutput, x);
  }

  @Override
  public void periodic() {
    if(Robot.beltsReversed) {
      Robot.m_io.xbox1.setRumble(RumbleType.kRightRumble, 0.2);
      Robot.m_io.xbox1.setRumble(RumbleType.kLeftRumble, 0.2);
    } else {
      Robot.m_io.xbox1.setRumble(RumbleType.kRightRumble, 0.0);
      Robot.m_io.xbox1.setRumble(RumbleType.kLeftRumble, 0.0);
    }
    // This method will be called once per scheduler run
    // upper.set(ControlMode.PercentOutput, Utils.deadband(-Robot.m_io.xbox1.getRawAxis(1), deadband));
    // lower.set(ControlMode.PercentOutput, Utils.deadband(-Robot.m_io.xbox1.getRawAxis(5), deadband));

  }
}
