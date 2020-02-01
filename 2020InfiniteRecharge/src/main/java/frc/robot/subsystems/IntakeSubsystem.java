/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj2.command.Subsystem;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Robot;

public class IntakeSubsystem extends SubsystemBase implements Subsystem {
  /**
   * Creates a new IntakeSubsystem.
   */
  private VictorSPX intake;
  private VictorSPX lower;
  private VictorSPX upper;


  private double MAX = 1.0;

  public IntakeSubsystem() {
    intake = new VictorSPX(Integer.parseInt(Robot.m_robotMap.getValue("intake")));
    lower = new VictorSPX(Integer.parseInt(Robot.m_robotMap.getValue("lowerBelt")));
    upper = new VictorSPX(Integer.parseInt(Robot.m_robotMap.getValue("upperBelt")));
  }

  public void intake(double x) {
    x = (x > MAX) ? MAX : x;
    x = (x < MAX) ? -MAX : x;

    intake.set(ControlMode.PercentOutput, x);
  }

  public void lower(double x) {
    x = (x > MAX) ? MAX : x;
    x = (x < MAX) ? -MAX : x;

    lower.set(ControlMode.PercentOutput, x);
  }

  public void upper(double x) {
    x = (x > MAX) ? MAX : x;
    x = (x < MAX) ? -MAX : x;

    upper.set(ControlMode.PercentOutput, x);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
