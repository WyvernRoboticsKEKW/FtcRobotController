package org.firstinspires.ftc.teamcode;


import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class Drive extends CommandOpMode {
    StormFly stormFly;
    MecanumDriveSubsystem m_drive;
    ToolSubsystem m_tool;
    RollerIntake rollerIntake;

    public void initialize() {
        stormFly = StormFly.getInstance(this);

        rollerIntake = new RollerIntake(stormFly);

        m_drive = new MecanumDriveSubsystem(stormFly);
        m_tool = new ToolSubsystem(stormFly);

        schedule(new Tool(stormFly.gamepadTool, m_tool, stormFly));
        schedule(new MecanumDriveClass(m_drive,stormFly.gamepadDrivetrain));

    }


    }


