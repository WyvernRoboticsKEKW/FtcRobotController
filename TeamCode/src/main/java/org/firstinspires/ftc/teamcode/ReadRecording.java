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

    @Override
    public void runOpMode() throws InterruptedException {
        robot.init(hardwareMap);

        List<Float> x    = new ArrayList<>(); // list of x inputs
        List<Float> y    = new ArrayList<>(); // list of y inputs
        List<Float> turn = new ArrayList<>(); // list of turn inputs

        // TODO Detect for Red or Blue Corner
        boolean isRedAndNotBlue = true;
        try {
            if (isRedAndNotBlue) {
                Scanner scannerStart = new Scanner(new File("/storage/emulator/0/AutonomousStartRed.dat"));
                while (scannerStart.hasNext()) {
                    x.add(scannerStart.nextFloat());
                    y.add(scannerStart.nextFloat());
                    turn.add(scannerStart.nextFloat());
                }
                scannerStart.close();
                int variation = detectSkyStones();
                if (variation == 0) {
                    Scanner scannerRed1 = new Scanner(new File("/storage/emulator/0/AutonomousRed1.dat"));
                    while (scannerRed1.hasNext()) {
                        x.add(scannerRed1.nextFloat());
                        y.add(scannerRed1.nextFloat());
                        turn.add(scannerRed1.nextFloat());
                    }
                    scannerRed1.close();
                } else if (variation == 1) {
                    Scanner scannerRed2 = new Scanner(new File("/storage/emulator/0/AutonomousRed2.dat"));
                    while (scannerRed2.hasNext()) {
                        x.add(scannerRed2.nextFloat());
                        y.add(scannerRed2.nextFloat());
                        turn.add(scannerRed2.nextFloat());
                    }
                    scannerRed2.close();
                } else if (variation == 2) {
                    Scanner scannerRed3 = new Scanner(new File("/storage/emulator/0/AutonomousRed3.dat"));
                    while (scannerRed3.hasNext()) {
                        x.add(scannerRed3.nextFloat());
                        y.add(scannerRed3.nextFloat());
                        turn.add(scannerRed3.nextFloat());
                    }
                    scannerRed3.close();
                }
            } else if (!isRedAndNotBlue) {
                Scanner scannerStart = new Scanner(new File("/storage/emulator/0/AutonomousStartBlue.dat"));
                while (scannerStart.hasNext()) {
                    x.add(scannerStart.nextFloat());
                    y.add(scannerStart.nextFloat());
                    turn.add(scannerStart.nextFloat());
                }
                scannerStart.close();
                int variation = detectSkyStones();
                if (variation == 0) {
                    Scanner scannerBlue1 = new Scanner(new File("/storage/emulator/0/AutonomousBlue1.dat"));
                    while (scannerBlue1.hasNext()) {
                        x.add(scannerBlue1.nextFloat());
                        y.add(scannerBlue1.nextFloat());
                        turn.add(scannerBlue1.nextFloat());
                    }
                    scannerBlue1.close();
                } else if (variation == 1) {
                    Scanner scannerBlue2 = new Scanner(new File("/storage/emulator/0/AutonomousBlue2.dat"));
                    while (scannerBlue2.hasNext()) {
                        x.add(scannerBlue2.nextFloat());
                        y.add(scannerBlue2.nextFloat());
                        turn.add(scannerBlue2.nextFloat());
                    }
                    scannerBlue2.close();
                } else if (variation == 2) {
                    Scanner scannerBlue3 = new Scanner(new File("/storage/emulator/0/AutonomousBlue3.dat"));
                    while (scannerBlue3.hasNext()) {
                        x.add(scannerBlue3.nextFloat());
                        y.add(scannerBlue3.nextFloat());
                        turn.add(scannerBlue3.nextFloat());
                    }
                    scannerBlue3.close();
                }
            }
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }
        int i = 0;
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
        int variation = (int)(Math.random()*3);
        // TODO Detect Which SkyStone Variation It Is
        return variation;
    }
}
