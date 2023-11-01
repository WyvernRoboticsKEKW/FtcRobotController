package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.drivebase.MecanumDrive;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.hardware.motors.MotorGroup;

public class MecanumDriveSubsystem extends SubsystemBase {
    StormFly stormFly;
    public MecanumDrive m_mecanum;
    public GamepadEx gamepadDrivetrain;


    public MecanumDriveSubsystem(MecanumDrive drive, GamepadEx gamepadEx){

        m_mecanum = drive;
        gamepadDrivetrain = gamepadEx;

    }
    public double getEncoders() {

        stormFly.frontLeft = stormFly.frontLeft;
        stormFly.frontRight = stormFly.frontRight;
        stormFly.backLeft = stormFly.backLeft;
        stormFly.backRight = stormFly.backRight;

        return 0;

    }



}


