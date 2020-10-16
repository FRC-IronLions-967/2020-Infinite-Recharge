/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.utils.vision.LimelightDefault;
import frc.robot.subsystems.*;

public class AutoRPMCommand extends CommandBase {
  private boolean finished = false;
  private SubsystemsInstance inst;
  private int[] rpmLookup;
  /**
   * Creates a new AutoRPMCommand.
   */
  public AutoRPMCommand() {
    // Use addRequirements() here to declare subsystem dependencies.
    inst = SubsystemsInstance.getInstance();
    rpmLookup = inst.m_shooterSubsystem.getRpmLookup();
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double ty = LimelightDefault.getTY() + 30;
    if(ty > 50.8) {
      inst.m_shooterSubsystem.setMaxRPM(rpmLookup[0]);
    } else if(ty > 48.3) {
      inst.m_shooterSubsystem.setMaxRPM(rpmLookup[1]);
    } else if(ty > 46.14) {
      inst.m_shooterSubsystem.setMaxRPM(rpmLookup[2]);
    } else if(ty > 44.1) {
      inst.m_shooterSubsystem.setMaxRPM(rpmLookup[3]);
    } else if(ty > 42.31) {
      inst.m_shooterSubsystem.setMaxRPM(rpmLookup[4]);
    } else if(ty > 39.8) {
      inst.m_shooterSubsystem.setMaxRPM(rpmLookup[5]);
    } else if(ty > 37.26) {
      inst.m_shooterSubsystem.setMaxRPM(rpmLookup[6]);
    } else if(ty > 35.84) {
      inst.m_shooterSubsystem.setMaxRPM(rpmLookup[7]);
    } else if(ty > 34.63) {
      inst.m_shooterSubsystem.setMaxRPM(rpmLookup[8]);
    } else if(ty > 33.7) {
      inst.m_shooterSubsystem.setMaxRPM(rpmLookup[9]);
    } else if(ty > 32.5) {
      inst.m_shooterSubsystem.setMaxRPM(rpmLookup[10]);
    } else if(ty > 31.95) {
      inst.m_shooterSubsystem.setMaxRPM(rpmLookup[11]);
    } else if(ty > 31.45) {
      inst.m_shooterSubsystem.setMaxRPM(rpmLookup[12]);
    } else if(ty > 30.7) {
      inst.m_shooterSubsystem.setMaxRPM(rpmLookup[13]);
    } else {
      inst.m_shooterSubsystem.setMaxRPM(5600);
    }
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
