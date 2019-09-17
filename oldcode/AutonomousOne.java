package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="auto1",group="jv")
public class AutonomousOne extends LinearOpMode {
    HornTailConfig robot = new HornTailConfig();

    ElapsedTime et = new ElapsedTime();

    @Override
    public void runOpMode() throws InterruptedException {
        robot.init(hardwareMap);

        waitForStart();

        pineapple(.5, .5, 3.5);
        pineapple(-.5, .5, .45);
        pineapple(.5, .5, 4.5);
        pineapple(-.5, .5, .45);


    }
    private void pineapple (double left, double right, double time){
        et.reset();
        while(et.seconds() <= time){

            robot.left1.setPower(left);
            robot.left2.setPower(left);
            robot.right1.setPower(right);
            robot.right2.setPower(right);


        }
    }
}
