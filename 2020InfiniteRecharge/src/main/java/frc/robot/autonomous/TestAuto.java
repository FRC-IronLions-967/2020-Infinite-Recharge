package frc.robot.autonomous;

import edu.wpi.first.networktables.*;
import frc.robot.Robot;

public class TestAuto implements Autonomous {
    @Override
    public void runAuto() {
        double MOE = 0.05;
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
        float tx = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tx").getNumber(0).floatValue();
            tx = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tx").getNumber(0).floatValue();
            double heading_error = -tx;
            double steering_adjust = 0.0f;
            System.out.println("The tx is " + tx);
            System.out.println("The heading error is " + heading_error);
            System.out.println("The initial steering adjust is " + steering_adjust);
            
            if (tx > 1.0) {
                steering_adjust = -0.1*heading_error - 0.05;
            } else if (tx < 1.0) {
                steering_adjust = -0.1*heading_error + 0.05;  
            } 
            if (tx > 0.5) {
                steering_adjust = -0.075*heading_error - 0.05;
            } else if (tx < 0.5) {
                steering_adjust = -0.075*heading_error + 0.05;  
            } 
            if (tx > 0.25) {
                steering_adjust = -0.05*heading_error - 0.05;
            } else if (tx < 0.25) {
                steering_adjust = -0.05*heading_error + 0.05;  
            }
            Robot.m_driveSubsystem.move(-steering_adjust, steering_adjust);
            System.out.println("The steering adjust is " + steering_adjust);

        }
        
    @Override
    public void stopAuto() {

    }
}