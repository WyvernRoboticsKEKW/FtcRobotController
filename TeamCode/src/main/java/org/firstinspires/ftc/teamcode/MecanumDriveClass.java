package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.drivebase.MecanumDrive;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class MecanumDriveClass extends CommandBase {
    MecanumDriveSubsystem m_subsystemDrive;
    StormFly stormFly;
    double forward;
    GamepadEx driverGamepad;
    double strafe;
    double turn;
    double heading;


    public MecanumDriveClass(MecanumDriveSubsystem subsystem, GamepadEx driverGamepad) {

        m_subsystemDrive = subsystem;
        this.driverGamepad = driverGamepad;
    }
    public void initialize() {
        //heading = stormFly.getHeading();
    }

    public void execute() {
        m_subsystemDrive.driveFieldCentric(forward, strafe, turn, heading);
        forward = -driverGamepad.getLeftY();
        strafe = -driverGamepad.getLeftX();
        turn =  -driverGamepad.getRightX();
    }
}

