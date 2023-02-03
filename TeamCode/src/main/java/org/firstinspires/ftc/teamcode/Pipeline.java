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
    public Scalar CyanUpper  = new Scalar(250, 180, 75);
    public Scalar CyanLower = new Scalar(40, 127, 0);
    public Scalar MagentaUpper  = new Scalar(250, 235, 240);
    public Scalar MagentaLower = new Scalar(40, 115, 160);
    public Scalar greenUpper  = new Scalar(250, 127, 112);
    public Scalar greenLower = new Scalar(40, 100, 60);

    private double[] colorCenter;

    protected int cyanPixels = 0;
    protected int magentaPixels = 0;
    protected int greenPixels = 0;

    public static int CAMERA_WIDTH = 640;
    public static int CAMERA_HEIGHT = 480;
    public boolean colorsDetected = false;

    public double[] getColorCenter() {
        return colorCenter;
    }

    @Override
    public Mat processFrame(Mat input) {
        // Process Image
        Mat yCrCb = new Mat();
        Imgproc.cvtColor(input, yCrCb, Imgproc.COLOR_BGR2YCrCb);

        colorCenter = yCrCb.get(CAMERA_WIDTH/2, CAMERA_HEIGHT/2);
        input.release();

        Mat cyan = new Mat();
        Mat magenta = new Mat();
        Mat green = new Mat();
        Core.inRange(yCrCb, CyanLower, CyanUpper, cyan);
        Core.inRange(yCrCb, MagentaLower, MagentaUpper, magenta);
        Core.inRange(yCrCb, greenLower, greenUpper, green);
        yCrCb.release();

        Mat cyanDenoised = denoise(cyan);
        Mat magentaDenoised = denoise(magenta);
        Mat greenDenoised = denoise(green);

        cyan.release();
        magenta.release();
        green.release();

        magentaPixels = Core.countNonZero(magentaDenoised);
        cyanPixels = Core.countNonZero(cyanDenoised);
        greenPixels = Core.countNonZero(greenDenoised);
        colorsDetected = true;
        // Release matricies that are no longer being used. OpenCV will report a memory leak if you don't

        greenDenoised.release();
        cyanDenoised.release();

        // Don't release the one being returned or else it will error


        return magentaDenoised;
    }

    public Mat denoise(Mat src) {

        Mat denoised = new Mat();

        Imgproc.morphologyEx(src, denoised,
                Imgproc.MORPH_OPEN,
                Imgproc.getStructuringElement(
                        Imgproc.MORPH_ELLIPSE,
                        new Size(3, 3)));

        src.release();

        return denoised;
    }

    public double returnCyan() {return cyanPixels;}

    public double returnMagenta() {return magentaPixels;}

    public double returnGreen() {return greenPixels;}
}


