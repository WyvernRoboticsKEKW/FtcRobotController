package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;

public class Tool extends CommandBase {

    GamepadEx toolGamepad;
    ToolSubsystem toolsubsystem;
    double left;
    double right;
    double A;
    double B;
    public Tool(GamepadEx toolGamepad, ToolSubsystem subsystem){
        toolsubsystem = subsystem;
        this.toolGamepad = toolGamepad;

    }

    public void initialize(){


    }
    public void execute() {
        right = toolGamepad.getTrigger(GamepadKeys.Trigger.RIGHT_TRIGGER);
        left = toolGamepad.getTrigger(GamepadKeys.Trigger.LEFT_TRIGGER);
        toolsubsystem.spinmotorwithMaximumHeightLimit(left - right);
        toolsubsystem.Intakeroller(A - B);
    }
}






