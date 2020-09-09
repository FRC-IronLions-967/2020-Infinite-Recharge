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

public class JamCommand extends CommandBase {
  /**
   * Creates a new JamCommand.
   */
  private double pos;
  private boolean finished =  false;
  private SubsystemsInstance inst;

  public JamCommand() {
    // Use addRequirements() here to declare subsystem dependencies.
    inst = SubsystemsInstance.getInstance();
    addRequirements(inst.m_elevatorSubsystem);
    this.pos = pos;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //TODO move this boolean to the subsystem, makes more sense there
    //also I'm not sure what I was thinking when I wrote this, it needs to be altered or removed
    Robot.elevatorJammed = (Robot.elevatorJammed) ? false : true;
    if(Robot.elevatorJammed == false){
      inst.m_elevatorSubsystem.jam(pos);
    } else if(Robot.elevatorJammed == true){
      inst.m_elevatorSubsystem.jam(.5);
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
