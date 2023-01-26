package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "AutoBlue", preselectTeleOp = "Drive")

public class AutoBlue extends AutoGuts {
    Pipeline pipeline;
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
        double greenPixels = pipeline.returnGreen();
        double cyanPixels = pipeline.returnCyan();
        double magentaPixels = pipeline.returnMagenta();

        if(greenPixels > cyanPixels && greenPixels > magentaPixels) {
            greenPixelsBool = true;
        }
        if(magentaPixels > cyanPixels && magentaPixels > greenPixels){
            magentaPixelsBool = true;
        }
        if(cyanPixels > greenPixels && cyanPixels > magentaPixels){
            cyanPixelsBool = true;
        }
        driveEncoder(1, 0, 0.5);

        //start

        //first movement
        /*driveEncoder(60 * TICKS_PER_INCH, 0, 1);
        turn(.5, -45);
        cascadeLift(Level.HIGH);
        driveEncoder(0, 3 * TICKS_PER_INCH, 1);
        closeClaw(false);
        turn(.5, 45);
        driveEncoder(-48 * TICKS_PER_INCH, 0, 1);
    */
        if (greenPixelsBool) {
            driveEncoder(0, 12, 1);
            //park place 3
        }
        if (magentaPixelsBool) {
            //park place 2
        }
        if (cyanPixelsBool) {
            driveEncoder(0, -12, 1);
            //park place 1
        }

    }
}
