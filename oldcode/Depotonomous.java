package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name="Depotonomous",group="jv")
public class Depotonomous extends LinearOpMode {
    DrogonConfig robot = new DrogonConfig();
    @Override
    public void runOpMode() throws InterruptedException {
        robot.init(hardwareMap,telemetry);
        robot.autoinit();
        waitForStart();
        robot.tfod.activate();
        sleep(250);
        sleep(100);
        robot.encmotors(-255.0, -0.128, -248.0, -0.128, -245.0, -0.128, -247.0, -0.128);
        if(robot.detectGold()){
            robot.tfod.deactivate();
            robot.encmotors(-2556.0, -0.148, -2576.0, -0.148, -2606.0, -0.148, -2642.0, -0.148);
            robot.e.reset();
            while(robot.e.seconds() < 3){
                robot.intake.setPower(-.6);
            }
            robot.intake.setPower(0);
            robot.encmotors(-263.0, -0.139, -278.0, -0.139, -267.0, -0.139, -285.0, -0.139);
            robot.encmotors(-1122.0, -0.147, -1331.0, -0.147, 1086.0, 0.147, 1244.0, 0.147);
            robot.encmotors(-6556.0, -0.148, -6576.0, -0.148, -6606.0, -0.148, -6642.0, -0.148);
        }else{
            robot.encmotors(291.0, 0.124, 441.0, 0.124, -331.0, -0.124, -557.0, -0.124);
            sleep(100);
            robot.tfod.activate();
            sleep(250);
            sleep(100);
            if(robot.detectGold()){
                robot.tfod.deactivate();
                robot.encmotors(-2068.0, -0.149, -2062.0, -.149, -2010.0, -0.144, -2009.0, -0.144);
                sleep(100);
                robot.encmotors(-617.0, -0.142, -704.0, -0.142, 632.0, 0.142, 581.0, 0.142);
                sleep(100);
                robot.encmotors(-1974.0, -0.149, -1996.0, -0.149, -2008.0, -0.149, -1997.0, -0.149);
                robot.e.reset();
                while(robot.e.seconds() < 3){
                    robot.intake.setPower(-.6);
                }
                robot.intake.setPower(0);
                robot.encmotors(6974.0, -0.149, 6996.0, -0.149, 7008.0, -0.149, 6997.0, -0.149);
            }else{
                robot.encmotors(-564.0,-0.104, -598.0,-0.104, 672.0,0.104, 659.0,0.104);
                robot.encmotors(-2348.0, -0.148, -2337.0, -0.148, -2339.0, -0.148, -2348.0, -0.148);
                robot.encmotors(525.0, 0.108, 730.0, 0.108,-612.0, -0.108,-727.0, -0.108);
                robot.encmotors(-841.0, -0.147, -807.0, -0.147, -813.0, -0.147, -815.0, -0.147);
                robot.e.reset();
                while(robot.e.seconds() < 3){
                    robot.intake.setPower(-.6);
                }
                robot.intake.setPower(0);
                robot.encmotors(-1372.0, -0.146, -1580.0, -0.146, 1429.0, 0.146, 1579.0, 0.146);
                robot.encmotors(-4142.0, -0.148, -4138.0, -0.148, -4142.0, -0.148, -4169.0, -0.148);

            }
        }
    }

}
