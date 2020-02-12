package frc.robot.autonomous;

import edu.wpi.first.networktables.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;

public class TestAuto implements Autonomous {
    @Override
    public void runAuto() {
        double MOE = 0.3;
        //TODO need to get PIDs set up in drivesubsystem or separate class file
        //TODO need to get encoders and the constants for the gear train set up
        /*
        pseudocode
        navigate from starting pos to within vision range
        getDist from NetworkTables
        getAngle from NetworkTables
        while(dist != targetDist && angle != 0) {
            drive closer/farther based on encoders
            change angle either by rotation or by physically moving
        }
        check vision again
        fire - potentially use vision to track??
        keep firing until empty
        */
        //TODO nav code
        final float OFFSET = 0.0f;
        float tx = getTX();
            double heading_error = -tx;
            double steering_adjust = 0.0f;
            System.out.println("The tx is " + tx);
            System.out.println("The heading error is " + heading_error);
            System.out.println("The initial steering adjust is " + steering_adjust);
            int i = 0;
            for(;;) {
                if(tx > MOE) {
                    steering_adjust = -0.03*heading_error;
                } else if(tx < -MOE) {
                    steering_adjust = -0.03*heading_error;
                } else {
                    i++;
                }
                steering_adjust = (steering_adjust > 0.03) ? 0.03 : steering_adjust;
                steering_adjust = (steering_adjust < -0.03) ? -0.03 : steering_adjust;
                Robot.m_driveSubsystem.move(-steering_adjust, steering_adjust);
                // System.out.println("The steering adjust is " + steering_adjust);
                System.out.println("L: " + -steering_adjust + " R: " + steering_adjust);
                SmartDashboard.putNumber("rightMotor", steering_adjust);
                SmartDashboard.putNumber("leftMotor", -steering_adjust);
                SmartDashboard.putNumber("tx", tx);
                tx = (getTV() == 1) ? getTX() : tx;
                if(i > 100) break;
            }
            Robot.m_shooterSubsystem.shootRPM(1.0);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Robot.m_intakeSubsystem.upper(0.0);
            Robot.m_intakeSubsystem.lower(0.0);
            // if (tx > 1.0) {
            //     steering_adjust = -0.05*heading_error - OFFSET;
            // } else if (tx < 1.0) {
            //     steering_adjust = -0.05*heading_error + OFFSET;  
            // } 
            // if (tx > 0.5) {
            //     steering_adjust = -0.025*heading_error - OFFSET;
            // } else if (tx < 0.5) {
            //     steering_adjust = -0.025*heading_error + OFFSET;  
            // } 
            // if (tx > 0.25) {
            //     steering_adjust = -0.025*heading_error - OFFSET;
            // } else if (tx < 0.25) {
            //     steering_adjust = -0.025*heading_error + OFFSET;  
            // }
        }
        
    @Override
    public void stopAuto() {

    }

    public float getTX() {
        return NetworkTableInstance.getDefault().getTable("limelight").getEntry("tx").getNumber(0).floatValue();
    }

    public float getTV() {
        return NetworkTableInstance.getDefault().getTable("limelight").getEntry("tv").getNumber(0).floatValue();
    }
}