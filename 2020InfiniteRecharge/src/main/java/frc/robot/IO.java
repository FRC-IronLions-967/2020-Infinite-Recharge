/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.POVButton;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.*;

/**
 * Add your docs here.
 */
public class IO {
    public Joystick xbox0;
    public Joystick xbox1;

    public IO() {
        //initialize both of the Xbox controllers here
        xbox0 = new Joystick(0);
        xbox1 = new Joystick(1);

        //initialize all of the buttons on the Xbox controllers here
        JoystickButton xbox0_a = new JoystickButton(xbox0, 1);
        JoystickButton xbox0_b = new JoystickButton(xbox0, 2);
        JoystickButton xbox0_x = new JoystickButton(xbox0, 3);
        JoystickButton xbox0_y = new JoystickButton(xbox0, 4);
        POVButton xbox0_povN = new POVButton(xbox0, 0, 0);
        POVButton xbox0_povNE = new POVButton(xbox0, 45, 0);
        POVButton xbox0_povE = new POVButton(xbox0, 90, 0);
        POVButton xbox0_povSE = new POVButton(xbox0, 135, 0);
        POVButton xbox0_povS = new POVButton(xbox0, 180, 0);
        POVButton xbox0_povSW = new POVButton(xbox0, 225, 0);
        POVButton xbox0_povW = new POVButton(xbox0, 270, 0);
        POVButton xbox0_povNW = new POVButton(xbox0, 315, 0);

        JoystickButton xbox1_a = new JoystickButton(xbox1, 1);
        JoystickButton xbox1_b = new JoystickButton(xbox1, 2);
        JoystickButton xbox1_x = new JoystickButton(xbox1, 3);
        JoystickButton xbox1_y = new JoystickButton(xbox1, 4);
        
        //TODO assign commands to button presses here
        // xbox0_povN.whenPressed(new PIDAngleCommand(0.0));
        // xbox0_povE.whenPressed(new PIDAngleCommand(90.0));
        // xbox0_povS.whenPressed(new PIDAngleCommand(180.0));
        // xbox0_povW.whenPressed(new PIDAngleCommand(-90.0));
        xbox0_povE.whenPressed(new AimRightCommand(0.02));
        xbox0_povW.whenPressed(new AimLeftCommand(0.02));
        xbox0_povE.whenReleased(new AimRightCommand(0));
        xbox0_povW.whenReleased(new AimLeftCommand(0));
        // xbox1_x.whileHeld(new IntakeCommand(0.5));
        xbox1_x.whenPressed(new IntakeCommand(0.8));
        xbox1_x.whenReleased(new IntakeCommand(0.0));
        // xbox1_a.whileHeld(new LowerBeltCommand(0.5));
        xbox1_a.whenPressed(new LowerBeltCommand(0.5));
        xbox1_a.whenReleased(new LowerBeltCommand(0.0));
        // xbox1_b.whileHeld(new UpperBeltCommand(0.5));
        xbox1_b.whenPressed(new UpperBeltCommand(0.5));
        xbox1_b.whenReleased(new UpperBeltCommand(0.0));
    }
}
