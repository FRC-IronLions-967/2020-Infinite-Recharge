/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Servo;

import edu.wpi.first.wpilibj2.command.Subsystem;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Robot;
import frc.robot.utils.Utils;

public class ElevatorSubsystem extends SubsystemBase {
  private TalonSRX elevator0;
  private TalonSRX elevator1;
  private TalonSRX roller;
  private Servo jam0;
  private Servo jam1;

  /**
   * Creates a new ElevatorSubsystem.
   */
  public ElevatorSubsystem() {
    elevator0 = new TalonSRX(Integer.parseInt(Robot.m_robotMap.getValue("elevator0")));
    elevator1 = new TalonSRX(Integer.parseInt(Robot.m_robotMap.getValue("elevator1")));
    roller = new TalonSRX(Integer.parseInt(Robot.m_robotMap.getValue("roller")));
    jam0 = new Servo(Integer.parseInt(Robot.m_robotMap.getValue("jam0")));
    jam1 = new Servo(Integer.parseInt(Robot.m_robotMap.getValue("jam1")));

    elevator0.setInverted(false);
    elevator1.setInverted(false);
    roller.setInverted(false);
    
    

    // elevator1.follow(elevator0);

  }

  public void jam(double x){
    jam1.set(x);

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    // elevator0.set(ControlMode.PercentOutput, (Robot.m_io.xbox1_povN.get()) ? 0.5 : ((Robot.m_io.xbox1_povS.get()) ? -0.5 : 0.0));
    elevator0.set(ControlMode.PercentOutput, Utils.deadband(-Robot.m_io.xbox1.getRawAxis(1), .12));
    elevator1.set(ControlMode.PercentOutput, Utils.deadband(-Robot.m_io.xbox1.getRawAxis(5), .12));

    roller.set(ControlMode.PercentOutput, (Robot.m_io.xbox1_povE.get()) ? 0.5 : ((Robot.m_io.xbox1_povW.get()) ? -0.5 : 0.0));
  }
}
