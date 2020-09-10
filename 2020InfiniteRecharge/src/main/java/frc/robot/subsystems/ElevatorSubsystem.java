/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Servo;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.IO;
import frc.robot.Robot;

public class ElevatorSubsystem extends SubsystemBase {
  private TalonSRX elevator0;
  private TalonSRX elevator1;
  // private TalonSRX roller;
  private Servo jam0;
  private Servo jam1;

  private DigitalInput bottomLimit;
  private DigitalInput upperLimit;

  private IO io;
  /**
   * Creates a new ElevatorSubsystem.
   */
  public ElevatorSubsystem() {
    io = IO.getInstance();

    elevator0 = new TalonSRX(Integer.parseInt(Robot.m_robotMap.getValue("elevator0")));
    elevator1 = new TalonSRX(Integer.parseInt(Robot.m_robotMap.getValue("elevator1")));
    // roller = new TalonSRX(Integer.parseInt(Robot.m_robotMap.getValue("roller")));
    jam0 = new Servo(Integer.parseInt(Robot.m_robotMap.getValue("jam0")));
    jam1 = new Servo(Integer.parseInt(Robot.m_robotMap.getValue("jam1")));

    elevator0.setNeutralMode(NeutralMode.Brake);
    elevator1.setNeutralMode(NeutralMode.Brake);

    elevator0.setInverted(false);
    elevator1.setInverted(false);
    // roller.setInverted(false);
  }

  public void jam(double x){
    jam1.set(x);

    bottomLimit = new DigitalInput(0);
    upperLimit = new DigitalInput(1);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    if(io.getManipulatorController().isAngleMatched("N") || io.getManipulatorController().isAngleMatched("NE") || io.getManipulatorController().isAngleMatched("NW")) {
      elevator0.set(ControlMode.PercentOutput, 0.66);
      elevator1.set(ControlMode.PercentOutput, 0.59);
    } else if(io.getManipulatorController().isAngleMatched("S") || io.getManipulatorController().isAngleMatched("SE") || io.getManipulatorController().isAngleMatched("SW")) {
      elevator0.set(ControlMode.PercentOutput, -0.66);
      elevator1.set(ControlMode.PercentOutput, -0.59);
    } else {
      elevator0.set(ControlMode.PercentOutput, 0.0);
      elevator1.set(ControlMode.PercentOutput, 0.0);
    }
  }
}
