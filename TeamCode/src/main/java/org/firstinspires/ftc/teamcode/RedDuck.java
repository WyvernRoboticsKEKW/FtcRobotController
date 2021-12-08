package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.openftc.easyopencv.OpenCvCamera;

@Autonomous(name ="RedDuck")
public class RedDuck extends Drivetrain {
    @Override
    public void runOpMode() throws InterruptedException {
        initialization();
        autonomousCamera();
        waitForStart();

        while(!pipeline.isReady()) sleep(100);

        while(opModeIsActive()) {
            double[] centerColor = pipeline.getColorCenter();
            telemetry.addData("Center Color", centerColor[0] + ", " + centerColor[1] + ", " + centerColor[2]);
            telemetry.update();
            sleep(100);
        }

        azure.camera.stopRecordingPipeline();
        azure.camera.stopStreaming();
    }
}
