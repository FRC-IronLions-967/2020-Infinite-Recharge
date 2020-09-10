package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.POVButton;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.*;
import frc.robot.utils.controls.XBoxController;

public class IO {
    private static IO instance;

    private XBoxController driverController;
    private XBoxController manipulatorController;

    private IO() {
        driverController = new XBoxController(0);
        manipulatorController = new XBoxController(1);
    }

    public static IO getInstance() {
        if(instance == null) instance = new IO();

        return instance;
    }

    public void telopInit() {
        driverController.whenPOVButtonPressed("E", new AimRightCommand(0.05));
        driverController.whenPOVButtonPressed("W", new AimLeftCommand(0.05));

        manipulatorController.whenButtonPressed("START", new AutoRPMCommand());
        manipulatorController.whenButtonPressed("SELECT", new JamCommand());
        manipulatorController.whenButtonPressed("A", new IntakeCommand(0.8));
        manipulatorController.whenButtonReleased("A", new IntakeCommand(0.0));
        manipulatorController.whenButtonPressed("X", new LowerBeltCommand(0.7));
        manipulatorController.whenButtonPressed("X", new LowerBeltCommand(0.0));
        manipulatorController.whenButtonPressed("B", new UpperBeltCommand(0.3));
        manipulatorController.whenButtonPressed("B", new UpperBeltCommand(0.0));
        manipulatorController.whenButtonPressed("Y", new ToggleBeltsCommand());
        manipulatorController.whenButtonPressed("LBUMP", new RPMDownCommand());
        manipulatorController.whenButtonPressed("RBUMP", new RPMUpCommand());
        manipulatorController.whenPOVButtonPressed("W", new SetRPMCommand(3900));
        manipulatorController.whenPOVButtonPressed("E", new SetRPMCommand(4500));
    }

    public XBoxController getDriverController() {
        return driverController;
    }

    public XBoxController getManipulatorController() {
        return manipulatorController;
    }
}