package frc.robot.autonomous;

import edu.wpi.first.networktables.NetworkTableEntry;
import frc.robot.Robot;

public class TestAuto implements Autonomous {
    @Override
    public void runAuto() {
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
        NetworkTableEntry tableArea = Robot.visionTable.getEntry("area");
        NetworkTableEntry tableWidth = Robot.visionTable.getEntry("width");
        double area = tableArea.getDouble(-1);
        double width = tableWidth.getDouble(-1);
    }

    @Override
    public void stopAuto() {

    }
}