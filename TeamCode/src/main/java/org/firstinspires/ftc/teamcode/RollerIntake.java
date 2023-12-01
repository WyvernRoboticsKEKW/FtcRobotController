package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.DcMotor;

public class RollerIntake extends SubsystemBase {
    StormFly stormFly;

    public RollerIntake(StormFly stormfly) {
        this.stormFly = stormfly;
        stormFly.rollerIntake.set(1);

    }

}    private boolean isIntakeOn;

