package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Autonomous(name = "Read Recording")
public class ReadRecording extends LinearOpMode {

    private Argorok argorok = new Argorok();

    private final double theta = -Math.PI / 4;
    private final double trans_factor = 1;
    private final double turn_factor = 1;

    private List<Float> x    = new ArrayList<>(); // list of x inputs
    private List<Float> y    = new ArrayList<>(); // list of y inputs
    private List<Float> turn = new ArrayList<>(); // list of turn inputs

    @Override
    public void runOpMode() throws InterruptedException {
        argorok.init(hardwareMap);
        waitForStart();


        // This Next Section Detects the Variation of the Game and Creates the Autonomous Program
        // Commented For Debugging Purposes
//        if (isRedAndNotBlue) {
//            createListPart("/storage/emulated/0/AutonomousStartRed.dat");
//        } else if (!isRedAndNotBlue) {
//            createListPart("/storage/emulated/0/AutonomousStartBlue.dat");
//        }
        int i = 0;
//        while(opModeIsActive()){
//            runOperations(x.get(i), y.get(i), turn.get(i));
//            i++;
//            sleep(30);
//        }
//        if (isRedAndNotBlue) {
//            int variation = detectSkyStones();
//            if (variation == 0) {
//                createListPart("/storage/emulated/0/AutonomousRed1.dat");
//            } else if (variation == 1) {
//                createListPart("/storage/emulated/0/AutonomousRed2.dat");
//            } else if (variation == 2) {
//                createListPart("/storage/emulated/0/AutonomousRed3.dat");
//            }
//        } else if (!isRedAndNotBlue) {
//            int variation = detectSkyStones();
//            if (variation == 0) {
//                createListPart("/storage/emulated/0/AutonomousBlue1.dat");
//            } else if (variation == 1) {
//                createListPart("/storage/emulated/0/AutonomousBlue2.dat");
//            } else if (variation == 2) {
//                createListPart("/storage/emulated/0/AutonomousBlue3.dat");
//            }
//        }
        // For Debugging Purposes
        try{createListPart("/storage/emulated/0/test.dat");}catch(IOException e){e.printStackTrace();}

        // This Run the Autonomous Program
        while(opModeIsActive() && i < x.size()){
            runOperations(x.get(i), y.get(i), turn.get(i));
            i++;
            sleep(30);
        }
    }
    private void runOperations(double x, double y, double turn){
        // Axis Rotation
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
    private int detectSkyStones(){
        int variation = (int)Math.floor(Math.random()*3);
        // TODO Detect Which SkyStone Variation It Is
        return variation;
    }
    private void createListPart(String file) throws IOException {
        DataInputStream dis = new DataInputStream(new FileInputStream(file));
        while (dis.available()>0) {
            x.add(dis.readFloat());
            y.add(dis.readFloat());
            turn.add(dis.readFloat());
        }
        dis.close();
    }
}
