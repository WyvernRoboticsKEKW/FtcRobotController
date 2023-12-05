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
        stormFly = StormFly.getInstance(this);


        //rollerIntake = new RollerIntake(stormFly);
        m_drive = new MecanumDriveSubsystem(stormFly);

        schedule(new MecanumDriveClass(m_drive,stormFly.gamepadDrivetrain));
        //schedule(new Tool(stormFly.gamepadTool, rollerIntake));

    }

    /*public class MyOpMode extends
            LinearOpMode {

        @Override
        public void runOpMode() throws InterruptedException {

        }
    }
       */


}