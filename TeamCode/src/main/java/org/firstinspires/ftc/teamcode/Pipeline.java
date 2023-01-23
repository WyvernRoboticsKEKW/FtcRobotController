package org.firstinspires.ftc.teamcode;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
import org.opencv.imgproc.Moments;
import org.openftc.easyopencv.OpenCvPipeline;

import java.util.ArrayList;
import java.util.List;


public class Pipeline extends OpenCvPipeline {
    public Scalar CyanUpper  = new Scalar(255, 175, 80);
    public Scalar CyanLower = new Scalar(40, 130, 0);
    public Scalar MagentaUpper  = new Scalar(255, 255, 255);
    public Scalar MagentaLower = new Scalar(40, 127, 150);
    public Scalar greenUpper  = new Scalar(255, 115, 125);
    public Scalar greenLower = new Scalar(40, 30, 50);

    private double[] colorCenter;

    protected int cyanPixels;
    protected int magentaPixels;
    protected int greenPixels;

    public static int CAMERA_WIDTH = 640;
    public static int CAMERA_HEIGHT = 360;

    public double[] getColorCenter() {
        return colorCenter;
    }

    @Override
    public Mat processFrame(Mat input) {
        // Process Image
        Mat yCrCb = new Mat();
        Imgproc.cvtColor(input, yCrCb, Imgproc.COLOR_RGB2YCrCb);

        colorCenter = yCrCb.get(CAMERA_WIDTH/2, CAMERA_HEIGHT/2);

        Mat cyan = new Mat();
        Mat magenta = new Mat();
        Mat green = new Mat();
        Core.inRange(yCrCb, CyanLower, CyanUpper, cyan);
        Core.inRange(yCrCb, MagentaLower, MagentaUpper, magenta);
        Core.inRange(yCrCb, greenUpper, greenLower, green);

        magentaPixels = Core.countNonZero(magenta);
        cyanPixels = Core.countNonZero(cyan);
        greenPixels = Core.countNonZero(green);

        return cyan;
    }


    public double returnCyan() {return cyanPixels;}

    public double returnMagenta() {return magentaPixels;}

    public double returnGreen() {return greenPixels;}
}


