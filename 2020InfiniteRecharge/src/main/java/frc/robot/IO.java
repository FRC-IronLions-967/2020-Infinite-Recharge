/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

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
        JoystickButton xbox1_a = new JoystickButton(xbox1, 1);
        JoystickButton xbox1_b = new JoystickButton(xbox1, 2);
        JoystickButton xbox1_x = new JoystickButton(xbox1, 3);
        JoystickButton xbox1_y = new JoystickButton(xbox1, 4);
        
        //TODO assign commands to button presses here
    }
}
