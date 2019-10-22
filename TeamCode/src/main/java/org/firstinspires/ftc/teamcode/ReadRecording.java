package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Autonomous(name = "Read Recoding")
public class ReadRecording extends LinearOpMode {

    private Robot robot = new Robot();

    private final double theta = Math.PI / 4;
    private final double trans_factor = 1.0;
    private final double turn_factor = 1.0;

    private List<Float> x    = new ArrayList<>(); // list of x inputs
    private List<Float> y    = new ArrayList<>(); // list of y inputs
    private List<Float> turn = new ArrayList<>(); // list of turn inputs

    @Override
    public void runOpMode() throws InterruptedException {
        robot.init(hardwareMap);

        // TODO Detect for Red or Blue Corner
        boolean isRedAndNotBlue = true;

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
        createListPart("/storage/emulated/0/test.dat");

        // This Run the Autonomous Program
        while(opModeIsActive()){
            runOperations(x.get(i), y.get(i), turn.get(i));
            i++;
            sleep(30);
        }
    }
    private void runOperations(double x, double y, double turn){
        // Scaling x and y
        x *= Math.cos(theta);
        y *= Math.sin(theta);
        // Axis Rotation
        double x_output = trans_factor * ((x * Math.cos(theta)) + (y * Math.sin(theta)));
        double y_output = trans_factor * ((x * (-Math.sin(theta))) + (y * Math.cos(theta)));
        // Get Turn Input
        turn *= turn_factor;
        // Apply Outputs
        robot.frontLeft.setPower(x_output + turn);
        robot.backLeft.setPower(y_output + turn);
        robot.frontRight.setPower(y_output - turn);
        robot.backRight.setPower(x_output - turn);
    }
    private int detectSkyStones(){
        int variation = (int)Math.floor(Math.random()*3);
        // TODO Detect Which SkyStone Variation It Is
        return variation;
    }
    private void createListPart(String file){
        try {
            Scanner scanner = new Scanner(new File(file));
            while (scanner.hasNext()) {
                x.add(scanner.nextFloat());
                y.add(scanner.nextFloat());
                turn.add(scanner.nextFloat());
            }
            scanner.close();
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }
    }
}
