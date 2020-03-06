package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name="AutoForward")
public class AutoForward extends LinearOpMode {
    Argorok argorok = new Argorok();

    @Override
    public void runOpMode() throws InterruptedException {
        Control control = new Control(argorok);
        control.init(hardwareMap);
        waitForStart();

        control.autoRunMecanumOfTheAncients(0,1,0,950);
    }
}
