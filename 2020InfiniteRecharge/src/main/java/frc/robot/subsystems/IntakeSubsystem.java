/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.Subsystem;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class IntakeSubsystem extends SubsystemBase implements Subsystem {
  /**
   * Creates a new IntakeSubsystem.
   */
  private CANSparkMax intakeMotor;

  private double MAX = 1.0;

  public IntakeSubsystem() {
    intakeMotor = new CANSparkMax(Integer.parseInt(Robot.m_robotMap.getValue("intakeMotor")), MotorType.kBrushless);
    // intakeMotor = new CANSparkMax(0, MotorType.kBrushless);
    intakeMotor.setInverted(true);
  }

  public void intake(double x) {
    x = (x > MAX) ? MAX : x;
    x = (x < MAX) ? -MAX : x;

    intakeMotor.set(x);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
