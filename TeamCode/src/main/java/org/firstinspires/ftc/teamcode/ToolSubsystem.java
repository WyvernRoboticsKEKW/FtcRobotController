package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.SubsystemBase;

public class ToolSubsystem extends SubsystemBase {
    StormFly stormFly;
    public ToolSubsystem(StormFly motors) {
        stormFly = motors;
    }
    int maxHeight = 1000; //to be replaced once tested

    public void spinmotorwithMaximumHeightLimit(double power) {
        stormFly.lift.set(power);
    }
    public void roll(){
        stormFly.rollerIntake.set(1);
    }
    public void stopRolling(){
        stormFly.rollerIntake.set(0);
    }
    public void voidExtake() {stormFly.rollerExtake.set(1);}
    public void Extake() {stormFly.rollerExtake.set(0); }


            public void REVERSE() {
    }
}





