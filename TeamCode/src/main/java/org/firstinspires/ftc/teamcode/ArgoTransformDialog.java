package org.firstinspires.ftc.teamcode;

import android.app.Activity;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;

import static java.lang.Double.parseDouble;

@Autonomous(name="ArgoTransform")
public class ArgoTransformDialog extends LinearOpMode {

    Argorok argorok = new Argorok();

    private double globalX = 0;
    private double globalY = 0;

    @Override
    public void runOpMode() throws InterruptedException {
        argorok.init(hardwareMap);

        waitForStart();

        goTo(0.3,0,12);

    }

    private void goTo(double power, double targetX, double targetY){
        if(targetX==globalX&&targetY==globalY||power==0){
            return;
        }
        double imuTheta = argorok.imu.getAngularOrientation(AxesReference.INTRINSIC,
                AxesOrder.ZYX,
                AngleUnit.RADIANS).firstAngle;
        double speed = power*((targetX-globalX)+(targetY-globalY))/(Math.sqrt(Math.pow((targetX-globalX),2)+Math.pow(targetY-globalY,2)));
        ArgoTransformation.goTo(argorok,speed,targetX,targetY,globalX,globalY,-imuTheta);
        globalX = targetX;
        globalY = targetY;
    }
}
