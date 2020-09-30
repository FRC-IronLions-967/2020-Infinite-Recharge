package frc.robot.autonomous;

import com.revrobotics.CANPIDController;

import edu.wpi.first.networktables.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.Robot;
import frc.robot.subsystems.SubsystemsInstance;

public class TestAuto implements Autonomous {
    private double tx;
    private boolean moved = false;
    private CANPIDController controllerRight;
    private CANPIDController controllerLeft;
    private SubsystemsInstance inst;

    @Override
    public void initAuto() {
        double kP = 5e-6; 
        double kI = 1e-6;
        double kD = 0; 
        double kIz = 0; 
        double kFF = 0; 
        double kMaxOutput = 1; 
        double kMinOutput = -1;

        controllerRight = inst.m_driveSubsystem.rightMaster.getPIDController();
        controllerLeft = inst.m_driveSubsystem.leftMaster.getPIDController();

        controllerRight.setP(kP);
        controllerRight.setI(kI);
        controllerRight.setD(kD);
        controllerRight.setIZone(kIz);
        controllerRight.setFF(kFF);
        controllerRight.setOutputRange(kMinOutput, kMaxOutput);

        controllerLeft.setP(kP);
        controllerLeft.setI(kI);
        controllerLeft.setD(kD);
        controllerLeft.setIZone(kIz);
        controllerLeft.setFF(kFF);
        controllerLeft.setOutputRange(kMinOutput, kMaxOutput);
    }

    @Override
    public void runAuto() {
        inst = SubsystemsInstance.getInstance();
        // double MOE = 1.0;
        // // tx = (getTV() == 1) ? getTX() : 10.0f;
        // tx = LimelightDefault.getTX();
        // double heading_error = -tx;
        // double steering_adjust = 0.0f;
        // if(tx > MOE || tx < -MOE) {
        //     steering_adjust = -0.01*heading_error;
        // } else {
        //     steering_adjust = 0;
        // try {
        //     Thread.sleep(250);
        // } catch (InterruptedException e) {
        //     e.printStackTrace();
        // }
        inst.m_shooterSubsystem.setMaxRPM(3900);
        // double ty = LimelightDefault.getTY() + 30;
        // if(ty > 50.8) {
        //     Robot.maxRPM = Robot.rpmLookup[0];
        //   } else if(ty > 48.3) {
        //     Robot.maxRPM = Robot.rpmLookup[1];
        //   } else if(ty > 46.14) {
        //     Robot.maxRPM = Robot.rpmLookup[2];
        //   } else if(ty > 44.1) {
        //     Robot.maxRPM = Robot.rpmLookup[3];
        //   } else if(ty > 42.31) {
        //     Robot.maxRPM = Robot.rpmLookup[4];
        //   } else if(ty > 39.8) {
        //     Robot.maxRPM = Robot.rpmLookup[5];
        //   } else if(ty > 37.26) {
        //     Robot.maxRPM = Robot.rpmLookup[6];
        //   } else if(ty > 35.84) {
        //     Robot.maxRPM = Robot.rpmLookup[7];
        //   } else if(ty > 34.63) {
        //     Robot.maxRPM = Robot.rpmLookup[8];
        //   } else if(ty > 33.7) {
        //     Robot.maxRPM = Robot.rpmLookup[9];
        //   } else if(ty > 32.5) {
        //     Robot.maxRPM = Robot.rpmLookup[10];
        //   } else if(ty > 31.95) {
        //     Robot.maxRPM = Robot.rpmLookup[11];
        //   } else if(ty > 31.45) {
        //     Robot.maxRPM = Robot.rpmLookup[12];
        //   } else if(ty > 30.7) {
        //     Robot.maxRPM = Robot.rpmLookup[13];
        //   } else {
        //     Robot.maxRPM = 5350;
        //   }
        inst.m_shooterSubsystem.shootRPM(1.0);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        inst.m_intakeSubsystem.upper(0.2);
        inst.m_intakeSubsystem.lower(0.2);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        inst.m_intakeSubsystem.upper(0.0);
        inst.m_intakeSubsystem.lower(0.0);
            // inst.m_intakeSubsystem.upper(0.7);
            // inst.m_intakeSubsystem.lower(0.7);
        if(!moved) {
          try {
            // Thread.sleep(3000);
            inst.m_driveSubsystem.move(-0.3, -0.3);
            
            Thread.sleep(1500);
            inst.m_driveSubsystem.move(0.0, 0.0);
            moved = true;
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
        inst.m_shooterSubsystem.shootRPM(0.0);
        // controllerLeft.setReference(steering_adjust * MAX_VELOCITY, ControlType.kVelocity);
        // controllerRight.setReference(-steering_adjust * MAX_VELOCITY, ControlType.kVelocity);
        // System.out.println("L: " + -steering_adjust + " R: " + steering_adjust);
        // SmartDashboard.putNumber("rightMotor", steering_adjust);
        // SmartDashboard.putNumber("leftMotor", -steering_adjust);
        SmartDashboard.putNumber("tx", tx);
    }
        
    @Override
    public void stopAuto() {

    }

    public double getTX() {
        return NetworkTableInstance.getDefault().getTable("limelight").getEntry("tx").getNumber(0).doubleValue();
    }

    public double getTV() {
        return NetworkTableInstance.getDefault().getTable("limelight").getEntry("tv").getNumber(0).doubleValue();
    }
}