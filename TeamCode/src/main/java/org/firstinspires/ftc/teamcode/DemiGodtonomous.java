package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;

import java.util.List;

@Autonomous(name="Demi-Godtonomous")
public class DemiGodtonomous extends LinearOpMode {
    Argorok argorok = new Argorok();
    Control control = new Control(argorok);

    @Override
    public void runOpMode() throws InterruptedException {
        argorok.init(hardwareMap);
        control.tfodInit();


        waitForStart();

//        sleep(500);
//        String orientation = "Orientation A";
//        List<Recognition> updatedRecognitions = argorok.tfod.getUpdatedRecognitions();
//        {
//            boolean flag = false;
//            while (!flag) {
//                try {
//                    updatedRecognitions.size();
//                    flag = true;
//                } catch (NullPointerException e) {
//                    updatedRecognitions = argorok.tfod.getUpdatedRecognitions();
//                    flag = false;
//                }
//            }
//        }
//        float avgPos = 0.0f;
//        int count = 0;
//        for (Recognition recognition : updatedRecognitions) {
//            avgPos = recognition.getLeft() + recognition.getRight();
//            count += 2;
//        }
//        avgPos /= count;
//        telemetry.addLine(updatedRecognitions.toString());
//        telemetry.addLine(Double.toString(avgPos));
//
//        if (updatedRecognitions.size() == 2) {
//            Recognition left = updatedRecognitions.get(0).getLeft() < updatedRecognitions.get(1).getLeft() ?
//                    updatedRecognitions.get(0) : updatedRecognitions.get(1);
//            Recognition right = updatedRecognitions.get(0).getLeft() > updatedRecognitions.get(1).getLeft() ?
//                    updatedRecognitions.get(0) : updatedRecognitions.get(1);
//            if (left.getLabel() == "Skystone") {
//                telemetry.addLine("Orientation B");
//                orientation = "Orientation B";
//            } else if (right.getLabel() == "Skystone") {
//                telemetry.addLine("Orientation C");
//                orientation = "Orientation C";
//            } else {
//                telemetry.addLine("Orientation A");
//                orientation = "Orientation A";
//            }
//        }
//        telemetry.addLine(orientation);
//        telemetry.update();
//
//        orientation = "Orientation A"; //remove this eventually
//
//        switch(orientation){
//            case "Orientation A":
//                control.voltageRunMecanum(-0.23,0.52,-0.01,16.15);
//                control.autoCloseClamp();
//                sleep(600);
////                control.voltageRunMecanum(0,-0.5,0,5.33);
////                control.voltageRunMecanum(1,0,0.02,20);
////                control.turn(Math.PI, 1);
////                control.voltageRunMecanum(0,0.5,0,5.33);
////                control.runFlüp(false);
//                break;
//            case "Orientation B":
//                control.autoRunMecanum(0,0,0,0); //placeholder
//                control.autoCloseClamp();
//                sleep(600);
//                break;
//            case "Orientation C":
//                control.autoRunMecanum(0,0,0,0); //placeholder
//                control.autoCloseClamp();
//                sleep(600);
//                break;
//            default:
//        }
        /*while(opModeIsActive()) {
            //TODO grab stones?
//            if(updatedRecognitions != null){
//                for(Recognition rec:updatedRecognitions){
//                    if(rec.getLabel()=="Skystone"){
//                        double ang = rec.estimateAngleToObject(AngleUnit.RADIANS);
//                       control.turn(ang+argorok.getHeading(), 1);
//                       control.moveToStone();
//                       control.autoCloseClamp();
//                       //TODO bring to foundation
//                       //TODO reset
//                   }
//                }
            if (getRuntime() >= 20) {
                //TODO foundation/park
            }
        }*/


        // Move Foundation with Flüp
//        waitForStart();
        /*while (opModeIsActive()) {
            telemetry.addData("Acceleration: ", control.getAcceleration());
            telemetry.update();
            wait(50);
        }*/
        /*control.autoOpenClamp();
        control.autoRunMecanum(0,-1,0,100);
        control.turn(Math.PI,1);
        //control.runMecanum(0,1,0,"field");
        sleep(100);
        while(!control.accSpike(0.00001)&&opModeIsActive()){
            telemetry.addData("Acceleration: ",control.getAcceleration());
            telemetry.update();
        }
        control.setAllZero();
        control.runFlüp(true);*/
    }
}