/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Robot;

public class ShooterSubsystem extends SubsystemBase {
  private CANSparkMax flywheel0;
  private CANSparkMax flywheel1;
  /**
   * Creates a new ReplaceMeSubsystem.
   */
  public ShooterSubsystem() {
    flywheel0 = new CANSparkMax(Integer.parseInt(Robot.m_robotMap.getValue("flywheel0")), MotorType.kBrushless);
    flywheel1 = new CANSparkMax(Integer.parseInt(Robot.m_robotMap.getValue("flywheel1")), MotorType.kBrushless);

    flywheel1.follow(flywheel0);

    flywheel0.setInverted(true);
    flywheel1.setInverted(true);
  }

  public void shoot(double power) {
    flywheel0.set(power);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
