package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;

public class Control {

    private Argorok argorok;

    private double theta = -Math.PI / 4;
    private double trans_factor = 1;
    private double turn_factor = 0.5;

    Control(Argorok argorok){
        this.argorok = argorok;
    }

    public void init(HardwareMap hwmap) {
        argorok.init(hwmap);
    }



    public void runMecanum(double x, double y, double turn, String mode) {

        double theta = mode == "field" ? this.theta +
                argorok.imu.getAngularOrientation(AxesReference.INTRINSIC,
                        AxesOrder.ZYX,
                        AngleUnit.RADIANS).firstAngle
                : this.theta;

        double x_output = trans_factor * ((x * Math.cos(theta)) + (y * Math.sin(theta)));
        double y_output = trans_factor * ((x * (-Math.sin(theta))) + (y * Math.cos(theta)));
        // Get Turn Input
        turn *= turn_factor;
        // Apply Outputs
        argorok.frontLeft.setPower(y_output + turn);
        argorok.backLeft.setPower(x_output + turn);
        argorok.frontRight.setPower(x_output - turn);
        argorok.backRight.setPower(y_output - turn);
    }
}
