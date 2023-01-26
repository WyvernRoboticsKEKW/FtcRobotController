package org.firstinspires.ftc.teamcode;

import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraRotation;

public abstract class Camera extends AutoGuts{
    Pipeline pipeline;

    @Override
    public void init(){
        super.init();

        pipeline = new Pipeline();

        hraezlyr.camera.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener() {
            @Override
            public void onOpened() {
                hraezlyr.camera.startStreaming(Pipeline.CAMERA_WIDTH, Pipeline.CAMERA_HEIGHT, OpenCvCameraRotation.SIDEWAYS_RIGHT);
            }

            @Override
            public void onError(int errorCode) {
                telemetry.addData("error", errorCode);
                telemetry.update();
            }

        });
        hraezlyr.camera.setPipeline(pipeline);
    }
}