/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANError;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Robot;

public class ShooterSubsystem extends SubsystemBase {
  private CANSparkMax flywheel0;
  private CANSparkMax flywheel1;
  private CANPIDController controller0;
  private CANPIDController controller1;
  private int maxRPM = 3900;
  private int rpmLookup[] = {3900, 3900, 3900, 4100, 4200, 4400, 4450, 4600, 4800, 5000, 5200, 5400, 5600, 5700};

  /**
   * Creates a new ReplaceMeSubsystem.
   */
  public ShooterSubsystem() {
    
    //Assigns the robot IDs from the robotMap.properties file
    flywheel0 = new CANSparkMax(Integer.parseInt(Robot.m_robotMap.getValue("flywheel1")), MotorType.kBrushless);
    flywheel1 = new CANSparkMax(Integer.parseInt(Robot.m_robotMap.getValue("flywheel0")), MotorType.kBrushless);
    
    //Sets flywheel1 to follow flywheel0
    flywheel1.follow(flywheel0);

    //Sets the flywheels to run inverted
    flywheel0.setInverted(false);
    flywheel1.setInverted(false);

    //PID stuff
    controller0 = flywheel0.getPIDController();

    double kP = 9.0e-8; 
    double kI = 2.7e-8;
    double kD = 0; 
    double kIz = 0; 
    double kFF = 1.58e-4; 
    double kMaxOutput = 1; 
    double kMinOutput = -1;

    controller0.setP(kP);
    controller0.setI(kI);
    controller0.setD(kD);
    controller0.setIZone(kIz);
    controller0.setFF(kFF);
    controller0.setOutputRange(kMinOutput, kMaxOutput);

  }

  public void shoot(double power) {
    // This method shoots the ball
    flywheel0.set(power);
  }

  public void shootRPM(double power) {
    double setPoint = power * maxRPM;
    SmartDashboard.putNumber("Setpoint", setPoint);
    if(setPoint != 0.0) {
      CANError err = controller0.setReference(setPoint, ControlType.kVelocity);
      DriverStation.reportError(err.toString(), false);
    } else {
      flywheel0.set(0.0);
    }
  }

  public double getRPM() {
    return flywheel0.getEncoder().getVelocity();
  }

  public int getMaxRPM() {
    return maxRPM;
  }

  public void setMaxRPM(int rpm) {
    this.maxRPM = rpm;
  }

  public int[] getRpmLookup() {
    return rpmLookup;
  }

  @Override
  public void periodic() {
    SmartDashboard.putNumber("Motor Power", flywheel0.get());
    // This method will be called once per scheduler run
  }
}
