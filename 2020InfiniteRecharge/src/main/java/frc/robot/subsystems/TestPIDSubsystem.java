/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDSubsystem;
import frc.robot.Robot;

public class TestPIDSubsystem extends PIDSubsystem {
  /**
   * Creates a new TestPIDSubsystem.
   */
  public TestPIDSubsystem() {
    super(
        // The PIDController used by the subsystem
        new PIDController(Double.parseDouble(Robot.m_values.getValue("P")), Double.parseDouble(Robot.m_values.getValue("I")), Double.parseDouble(Robot.m_values.getValue("D"))));
  }

  @Override
  public void useOutput(double output, double setpoint) {
    // Use the output here
  }

  @Override
  public double getMeasurement() {
    // Return the process variable measurement here
    return 0;
  }
}
