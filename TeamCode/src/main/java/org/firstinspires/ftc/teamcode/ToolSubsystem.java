package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.SubsystemBase;

public class ToolSubsystem extends SubsystemBase {
    StormFly stormFly;
    public ToolSubsystem(StormFly motors) {
        stormFly = motors;
    }
    int maxHeight = 1000; //to be replaced once tested

    public void spinmotorwithMaximumHeightLimit(double power) {stormFly.lift.set(power);
    }
    public void rollIntakeIn() {
        stormFly.rollerIntake.set(-1);
    }public void rollIntakeOut(){
            stormFly.rollerIntake.set(1);
    }public void stopRollingIntake(){
        stormFly.rollerIntake.set(0);
    }
    public void rollExtake() {
        stormFly.rollerExtake.set(.75);
    }public void stopRollingExtake() {stormFly.rollerExtake.set(0);
    }public void armUp() {
        stormFly.hangArm.set(1);
    }public void armDown() {
        stormFly.hangArm.set(-1);
    }public void armStop() {
        stormFly.hangArm.set(0);
    }
}






