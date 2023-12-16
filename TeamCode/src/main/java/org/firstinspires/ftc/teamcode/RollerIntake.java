package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.hardware.DcMotor;

public class RollerIntake extends SubsystemBase {
    StormFly stormFly;


    public RollerIntake(StormFly stormfly) {
        this.stormFly = stormfly;
        stormFly.gamepadTool.getButton(GamepadKeys.Button.A);

        if(button.A)
    }
    public void roll(double power){stormFly.rollerIntake.set(power);
    }





    }

