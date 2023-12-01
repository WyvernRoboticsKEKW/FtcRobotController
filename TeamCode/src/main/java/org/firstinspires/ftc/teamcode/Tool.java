package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;

public class Tool extends CommandBase {

    GamepadEx toolGamepad;
    RollerIntake toolsubsystem;

    public Tool(GamepadEx toolGamepad, RollerIntake subsystem){
        toolsubsystem = subsystem;
        this.toolGamepad = toolGamepad;

    }

    public void initialize(){

        toolsubsystem.roll(toolGamepad.getTrigger(GamepadKeys.Trigger.RIGHT_TRIGGER));


    }
}






