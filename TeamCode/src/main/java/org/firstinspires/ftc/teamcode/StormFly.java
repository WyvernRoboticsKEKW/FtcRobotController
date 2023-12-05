package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.Robot;
import com.arcrobotics.ftclib.drivebase.MecanumDrive;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.ImuOrientationOnRobot;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

public class StormFly extends Robot {

    private static StormFly instance;

    Motor frontLeft;
    Motor frontRight;
    Motor backLeft;
    Motor backRight;
    Motor rollerIntake;
    IMU imu;
    public MecanumDrive mecanumDrive;


    public GamepadEx gamepadDrivetrain;
    public GamepadEx gamepadTool;

    public static StormFly getInstance(CommandOpMode opMode) {
        if (instance == null) {
            instance = new StormFly(opMode);
        }
        return (instance);
    }

    public StormFly(CommandOpMode opMode) {
        HardwareMap hardwareMap = opMode.hardwareMap;


      //  rollerIntake = new Motor(hardwareMap, "rollerIntake");
        frontLeft = new Motor(hardwareMap, "frontLeft");
        frontRight = new Motor(hardwareMap, "frontRight");
        backLeft = new Motor(hardwareMap, "backLeft");
        backRight = new Motor(hardwareMap, "backRight");


        //imu = hardwareMap.get(IMU.class, "imu");


        mecanumDrive = new MecanumDrive(frontLeft, frontRight, backLeft, backRight);
        gamepadDrivetrain = new GamepadEx(opMode.gamepad1);
        gamepadTool = new GamepadEx(opMode.gamepad2);

        //RevHubOrientationOnRobot orientationOnRobot = new RevHubOrientationOnRobot(RevHubOrientationOnRobot.LogoFacingDirection.UP,
          //      RevHubOrientationOnRobot.UsbFacingDirection.FORWARD

     //   );

        //imu.initialize(new IMU.Parameters(orientationOnRobot));

    }

   // public double getHeading(){

     //   Orientation Theta = imu.getRobotOrientation(AxesReference.INTRINSIC, AxesOrder.XYZ, AngleUnit.DEGREES);

       // return(Theta.firstAngle);
         //i'm writing this on thursday before i have to be gone for the tempe tournament,
       // grant and will just download the code. -Danaila
    //I coummented out a bunch of stuff to make the bot work -Isaac

  //  }

}

