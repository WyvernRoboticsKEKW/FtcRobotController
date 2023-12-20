package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.command.button.Button;
import com.arcrobotics.ftclib.command.button.GamepadButton;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;

public class Tool extends CommandBase {

    GamepadEx toolGamepad;
    ToolSubsystem toolsubsystem;
    double left;
    double right;
    double B;
    Button A;
    StormFly stormFly;
    public Tool(GamepadEx toolGamepad, ToolSubsystem subsystem, StormFly stormFly){
        toolsubsystem = subsystem;
        this.toolGamepad = toolGamepad;
        this.stormFly = stormFly;

    }

    public void initialize(){
        A = new GamepadButton(
                toolGamepad, GamepadKeys.Button.A
        );

    }
    public void execute() {
        right = toolGamepad.getTrigger(GamepadKeys.Trigger.RIGHT_TRIGGER);
        left = toolGamepad.getTrigger(GamepadKeys.Trigger.LEFT_TRIGGER);
        toolsubsystem.spinmotorwithMaximumHeightLimit(left - right);
        A.whenPressed(toolsubsystem::roll).whenReleased(toolsubsystem::stopRolling);

    }
}






