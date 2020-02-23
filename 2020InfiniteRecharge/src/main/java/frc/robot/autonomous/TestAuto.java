package frc.robot.autonomous;

import edu.wpi.first.networktables.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.utils.vision.LimelightDefault;

public class TestAuto implements Autonomous {
    private double tx;
    @Override
    public void runAuto() {
        double MOE = 1.5;
        // tx = (getTV() == 1) ? getTX() : 10.0f;
        tx = LimelightDefault.getTX();
        double heading_error = -tx;
        double steering_adjust = 0.0f;
        if(tx > MOE) {
            steering_adjust = -0.01*heading_error;
        } else if(tx < -MOE) {
            steering_adjust = -0.01*heading_error;
        } else {
            steering_adjust = 0;
        try {
            Thread.sleep(250);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Robot.m_shooterSubsystem.shoot(0.55); //.7826
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Robot.m_intakeSubsystem.upper(0.4);
        Robot.m_intakeSubsystem.lower(0.4);
            // Robot.m_intakeSubsystem.upper(0.7);
            // Robot.m_intakeSubsystem.lower(0.7);
        }
        steering_adjust = (steering_adjust > 0.10) ? 0.10 : steering_adjust;
        steering_adjust = (steering_adjust < -0.10) ? -0.10 : steering_adjust;
        // controllerLeft.setReference(steering_adjust * MAX_VELOCITY, ControlType.kVelocity);
        // controllerRight.setReference(-steering_adjust * MAX_VELOCITY, ControlType.kVelocity);
        Robot.m_driveSubsystem.move(-steering_adjust, steering_adjust);
        // System.out.println("L: " + -steering_adjust + " R: " + steering_adjust);
        SmartDashboard.putNumber("rightMotor", steering_adjust);
        SmartDashboard.putNumber("leftMotor", -steering_adjust);
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