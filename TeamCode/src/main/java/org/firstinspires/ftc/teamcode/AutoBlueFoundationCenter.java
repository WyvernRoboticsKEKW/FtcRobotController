package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name="AutoBlueFoundationCenter")
public class AutoBlueFoundationCenter extends LinearOpMode {
    Argorok argorok = new Argorok();

    @Override
    public void runOpMode() throws InterruptedException {
        Control control = new Control(argorok);
        control.init(hardwareMap);
        waitForStart();

        control.autoRunMecanum(0,0.5,0,250);
        control.autoRunMecanum(0,0,1,753);
        control.autoLift(1,250);
        control.autoCloseClamp();
        control.autoRunMecanum(-0.25,0.55,0,2000);
        control.autoLift(-1,250);
        control.autoOpenClamp();
        control.autoRunMecanum(-0.2,-1,0,1900);
        control.autoLift(1,550);
        Thread.sleep(500);
        control.autoRunMecanum(0,0.5,0,100);
        control.autoRunMecanum(0.6,0,0,2000);
        control.autoLift(-1,400);
        control.autoRunMecanum(0,1,0,950);
        control.autoRunMecanum(0.6,0,0,1100);
    }
}
