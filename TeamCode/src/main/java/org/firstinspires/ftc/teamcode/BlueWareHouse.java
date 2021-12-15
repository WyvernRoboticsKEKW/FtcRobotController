package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.openftc.easyopencv.OpenCvCamera;

@Autonomous(name ="BlueWareHouse")
public class BlueWareHouse extends Drivetrain {
    @Override
    public void runOpMode() throws InterruptedException {
        initialization();
        waitForStart();

        setDrivePower(1,1);
        sleep(1000);
        setDrivePower(0,0);

        stopRecording(); // turn off the robots camera
        }
    }
