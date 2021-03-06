/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.IO;
import frc.robot.Robot;
import frc.robot.subsystems.*;

public class IntakeCommand extends CommandBase {
  private double power;
  private boolean finished =  false;
  private SubsystemsInstance inst;

  /**
   * Creates a new IntakeCommand.
   */
  public IntakeCommand(double power) {
    // Use addRequirements() here to declare subsystem dependencies.
    inst = SubsystemsInstance.getInstance();
    addRequirements(inst.m_intakeSubsystem);
    this.power = power;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // Robot.m_intakeSubsystem.intake(Utils.deadband(io.xbox1.getRawAxis(1), Double.parseDouble(Robot.m_values.getValue("deadband"))));
    inst.m_intakeSubsystem.intake(power);
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
