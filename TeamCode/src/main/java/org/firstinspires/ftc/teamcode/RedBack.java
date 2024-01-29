package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous
public class RedBack extends CommandOpMode {
    StormFly stormFly;
    MecanumDriveSubsystem MecanumDrivesub;

    @Override
    public void initialize() {
        stormFly = StormFly.getInstance(this);

        MecanumDrivesub = new MecanumDriveSubsystem(stormFly);

        schedule(new AutoStrafeL(1, stormFly, MecanumDrivesub));
        schedule(new AutoForwardShort(1, stormFly, MecanumDrivesub));
    }
}
