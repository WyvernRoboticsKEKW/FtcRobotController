package org.firstinspires.ftc.teamcode;


import com.arcrobotics.ftclib.command.CommandOpMode;

public class Drive extends CommandOpMode {
    StormFly stormFly;
    MecanumDriveSubsystem m_drive;


    @Override
    public void initialize() {
        stormFly = StormFly.getInstance(this);

        schedule(new MecanumDriveClass(stormFly.frontLeft, stormFly.frontRight, stormFly.backLeft, stormFly.backRight));
        m_drive = new MecanumDriveSubsystem(stormFly.mecanum, stormFly.gamepadDrivetrain);



    }

}








