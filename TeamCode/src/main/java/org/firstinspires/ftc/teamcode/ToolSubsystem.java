package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;

public class ToolSubsystem extends SubsystemBase {
    StormFly stormFly;
    public ToolSubsystem(StormFly motors) {
        stormFly = motors;
    }
    int maxHeight = 1000; //to be replaced once tested

    public void spinmotorwithMaximumHeightLimit(double power) {


        if(stormFly.lift.getCurrentPosition()>maxHeight) {
            stormFly.lift.set(0);

        }
        else {
           stormFly.lift.set(power);

        }
    }
    public void Intakeroller(double power) {
        if (stormFly.gamepadTool.getButton(GamepadKeys.Button.A)) {
            stormFly.rollerIntake.set(power);
        }
        if (stormFly.gamepadTool.getButton(GamepadKeys.Button.B)) {
            stormFly.rollerIntake.set(power - power);
        }
    }


}




