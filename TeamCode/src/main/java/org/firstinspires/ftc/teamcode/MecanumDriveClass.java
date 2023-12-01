package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.drivebase.MecanumDrive;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class MecanumDriveClass extends CommandBase {
    MecanumDriveSubsystem m_subsystemDrive;
    StormFly stormFly;
    MecanumDrive mecanum;


    public MecanumDriveClass(MecanumDrive mecanum, MecanumDriveSubsystem subsystem) {
        this.mecanum = mecanum;
        m_subsystemDrive = subsystem;
    }

    public void initialize() {
        m_subsystemDrive.mecanumDrive();
    }



}

