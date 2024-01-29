package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous
public class RedFront extends CommandOpMode {
    StormFly stormFly;
    MecanumDriveSubsystem MecanumDrivesub;

    @Override
    public void initialize() {
        stormFly = StormFly.getInstance(this);

        MecanumDrivesub = new MecanumDriveSubsystem(stormFly);

        schedule(new AutoStrafeL(1, stormFly, MecanumDrivesub));
        schedule(new AutoForward(1, stormFly, MecanumDrivesub));
    }


}
