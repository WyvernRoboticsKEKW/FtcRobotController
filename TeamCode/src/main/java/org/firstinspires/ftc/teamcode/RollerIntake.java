package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.DcMotor;

public class RollerIntake extends SubsystemBase {
    StormFly stormFly;
    private boolean isIntakeOn;

    public RollerIntake(StormFly stormfly) {
        this.stormFly = stormfly;


    }
    public void roll(double power){
        stormFly.rollerIntake.set(power);
    }
}

