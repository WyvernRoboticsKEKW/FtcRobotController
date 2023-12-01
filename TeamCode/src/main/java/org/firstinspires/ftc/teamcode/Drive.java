package org.firstinspires.ftc.teamcode;


import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class Drive extends CommandOpMode {
    StormFly stormFly;
    MecanumDriveSubsystem m_drive;
    RollerIntake rollerIntake;


    @Override
    public void initialize() {
        super.init();
        stormFly = StormFly.getInstance(this);
        m_drive = new MecanumDriveSubsystem(stormFly.mecanum, stormFly.gamepadDrivetrain);
        rollerIntake = new RollerIntake(stormFly);


        schedule(new MecanumDriveClass(stormFly.mecanum, m_drive));
        schedule(new Tool(stormFly.gamepadTool, rollerIntake));

    }

    /*public class MyOpMode extends
            LinearOpMode {

        @Override
        public void runOpMode() throws InterruptedException {

        }
    }
       */


}