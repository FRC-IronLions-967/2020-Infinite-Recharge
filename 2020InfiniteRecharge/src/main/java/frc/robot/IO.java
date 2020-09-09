package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.POVButton;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.*;

public class IO {
    private static IO instance;

    public Joystick xbox0;
    public Joystick xbox1;

    public POVButton xbox0_povN;
    public POVButton xbox0_povNE;
    public POVButton xbox0_povE;
    public POVButton xbox0_povSE;
    public POVButton xbox0_povS;
    public POVButton xbox0_povSW;
    public POVButton xbox0_povW;
    public POVButton xbox0_povNW;
    public POVButton xbox1_povN;
    public POVButton xbox1_povNE;
    public POVButton xbox1_povE;
    public POVButton xbox1_povSE;
    public POVButton xbox1_povS;
    public POVButton xbox1_povSW;
    public POVButton xbox1_povW;
    public POVButton xbox1_povNW;
    public JoystickButton xbox0_a;
    public JoystickButton xbox0_b;
    public JoystickButton xbox0_x;
    public JoystickButton xbox0_y;
    JoystickButton xbox1_a;
    JoystickButton xbox1_b;
    JoystickButton xbox1_x;
    JoystickButton xbox1_y;
    JoystickButton xbox1_bump_l;
    JoystickButton xbox1_bump_r;
    JoystickButton xbox1_select;
    JoystickButton xbox1_start;

    private IO() {
        //initialize both of the Xbox controllers here, 0 is the drive controller, 1 controls the manipulators
        xbox0 = new Joystick(0);
        xbox1 = new Joystick(1);

        //initialize all of the buttons on the Xbox controllers here
        xbox0_a = new JoystickButton(xbox0, 1);
        xbox0_b = new JoystickButton(xbox0, 2);
        xbox0_x = new JoystickButton(xbox0, 3);
        xbox0_y = new JoystickButton(xbox0, 4);
        xbox0_povN = new POVButton(xbox0, 0, 0);
        xbox0_povNE = new POVButton(xbox0, 45, 0);
        xbox0_povE = new POVButton(xbox0, 90, 0);
        xbox0_povSE = new POVButton(xbox0, 135, 0);
        xbox0_povS = new POVButton(xbox0, 180, 0);
        xbox0_povSW = new POVButton(xbox0, 225, 0);
        xbox0_povW = new POVButton(xbox0, 270, 0);
        xbox0_povNW = new POVButton(xbox0, 315, 0);

        xbox1_a = new JoystickButton(xbox1, 1);
        xbox1_b = new JoystickButton(xbox1, 2);
        xbox1_x = new JoystickButton(xbox1, 3);
        xbox1_y = new JoystickButton(xbox1, 4);
        xbox1_bump_l = new JoystickButton(xbox1, 5);
        xbox1_bump_r = new JoystickButton(xbox1, 6);
        xbox1_select = new JoystickButton(xbox1, 7);
        xbox1_start = new JoystickButton(xbox1, 8);
        xbox1_povN = new POVButton(xbox1, 0, 0);
        xbox1_povNE = new POVButton(xbox1, 45, 0);
        xbox1_povE = new POVButton(xbox1, 90, 0);
        xbox1_povSE = new POVButton(xbox1, 135, 0);
        xbox1_povS = new POVButton(xbox1, 180, 0);
        xbox1_povSW = new POVButton(xbox1, 225, 0);
        xbox1_povW = new POVButton(xbox1, 270, 0);
        xbox1_povNW = new POVButton(xbox1, 315, 0);
        //Assigns commands to each of the buttons
        //TODO assign commands to button presses here
        // xbox0_povE.whenPressed(new AimRightCommand(0.05));
        // xbox0_povW.whenPressed(new AimLeftCommand(0.05));


        // xbox1_start.whenPressed(new AutoRPMCommand());
        // xbox1_select.whenPressed(new JamCommand());
        // xbox1_a.whenPressed(new IntakeCommand(0.8));
        // // xbox1_a.whenReleased(new IntakeCommand(0.0));
        // xbox1_x.whenPressed(new LowerBeltCommand(0.3));
        // xbox1_x.whenReleased(new LowerBeltCommand(0.0));
        // xbox1_b.whenPressed(new UpperBeltCommand(0.3));
        // xbox1_b.whenReleased(new UpperBeltCommand(0.0));
        // xbox1_y.whenPressed(new ToggleBeltsCommand());
        // xbox1_bump_l.whenPressed(new RPMDownCommand());
        // xbox1_bump_r.whenPressed(new RPMUpCommand());
    }

    public static IO getInstance() {
        if(instance == null) instance = new IO();

        return instance;
    }

    public void telopInit() {
        xbox0_povE.whenPressed(new AimRightCommand(0.05));
        xbox0_povW.whenPressed(new AimLeftCommand(0.05));


        xbox1_start.whenPressed(new AutoRPMCommand());
        xbox1_select.whenPressed(new JamCommand());
        xbox1_a.whenPressed(new IntakeCommand(0.8));
        xbox1_a.whenReleased(new IntakeCommand(0.0));
        xbox1_x.whenPressed(new LowerBeltCommand(0.7));
        xbox1_x.whenReleased(new LowerBeltCommand(0.0));
        xbox1_b.whenPressed(new UpperBeltCommand(0.3));
        xbox1_b.whenReleased(new UpperBeltCommand(0.0));
        xbox1_y.whenPressed(new ToggleBeltsCommand());
        xbox1_bump_l.whenPressed(new RPMDownCommand());
        xbox1_bump_r.whenPressed(new RPMUpCommand());
        xbox1_povW.whenPressed(new SetRPMCommand(3900));
        xbox1_povE.whenPressed(new SetRPMCommand(4500));
    }
}