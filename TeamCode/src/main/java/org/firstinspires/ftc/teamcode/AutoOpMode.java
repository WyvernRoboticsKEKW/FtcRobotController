package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous
public class AutoOpMode extends CommandOpMode {
    StormFly stormFly;
    MecanumDriveSubsystem MecanumDrivesub;

    @Override
    public void initialize() {
        stormFly = StormFly.getInstance(this);

        MecanumDrivesub = new MecanumDriveSubsystem(stormFly);

        schedule(new AutoStrafe(1, stormFly, MecanumDrivesub));
        schedule(new AutoForward(1, stormFly, MecanumDrivesub));
    }


}
