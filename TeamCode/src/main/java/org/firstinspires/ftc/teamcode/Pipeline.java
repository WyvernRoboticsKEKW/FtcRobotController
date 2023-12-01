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
import org.openftc.easyopencv.OpenCvPipeline;

import java.util.ArrayList;
import java.util.List;

public class Pipeline extends OpenCvPipeline {

     Scalar Blue = new Scalar(120,112,135 ); //still working on this

    private boolean isReady = false;
    //blue might be y=120 Cr=64 Cb=192                 Y      Cr     Cb    (do not change  Y)
    public static Scalar scalarLowerYCrCb = new Scalar(40,90,160);
    public static Scalar scalarUpperYCrCb = new Scalar(130,120,187);
    private double[] colorCenter;

    public boolean error = false;
    public Exception debug;

    private int borderLeftX   = 0;   //amount of pixels from the left side of the cam to skip
    private int borderRightX  = 0;   //amount of pixels from the right of the cam to skip
    private int borderTopY    = 0;   //amount of pixels from the top of the cam to skip
    private int borderBottomY = 0;   //amount of pixels from the bottom of the cam to skip


    @Override
    public Mat processFrame(Mat input) {

        return null;
    }
}
