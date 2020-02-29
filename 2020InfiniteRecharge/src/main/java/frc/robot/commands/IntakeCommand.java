/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;

public class IntakeCommand extends CommandBase {
  private double power;
  private boolean finished =  false;

  /**
   * Creates a new IntakeCommand.
   */
  public IntakeCommand(double power) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(Robot.m_intakeSubsystem);
    this.power = power;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // Robot.m_intakeSubsystem.intake(Utils.deadband(Robot.m_io.xbox1.getRawAxis(1), Double.parseDouble(Robot.m_values.getValue("deadband"))));
    Robot.intakeOn = (Robot.intakeOn) ? false : true;
    Robot.m_intakeSubsystem.intake((Robot.intakeOn) ? power : 0.0);
    finished = true;
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return finished;
  }
}
