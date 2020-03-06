package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name="Autonomous")
public class Auto extends LinearOpMode {
    Argorok argorok = new Argorok();

    @Override
    public void runOpMode() throws InterruptedException{
        Control control = new Control(argorok);
        control.init(hardwareMap);
        waitForStart();

        //RED SIDE GRAB FOUNDATION THEN PARK NEAR WALL
//        control.autoRunMecanumOfTheAncients(0,0.5,0,250);
//        control.autoRunMecanumOfTheAncients(0,0,1,753);
//        control.autoLift(1,250);
//        control.autoCloseClamp();
//        control.autoRunMecanumOfTheAncients(0.25,0.55,0,2000);
//        control.autoLift(-1,250);
//        control.autoOpenClamp();
//        control.autoRunMecanumOfTheAncients(0.1,-1,0,1700);
//        control.autoLift(1,350);
//        control.autoRunMecanumOfTheAncients(0,0.5,0,100);
//        control.autoRunMecanumOfTheAncients(-0.6,0,0,1200);
//        control.autoLift(-1,300);
//        control.autoRunMecanumOfTheAncients(-0.6,0,0,1400);

        //BLUE SIDE GRAB FOUNDATION THEN PARK NEAR WALL
//        control.autoRunMecanumOfTheAncients(0,0.5,0,250);
//        control.autoRunMecanumOfTheAncients(0,0,1,753);
//        control.autoLift(1,250);
//        control.autoCloseClamp();
//        control.autoRunMecanumOfTheAncients(-0.25,0.55,0,2000);
//        control.autoLift(-1,250);
//        control.autoOpenClamp();
//        control.autoRunMecanumOfTheAncients(-0.1,-1,0,1700);
//        control.autoLift(1,450);
//        Thread.sleep(300);
//        control.autoRunMecanumOfTheAncients(0,0.5,0,100);
//        control.autoRunMecanumOfTheAncients(0.6,0,0,1600);
//        control.autoLift(-1,400);
//        control.autoRunMecanumOfTheAncients(0.6,0,0,1400);

        //RED SIDE GRAB FOUNDATION THEN PARK NEAR CENTER
//        control.autoRunMecanumOfTheAncients(0,0.5,0,250);
//        control.autoRunMecanumOfTheAncients(0,0,1,753);
//        control.autoLift(1,250);
//        control.autoCloseClamp();
//        control.autoRunMecanumOfTheAncients(0.25,0.55,0,2000);
//        control.autoLift(-1,250);
//        control.autoOpenClamp();
//        control.autoRunMecanumOfTheAncients(0.2,-1,0,1900);
//        control.autoLift(1,550);
//        Thread.sleep(500);
//        control.autoRunMecanumOfTheAncients(0,0.5,0,100);
//        control.autoRunMecanumOfTheAncients(-0.6,0,0,2000);
//        control.autoLift(-1,400);
//        control.autoRunMecanumOfTheAncients(0,1,0,950);
//        control.autoRunMecanumOfTheAncients(-0.6,0,0,1100);

        //BLUE SIDE GRAB FOUNDATION THEN PARK NEAR CENTER
//        control.autoRunMecanumOfTheAncients(0,0.5,0,250);
//        control.autoRunMecanumOfTheAncients(0,0,1,753);
//        control.autoLift(1,250);
//        control.autoCloseClamp();
//        control.autoRunMecanumOfTheAncients(-0.25,0.55,0,2000);
//        control.autoLift(-1,250);
//        control.autoOpenClamp();
//        control.autoRunMecanumOfTheAncients(-0.2,-1,0,1900);
//        control.autoLift(1,550);
//        Thread.sleep(500);
//        control.autoRunMecanumOfTheAncients(0,0.5,0,100);
//        control.autoRunMecanumOfTheAncients(0.6,0,0,2000);
//        control.autoLift(-1,400);
//        control.autoRunMecanumOfTheAncients(0,1,0,950);
//        control.autoRunMecanumOfTheAncients(0.6,0,0,1100);
    }
}
