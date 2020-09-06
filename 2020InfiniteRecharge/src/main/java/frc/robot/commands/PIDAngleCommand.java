/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.subsystems.SubsystemsInstance;

public class PIDAngleCommand extends CommandBase {
  private double angle;
  SubsystemsInstance inst;
  /**
   * Creates a new PIDAngleCommand.
   */
  public PIDAngleCommand(double angle) {
    // Use addRequirements() here to declare subsystem dependencies.
    inst = SubsystemsInstance.getInstance();
    addRequirements(inst.m_driveSubsystem, inst.m_testPIDSubsystem);
    this.angle = angle;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    inst.m_testPIDSubsystem.setSetpoint(angle);
    inst.m_testPIDSubsystem.enable();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
