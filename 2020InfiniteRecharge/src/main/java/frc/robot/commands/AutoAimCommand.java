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
import frc.robot.Robot;
import frc.robot.subsystems.SubsystemsInstance;
import frc.robot.utils.vision.*;

public class AutoAimCommand extends CommandBase {
  private boolean finished;
  private double tx;
  private long startTime = 0;
  private SubsystemsInstance inst;
  /**
   * Creates a new AutoAimCommand.
   */
  public AutoAimCommand(long startTime) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.startTime = startTime;
    inst = SubsystemsInstance.getInstance();
    addRequirements(inst.m_driveSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    // startTime = System.currentTimeMillis();
    // CommandScheduler.getInstance().setDefaultCommand(Robot.m_driveSubsystem, this);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double MOE = 1.0;
    // tx = (getTV() == 1) ? getTX() : 10.0f;
    tx = LimelightDefault.getTX();
    double heading_error = -tx;
    double steering_adjust = 0.0f;
    if(tx > MOE || tx < -MOE) {
      steering_adjust = -0.02*heading_error;
    } else {
      steering_adjust = 0;
      finished = true;
      inst.m_driveSubsystem.lastAimSuccessful = true;
    }
      // try {
      //   Thread.sleep(250);
      // } catch (InterruptedException e) {
      //   e.printStackTrace();
      // }
    //TODO clean this up this is ugly - Nathan
    double ty = LimelightDefault.getTY() + 30;
    if(ty > 50.8) {
      Robot.maxRPM = Robot.rpmLookup[0];
    } else if(ty > 48.3) {
      Robot.maxRPM = Robot.rpmLookup[1];
    } else if(ty > 46.14) {
      Robot.maxRPM = Robot.rpmLookup[2];
    } else if(ty > 44.1) {
      Robot.maxRPM = Robot.rpmLookup[3];
    } else if(ty > 42.31) {
      Robot.maxRPM = Robot.rpmLookup[4];
    } else if(ty > 39.8) {
      Robot.maxRPM = Robot.rpmLookup[5];
    } else if(ty > 37.26) {
      Robot.maxRPM = Robot.rpmLookup[6];
    } else if(ty > 35.84) {
      Robot.maxRPM = Robot.rpmLookup[7];
    } else if(ty > 34.63) {
      Robot.maxRPM = Robot.rpmLookup[8];
    } else if(ty > 33.7) {
      Robot.maxRPM = Robot.rpmLookup[9];
    } else if(ty > 32.5) {
      Robot.maxRPM = Robot.rpmLookup[10];
    } else if(ty > 31.95) {
      Robot.maxRPM = Robot.rpmLookup[11];
    } else if(ty > 31.45) {
      Robot.maxRPM = Robot.rpmLookup[12];
    } else if(ty > 30.7) {
      Robot.maxRPM = Robot.rpmLookup[13];
    } else {
      Robot.maxRPM = 5600;
    }
    steering_adjust = (steering_adjust > 0.10) ? 0.10 : steering_adjust;
    steering_adjust = (steering_adjust < -0.10) ? -0.10 : steering_adjust;
    inst.m_driveSubsystem.move(-steering_adjust, steering_adjust);
    SmartDashboard.putNumber("rightMotor", steering_adjust);
    SmartDashboard.putNumber("leftMotor", -steering_adjust);
    SmartDashboard.putNumber("tx", tx);
    if(System.currentTimeMillis() - startTime > 2500) {
      finished = true;
    }
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
