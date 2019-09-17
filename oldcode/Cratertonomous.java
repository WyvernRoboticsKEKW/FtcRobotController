package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name="Cratertonomous",group="jv")
public class Cratertonomous extends LinearOpMode {
    DrogonConfig robot = new DrogonConfig();
    @Override
    public void runOpMode() throws InterruptedException {
        robot.init(hardwareMap,telemetry);
        robot.autoinit();
        waitForStart();
        robot.activateTfod();
        sleep(250);
        //possible movement forward

        if(robot.detectGold()){
            //move forward, then drop off marker, then ram in to the crater.
        }else{
            //turn roughly 30 deg to left
            if(robot.detectGold()){
                //move forward, then drop off marker, then ram in to the crater.
            }else{
                //turn roughly 60 deg to right
                //move forward, then drop off marker, then ram in to the crater.
            }
        }
    }

}
