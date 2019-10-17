package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.util.ElapsedTime;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/*
This program is designed to use the file output of MecanumDrive_Record. It first identifies the
spaces, then it loops between spaces and concantenates it onto a variable, then parses the string
as a double. This double is then set as the motor power in an attempt to replicate the movements
recorded in MecanumDrive_Record. It will then run a while loop until the time in the file has been
reached to try and time the movements properly. This will continue until there are no more lines
left in the file.

--Thomas
 */

@Autonomous(name = "MecanumDrive_Read")
public class MecanumDrive_Read extends LinearOpMode {
    //initialize variables
    private Robot robot = new Robot();

    private String currentLine = "";
    private Integer[] spaceLocations = new Integer[4];
    private Integer arrayIncrement = 0;

    private ElapsedTime time = new ElapsedTime();

    @Override
    public void runOpMode() {
        robot.init(hardwareMap);
        waitForStart();
        while (opModeIsActive()){
            try{
                //define the scanner that will scan the file
                Scanner scanner = new Scanner(new File("OutputRecord_0.dat"));
                while (scanner.hasNextLine()) {
                    currentLine = scanner.nextLine();

                    //if there is another line, find out at what position spaces occur
                    for (int i=1; i < currentLine.length(); i++) {
                        if (Character.toString(currentLine.charAt(i)).compareTo(" ") == 0) {
                            spaceLocations[arrayIncrement] = i;
                            arrayIncrement++;
                        }
                    }
                    //set some positions in the array
                    spaceLocations[0] = 0; spaceLocations[spaceLocations.length] = currentLine.length();
                    arrayIncrement = 0;

                    //set the motor power based off of the space locations within the file
                    robot.frontLeft.setPower(getDouble(scanner, spaceLocations, 0, 1));
                    robot.backLeft.setPower(getDouble(scanner, spaceLocations, 1, 2));
                    robot.frontRight.setPower(getDouble(scanner, spaceLocations, 2, 3));
                    robot.backRight.setPower(getDouble(scanner, spaceLocations, 3, 4));

                    while (getDouble(scanner, spaceLocations, 4, 5) < time.milliseconds()) {/*wait until the time in the file is equal to the current time*/}
                }
            } catch (FileNotFoundException e){
                e.printStackTrace();
            }
        }
    }
    private double getDouble(Scanner scanner, Integer[] spaces, int start, int end) {
        //initialize variables for the function
        String j = "";
        String currentLine = scanner.nextLine();

        for ( int i=spaces[start]; i<spaces[end]; i++) {
            j = j + currentLine.charAt(i);
            //put the characters between the space locations into a string
        }
        //parse the string as a double and return it
        return Double.parseDouble(j);
    }
}
