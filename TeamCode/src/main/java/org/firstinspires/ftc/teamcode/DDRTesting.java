package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "DDRTesting")
public class DDRTesting extends Drivetrain {
    @Override
    public void runOpMode() throws InterruptedException {

        initialization();
        waitForStart();
        setCarousel(-1);
        sleep(7000);
        setCarousel(0);
        sleep(1000);



    }
}
