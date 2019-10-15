package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.util.ElapsedTime;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

@Autonomous(name = "MecanumDrive_Read")
public class MecanumDrive_Read extends LinearOpMode {
    private Robot robot = new Robot();

    private String currentLine = "";
    private Integer[] spaceLocations = new Integer[4];
    private Integer arrayIncrement = 0;

    private String frontLeftPower = "";
    private String backLeftPower = "";
    private String frontRightPower = "";
    private String backRightPower = "";

    private String stringElapsedTime = "";
    private ElapsedTime time = new ElapsedTime();

    @Override
    public void runOpMode() {
        robot.init(hardwareMap);
        waitForStart();
        while (opModeIsActive()){
            try{
                Scanner scanner = new Scanner(new File("OutputRecord_0.dat"));
                while (scanner.hasNextLine()) {
                    currentLine = scanner.nextLine();

                    for (int i=0; i < currentLine.length(); i++) {
                        if (Character.toString(currentLine.charAt(i)).compareTo(" ") == 0) {
                            spaceLocations[arrayIncrement] = i;
                            arrayIncrement++;
                        }
                    }
                    arrayIncrement = 0;

                    for (int i=0; i < spaceLocations[0]; i++) {
                        frontLeftPower = frontLeftPower + currentLine.charAt(i);
                    }
                    robot.frontLeft.setPower(Double.parseDouble(frontLeftPower));

                    for (int i=spaceLocations[0]; i < spaceLocations[1]; i++) {
                        backLeftPower = backLeftPower + currentLine.charAt(i);
                    }
                    robot.backLeft.setPower(Double.parseDouble(backLeftPower));

                    for (int i=spaceLocations[1]; i < spaceLocations[2]; i++) {
                        frontRightPower = frontRightPower + currentLine.charAt(i);
                    }
                    robot.frontRight.setPower(Double.parseDouble(frontRightPower));

                    for (int i=spaceLocations[2]; i < spaceLocations[3]; i++) {
                        backRightPower = backRightPower + currentLine.charAt(i);
                    }
                    robot.backRight.setPower(Double.parseDouble(backRightPower));

                    for (int i=spaceLocations[3]; i < currentLine.length(); i++) {
                        stringElapsedTime = stringElapsedTime + currentLine.charAt(i);
                    }

                    while (Double.parseDouble(stringElapsedTime) < time.milliseconds()) {}
                }
            } catch (FileNotFoundException e){
                e.printStackTrace();
            }
        }
    }
}
