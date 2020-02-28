/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.Robot;
import frc.robot.utils.vision.*;

public class AutoAimCommand extends CommandBase {
  private boolean finished;
  private double tx;
  /**
   * Creates a new AutoAimCommand.
   */
  public AutoAimCommand() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(Robot.m_driveSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    CommandScheduler.getInstance().setDefaultCommand(Robot.m_driveSubsystem, this);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double MOE = 1.5;
    // tx = (getTV() == 1) ? getTX() : 10.0f;
    tx = LimelightDefault.getTX();
    double heading_error = -tx;
    double steering_adjust = 0.0f;
    if(tx > MOE || tx < -MOE) {
      steering_adjust = -0.01*heading_error;
    } else {
      steering_adjust = 0;
      finished = true;
      try {
        Thread.sleep(250);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    steering_adjust = (steering_adjust > 0.10) ? 0.10 : steering_adjust;
    steering_adjust = (steering_adjust < -0.10) ? -0.10 : steering_adjust;
    Robot.m_driveSubsystem.move(-steering_adjust, steering_adjust);
    SmartDashboard.putNumber("rightMotor", steering_adjust);
    SmartDashboard.putNumber("leftMotor", -steering_adjust);
    SmartDashboard.putNumber("tx", tx);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    // CommandScheduler.getInstance().setDefaultCommand(Robot.m_driveSubsystem, new ArcadeDriveLookupCommand());
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return finished;
  }

  public double getTX() {
    return NetworkTableInstance.getDefault().getTable("limelight").getEntry("tx").getDouble(0.0);
  }
}
