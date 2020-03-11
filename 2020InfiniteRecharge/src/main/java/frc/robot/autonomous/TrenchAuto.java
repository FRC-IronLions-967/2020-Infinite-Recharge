package frc.robot.autonomous;

import com.revrobotics.CANEncoder;

import edu.wpi.first.networktables.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;

public class TrenchAuto implements Autonomous {
    private double tx;
    private boolean moved = false;
    @Override
    public void runAuto() {
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
        Robot.maxRPM = 3900;
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
        Robot.m_shooterSubsystem.shootRPM(1.0);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Robot.m_intakeSubsystem.upper(0.2);
        Robot.m_intakeSubsystem.lower(0.2);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Robot.m_intakeSubsystem.upper(0.0);
        Robot.m_intakeSubsystem.lower(0.0);
        Robot.m_shooterSubsystem.shootRPM(0.0);

        if(!moved) {
          Robot.m_driveSubsystem.move(-0.3, -0.3);
          CANEncoder rEncoder = Robot.m_driveSubsystem.rightMaster.getEncoder();
          CANEncoder lEncoder = Robot.m_driveSubsystem.leftMaster.getEncoder();
          rEncoder.setPosition(0.0);
          lEncoder.setPosition(0.0);
          while(rEncoder.getPosition() < 20 && lEncoder.getPosition() < 20);
          Robot.m_driveSubsystem.move(0.0, 0.0);
        }
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