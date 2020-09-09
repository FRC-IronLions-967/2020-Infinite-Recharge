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
import frc.robot.utils.Utils;
import frc.robot.*;

public class ArcadeDriveCommand extends CommandBase {
  private SubsystemsInstance inst;
  private IO io;
  /**
   * Creates a new ArcadeDriveCommand.
   */
  public ArcadeDriveCommand() {
    // Use addRequirements() here to declare subsystem dependencies.
    inst = SubsystemsInstance.getInstance();
    io = IO.getInstance();
    addRequirements(inst.m_driveSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //run the arcade drive based off of the joystick inputs from the xbox driver controller
    inst.m_driveSubsystem.arcadeDrive(-Utils.deadband(io.xbox0.getRawAxis(1), Double.parseDouble(Robot.m_values.getValue("deadband"))), Utils.deadband(io.xbox0.getRawAxis(4), Double.parseDouble(Robot.m_values.getValue("deadband"))));
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
