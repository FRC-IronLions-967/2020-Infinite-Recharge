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

public class RPMDownCommand extends CommandBase {
  private boolean finished = false;
  private SubsystemsInstance inst;
  /**
   * Creates a new RPMDownCommand.
   */
  public RPMDownCommand() {
    // Use addRequirements() here to declare subsystem dependencies.
    inst = SubsystemsInstance.getInstance();
    addRequirements(inst.m_shooterSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // int rpm = inst.m_shooterSubsystem.getMaxRPM();
    // if(rpm > 1000) {
    //    inst.m_shooterSubsystem.setMaxRPM(rpm - 50);
    // }
    inst.m_shooterSubsystem.decrementMaxRPM();
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
