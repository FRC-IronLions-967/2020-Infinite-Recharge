/*
    This file defines a singleton class because we will never need more than one instance of a subsystem
    I (Nathan) did this to try and keep the subsystems in one place instead of as public static variables
    in Robot.java, which I thought was probably a bad practice and was generally more unreadable
*/


package frc.robot.subsystems;

public class SubsystemsInstance {
    private static SubsystemsInstance instance;

    public DriveSubsystem m_driveSubsystem;
    public IntakeSubsystem m_intakeSubsystem;
    public ShooterSubsystem m_shooterSubsystem;
    public TestPIDSubsystem m_testPIDSubsystem;
    public ElevatorSubsystem m_elevatorSubsystem;
    public KalmanSubsystem m_kalmanSubsystem;

    private SubsystemsInstance() {
        m_driveSubsystem = new DriveSubsystem();
        m_elevatorSubsystem = new ElevatorSubsystem();
        m_intakeSubsystem = new IntakeSubsystem();
        m_shooterSubsystem = new ShooterSubsystem();
        m_testPIDSubsystem = new TestPIDSubsystem();
        m_kalmanSubsystem = new KalmanSubsystem();
    }

    public static SubsystemsInstance getInstance() {
        if(instance == null) instance = new SubsystemsInstance();

        return instance;
    }
}