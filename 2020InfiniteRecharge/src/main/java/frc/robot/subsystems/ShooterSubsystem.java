/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Robot;

public class ShooterSubsystem extends SubsystemBase {
  private CANSparkMax flywheel0;
  private CANSparkMax flywheel1;
  private double maxRPM = 5000;
  private CANPIDController controller0;
  private CANPIDController controller1;
  /**
   * Creates a new ReplaceMeSubsystem.
   */
  public ShooterSubsystem() {
    
    //Assigns the robot IDs fro mthe robotMap.properties file
    flywheel0 = new CANSparkMax(Integer.parseInt(Robot.m_robotMap.getValue("flywheel1")), MotorType.kBrushless);
    flywheel1 = new CANSparkMax(Integer.parseInt(Robot.m_robotMap.getValue("flywheel0")), MotorType.kBrushless);
    
    //Sets flywheel1 to follow flywheel0
    flywheel1.follow(flywheel0);

    //Sets the flywheels to run inverted
    flywheel0.setInverted(false);
    flywheel1.setInverted(false);

    //PID stuff
    controller0 = flywheel0.getPIDController();
    controller1 = flywheel1.getPIDController();

    double kP = 1e-4; 
    double kI = 1e-6;
    double kD = 0; 
    double kIz = 0; 
    double kFF = 0; 
    double kMaxOutput = 1; 
    double kMinOutput = -1;

    controller0.setP(kP);
    controller0.setI(kI);
    controller0.setD(kD);
    controller0.setIZone(kIz);
    controller0.setFF(kFF);
    controller0.setOutputRange(kMinOutput, kMaxOutput);

    controller1.setP(kP);
    controller1.setI(kI);
    controller1.setD(kD);
    controller1.setIZone(kIz);
    controller1.setFF(kFF);
    controller1.setOutputRange(kMinOutput, kMaxOutput);
  }

  public void shoot(double power) {
    // This method shoots the ball
    flywheel0.set(power);
  }

  public void shootRPM(double power) {
    double setPoint = power * maxRPM;
    controller0.setReference(setPoint, ControlType.kVelocity);
  }

  public double getRPM() {
    return flywheel0.getEncoder().getVelocity();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
