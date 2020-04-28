/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

#include "IO.h"
#include <frc/Joystick.h>
#include <frc/buttons/JoystickButton.h>

using namespace frc;

IO::IO() {
    xbox0 = Joystick(0);
    xbox1 = Joystick(1);

    JoystickButton xbox0_a = JoystickButton(&xbox0, 1);
}
