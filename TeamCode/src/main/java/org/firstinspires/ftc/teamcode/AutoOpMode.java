package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@Autonomous
public class AutoOpMode extends CommandOpMode {
    StormFly stormFly;

    @Override
    public void initialize() {
        stormFly = StormFly.getInstance(this);



        //For the Tuscon program, we will strafe 2 inches away from the wall, then drive forward into the backstage (if we start from A2 or F2, 24 rotations should be
        // enough. If we start from A4 or F4, 12 rotations should be enough.)(If you're wondering, our wheel diameter is 4 inches) - Finch (Will)


    }

    private void driveForward(double v) {
    }
}
