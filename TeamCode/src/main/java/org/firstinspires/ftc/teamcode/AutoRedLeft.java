package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "AutoRedLeft", preselectTeleOp = "Drive")

public class AutoRedLeft extends Camera {
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
        boolean cyanPixelsBool = false;


        if (greenPixels > cyanPixels && greenPixels > magentaPixels) {
            greenPixelsBool = true;
        }
        if (magentaPixels > cyanPixels && magentaPixels > greenPixels) {
            magentaPixelsBool = true;
        }
        if (cyanPixels > greenPixels && cyanPixels > magentaPixels) {
            cyanPixelsBool = true;
        }
        closeClaw(true);
        driveEncoder(0,40,.5);
        driveEncoder(46,0,.5);
        turn(4,45);
        cascadeLift(Level.HIGH, .5);
        driveEncoder(2,2, .5);
        closeClaw(false);
        driveEncoder(2,2, -.5);
        cascadeLift(Level.GROUND, 0.5);
        returnTo0(.5);
        if(magentaPixelsBool){
            driveEncoder(46,0,.5);
            returnTo0(.5);
        }
        if(cyanPixelsBool){
            driveEncoder(46,0,.5);
            returnTo0(.5);
            driveEncoder(46,0,.5);
            returnTo0(.5);
        }


    }
}