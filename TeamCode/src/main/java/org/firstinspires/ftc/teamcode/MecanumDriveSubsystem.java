package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.drivebase.MecanumDrive;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.hardware.motors.MotorGroup;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;

public class MecanumDriveSubsystem extends SubsystemBase {
    StormFly stormFly;
    public MecanumDrive m_mecanum;
    public GamepadEx gamepadDrivetrain;


    public MecanumDriveSubsystem(MecanumDrive drive, GamepadEx gamepadEx){

        m_mecanum = drive;
        gamepadDrivetrain = gamepadEx;

    }
   public void mecanumDrive(){
        m_mecanum.driveFieldCentric(gamepadDrivetrain.getLeftX(), gamepadDrivetrain.getLeftY(), gamepadDrivetrain.getRightX(),
                stormFly.getHeading(), false);
   }



}


