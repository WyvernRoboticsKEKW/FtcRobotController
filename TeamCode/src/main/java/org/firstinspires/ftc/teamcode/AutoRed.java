package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous(name = "AutoRed", preselectTeleOp = "Drive")

public class AutoRed extends Camera {
    // diagonal fov of camera 55 degrees
    // horizontal fov of camera is 49 degrees
    // vertical fov 28 fov
    // 1280 by 720
    // 16:9 aspect ratio

    @Override
    public void start() {

        double greenPixels = pipeline.returnGreen();
        double cyanPixels = pipeline.returnCyan();
        double magentaPixels = pipeline.returnMagenta();
        boolean greenPixelsBool = false;
        boolean magentaPixelsBool = false;
        boolean cyanPixelsBool= false;

        //TODO: figure out tick per inch ratio for movement

        //TODO: START SIDEWAYS
        //start


        /*
        if(greenPixels > cyanPixels && greenPixels > magentaPixels) {
            greenPixelsBool = true;
        }
        if(magentaPixels > cyanPixels && magentaPixels > greenPixels){
            magentaPixelsBool = true;
        }
        if(cyanPixels > greenPixels && cyanPixels > magentaPixels){
            cyanPixelsBool = true;
        }

         */
        turn(2,90);
        //returnTo0(.2);
/*
        //first movement
        closeClaw(true);
        driveEncoder(-60 * TICKS_PER_INCH , 0, 1);
        turn(.5,45);
        cascadeLift(Level.HIGH);
        driveEncoder(0,3 * TICKS_PER_INCH,1);
        closeClaw(false);
        turn(.5, -45);
        driveEncoder(48 * TICKS_PER_INCH, 0, 1);

        if(greenPixelsBool) {
            driveEncoder(0,12,1);
            //park place 3
        }
        if(magentaPixelsBool){
            //park place 2
        }
        if(cyanPixelsBool){
            driveEncoder(0,-12,1);
            //park place 1
        }
        */
    }

    @Override
    public void loop(){
        telemetry.addData("cyanPixels", pipeline.returnCyan());
        telemetry.addData("magentaPixels", pipeline.returnMagenta());
        telemetry.addData("greenPixels", pipeline.returnGreen());
        telemetry.addData("colorsDetected", pipeline.colorsDetected);
        telemetry.update();
    }
}