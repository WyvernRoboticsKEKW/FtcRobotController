package org.firstinspires.ftc.teamcode;


import com.arcrobotics.ftclib.command.CommandOpMode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class Drive extends CommandOpMode {
    StormFly stormFly;
    MecanumDriveSubsystem m_drive;


    @Override
    public void initialize() {
        super.init();
        stormFly = StormFly.getInstance(this);

        schedule(new MecanumDriveClass(stormFly.mecanum, m_drive));
        m_drive = new MecanumDriveSubsystem(stormFly.mecanum, stormFly.gamepadDrivetrain);
    }

    /*public class MyOpMode extends
            LinearOpMode {

        @Override
        public void runOpMode() throws InterruptedException {

        }
    }
       */


}