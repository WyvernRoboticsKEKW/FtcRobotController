package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.Robot;
import com.arcrobotics.ftclib.drivebase.MecanumDrive;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class StormFly extends Robot {
    public MecanumDrive mecanum;
    private static StormFly instance;

    Motor frontLeft;
    Motor frontRight;
    Motor backLeft;
    Motor backRight;

    public GamepadEx gamepadDrivetrain;
    public GamepadEx gamepadTool;

    public static StormFly getInstance(CommandOpMode opMode) {
        if (instance != null) {
            instance = new StormFly(opMode);
        }
        return (instance);
    }

    public StormFly(CommandOpMode opMode) {
        HardwareMap hardwareMap = opMode.hardwareMap;


        frontLeft = new Motor(hardwareMap, "frontLeft");
        frontRight = new Motor(hardwareMap, "frontRight");
        backLeft = new Motor(hardwareMap, "backLeft");
        backRight = new Motor(hardwareMap, "backRight");

        mecanum = new MecanumDrive(frontLeft, frontRight, backLeft, backRight);

    }
}
