package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name="AutoBlueFoundationWall")
public class AutoBlueFoundationWall extends LinearOpMode {
    Argorok argorok = new Argorok();

    @Override
    public void runOpMode() throws InterruptedException {
        Control control = new Control(argorok);
        control.init(hardwareMap);
        waitForStart();

        control.autoRunMecanum(0,0.5,0,250);
        control.autoRunMecanum(0,0,-1,765);
        control.autoLift(1,250);
        control.autoCloseClamp();
        control.autoRunMecanum(-0.25,0.55,0,2000);
        control.autoLift(-1,250);
        control.autoOpenClamp();
        control.autoRunMecanum(-0.1,-1,0,1700);
        control.autoLift(1,450);
        control.autoCloseClamp();
        control.autoRunMecanum(0,0.5,0,100);
        control.autoRunMecanum(0.6,0,0,1600);
        control.autoLift(-1,400);
        control.autoOpenClamp();
        control.autoRunMecanum(0.6,0,0,1400);
    }
}
