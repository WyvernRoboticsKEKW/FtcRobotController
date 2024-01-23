package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.drivebase.MecanumDrive;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.hardware.motors.MotorGroup;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;

public class MecanumDriveSubsystem extends SubsystemBase {
    StormFly stormFly;

    public GamepadEx gamepadDrivetrain;

    public MecanumDriveSubsystem(StormFly stormFly){

        this.stormFly = stormFly;
        gamepadDrivetrain = stormFly.gamepadDrivetrain;

    }
    public void driveFieldCentric(double forward, double strafe, double turn, double heading) {
        stormFly.mecanumDrive.driveFieldCentric(strafe, forward, turn, heading, false);
    }
    public void strafe(double power){
        stormFly.mecanumDrive.driveWithMotorPowers(power, power, -power, power);
    }
    public void forward(double power){
        stormFly.mecanumDrive.driveWithMotorPowers(power, power, power, power);
    }




}


