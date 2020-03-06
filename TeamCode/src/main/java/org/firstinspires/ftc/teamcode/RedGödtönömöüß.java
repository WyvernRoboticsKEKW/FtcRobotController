package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;


@Autonomous(name="Red Gödtönömöüß")
public class RedGödtönömöüß extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        Argorok argorok = new Argorok();
        Control control = new Control(argorok);
        control.init(hardwareMap);
        control.vuforiaInit();
        telemetry.addLine(argorok.vuforia.getCameraName().toString());
        telemetry.update();
        waitForStart();

        control.voltageRunMecanum(0,1,0,490);
        ElapsedTime et = new ElapsedTime();

        control.runMecanum(-.16,0,0,"field");
        while(!control.isStoneVisible() && opModeIsActive()){
            telemetry.addLine(Boolean.toString(control.isStoneVisible()));
            telemetry.update();
        }
        control.voltageRunMecanum(1,0,0,250);
        final double scanTime = et.seconds();
        control.setAllZero();

        control.voltageRunMecanum(0,1,0,450);
        control.autoCloseClamp();
        sleep(500);
        control.voltageRunMecanum(0,-1,0,300);

        control.voltageRunMecanum(1,0,0,(int)(100*scanTime+900));
        control.autoOpenClamp();
        sleep(300);
        control.voltageRunMecanum(-1,0,0,600);
        /*control.voltageRunMecanum(0.2,0,0,10);
        control.autoLift(.5,300);
        control.autoOpenClamp();
        control.voltageRunMecanum(0,-.5,0,100);
        control.autoLift(-.5,200);
        control.turn(Math.PI,1);
        control.voltageRunMecanum(0,.5,0,100);
        control.runFlüp(true);
        control.turn(3*Math.PI/2,1);
        control.voltageRunMecanum(.3,0,0,100);
        control.runFlüp(false);
        control.turn(0,1);

        control.voltageRunMecanum(-1,0,0,(int)(100*scanTime));
        et.reset();
        while(!control.isStoneVisible()){
            control.runMecanum(-.3,0,0,"field");
        }
        control.setAllZero();
        final double secScanTime = et.seconds();
        control.voltageRunMecanum(0,1,0,100);
        control.autoCloseClamp();
        control.voltageRunMecanum(0,-1,0,100);
        control.voltageRunMecanum(1,0,0,(int)(100*(scanTime+secScanTime)));
        control.turn(Math.PI/2,1);
        control.autoLift(.5,100);
        control.voltageRunMecanum(.3,0,0,100);
        control.autoOpenClamp();
        control.voltageRunMecanum(-.3,0,0,100);
        control.turn(0,1);
        control.autoLift(-.5,150);*/
//        control.voltageRunMecanum(-1,0,0,1000);
        control.setAllZero();
    }
}
