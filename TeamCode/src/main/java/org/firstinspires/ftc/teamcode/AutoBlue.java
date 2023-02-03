package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "AutoBlue", preselectTeleOp = "Drive")

public class AutoBlue extends Camera {
    // diagonal fov of camera 55 degrees
    // horizontal fov of camera is 49 degrees
    // vertical fov 28 fov
    // 1280 by 720
    // 16:9 aspect ratio

    @Override
    public void start() {


        boolean greenPixelsBool = false;
        boolean magentaPixelsBool = false;
        boolean cyanPixelsBool= false;

        sleep(1000);
        double greenPixels = pipeline.returnGreen();
        double cyanPixels = pipeline.returnCyan();
        double magentaPixels = pipeline.returnMagenta();
        telemetry.addData("cyanPixels", pipeline.returnCyan());
        telemetry.addData("magentaPixels", pipeline.returnMagenta());
        telemetry.addData("greenPixels", pipeline.returnGreen());
        telemetry.update();
        if(greenPixels > cyanPixels && greenPixels > magentaPixels) {
            greenPixelsBool = true;
        }
        if(magentaPixels > cyanPixels && magentaPixels > greenPixels){
            magentaPixelsBool = true;
        }
        if(cyanPixels > greenPixels && cyanPixels > magentaPixels){
            cyanPixelsBool = true;
        }
        driveEncoder(0, -41, .5);



        if (greenPixelsBool) {
            driveEncoder(-46, 0, .5);
            //park place 3
        }
        //if (magentaPixelsBool) {
            //park place 2
       // }
        if (cyanPixelsBool) {
            driveEncoder(46, 0, .5);
            //park place 1
        }


        sleep(1000);
    }
    @Override
    public void loop(){
        telemetry.addData("cyanPixels", pipeline.returnCyan());
        telemetry.addData("magentaPixels", pipeline.returnMagenta());
        telemetry.addData("greenPixels", pipeline.returnGreen());
        telemetry.addData("topLeftTargetPos", hraezlyr.topLeft.getTargetPosition());
        telemetry.addData("topRightTargetPos", hraezlyr.topRight.getTargetPosition());
        telemetry.addData("bottomLeftTargetPos", hraezlyr.bottomLeft.getTargetPosition());
        telemetry.addData("bottomRightTargetPos", hraezlyr.bottomRight.getTargetPosition());



        telemetry.update();
    }
}
