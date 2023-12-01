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


        frontRight.setDirection(DcMotorSimple.Direction.FORWARD);

        backRight.setDirection(DcMotorSimple.Direction.FORWARD);
            //wait for start button to be pressed
                waitForStart();

                moveMecanum(0.5), 0, 0);



    }

    private void driveForward(double v) {
    }
}
