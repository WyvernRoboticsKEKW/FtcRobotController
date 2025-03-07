package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.command.button.Button;
import com.arcrobotics.ftclib.command.button.GamepadButton;
import com.arcrobotics.ftclib.command.button.Trigger;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.arcrobotics.ftclib.gamepad.TriggerReader;

public class Tool extends CommandBase {

    GamepadEx toolGamepad;
    ToolSubsystem toolsubsystem;
    Button B;
    Button A;
    Button DpUp;
    Button DpDown;
    Button RightBumper;
    double LiftVelocity;

    StormFly stormFly;
    public Tool(GamepadEx toolGamepad, ToolSubsystem subsystem, StormFly stormFly){
        toolsubsystem = subsystem;
        this.toolGamepad = toolGamepad;
        this.stormFly = stormFly;

    }

    public void initialize(){
        A = new GamepadButton(toolGamepad, GamepadKeys.Button.A);
        B = new GamepadButton(toolGamepad, GamepadKeys.Button.B);
        DpUp = new GamepadButton(toolGamepad, GamepadKeys.Button.DPAD_UP);
        DpDown = new GamepadButton(toolGamepad, GamepadKeys.Button.DPAD_DOWN);
        RightBumper = new GamepadButton(toolGamepad, GamepadKeys.Button.RIGHT_BUMPER);
        stormFly.lift.stopAndResetEncoder();


    }
    public void execute() {


        LiftVelocity =  toolGamepad.getTrigger(GamepadKeys.Trigger.LEFT_TRIGGER) - toolGamepad.getTrigger(GamepadKeys.Trigger.RIGHT_TRIGGER);
        toolsubsystem.spinmotorwithMaximumHeightLimit(LiftVelocity);
        A.whenPressed(toolsubsystem::rollIntakeIn).whenReleased(toolsubsystem::stopRollingIntake);
        B.whenPressed(toolsubsystem::rollIntakeOut).whenReleased(toolsubsystem::stopRollingIntake);
        RightBumper.whenPressed(toolsubsystem::rollExtake).whenReleased(toolsubsystem::stopRollingExtake);
        DpUp.whenPressed(toolsubsystem::armUp).whenReleased(toolsubsystem::armStop);
        DpDown.whenPressed(toolsubsystem::armDown).whenReleased(toolsubsystem::armStop);
    }
}