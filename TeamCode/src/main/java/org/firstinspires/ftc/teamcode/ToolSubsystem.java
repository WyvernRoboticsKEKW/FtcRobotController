package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.SubsystemBase;

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

}




