package frc.robot.autonomous;

// this interface is for creating autonomous classes
// each class must implement the following methods
public interface Autonomous {
    //called when the autonomous period begins, do stuff like set up PID controllers and other sensors here
    public void initAuto();

    //put a control loop in here to run while the autonomous period is running
    //might change this at some point in the future as I'm (Nathan) pretty sure this is a bad practice
    public void runAuto();

    //called when the autonomous period ends, release resources here
    public void stopAuto();
}