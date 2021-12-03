package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.openftc.easyopencv.OpenCvCamera;

@Autonomous(name ="BlueDuck")
public class BlueDuck extends Drivetrain {
    @Override
    public void runOpMode() throws InterruptedException {
        initialization();
        waitForStart();

        setDrivePower(1,1);
        sleep(1000);
        setDrivePower(0,0);

        azure.camera.setViewportRenderingPolicy(OpenCvCamera.ViewportRenderingPolicy.OPTIMIZE_VIEW);

        azure.camera.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener() {
            @Override
            public void onOpened() {
            }

            @Override
            public void onError(int errorCode) {

            }
        });


    }
}
