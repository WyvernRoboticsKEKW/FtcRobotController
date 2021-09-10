package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

/*
    DRIVE LAYER

 Contains all logical game operations and utilizes the control layer for robot functions
*/

@Autonomous(name="AutoDrive2", group="Autonomous")
public class AutoDrive2 extends LinearOpMode {

    public Control control;

    @Override
    public void runOpMode() throws InterruptedException {
        telemetry.addData(">", "DO NOT START YET");
        telemetry.update();
        // Initialize Control Layer
        control = new Control();
        control.init(hardwareMap);

        // Await start event
        control.vermithrax.toggleGripState();
        control.vermithrax.setArmPosition(0);
        telemetry.addData(">", "Press Play to start mode");
        telemetry.update();
        waitForStart();

        // Main game logic
        //control.vermithrax.initArm();
        try {
            // Drive forward to prep shot
            control.driveForTime(0.75, 0.75, 900);
            Thread.sleep(1000);
            // Spin up flywheel and then loader
            control.vermithrax.setFlywheelPower(1);
            Thread.sleep(3000);
            control.vermithrax.setLoaderPower(1);
            Thread.sleep(3000);
            // Turn off loader and turn on intake for second ring
            control.vermithrax.setLoaderPower(0);
            control.vermithrax.setIntakePower(1);
            Thread.sleep(2000);
            // Turn off intake and turn on loader to fire second ring
            control.vermithrax.setIntakePower(0);
            control.vermithrax.setLoaderPower(1);
            Thread.sleep(3000);
            // Turn everything off
            control.vermithrax.setIntakePower(0);
            control.vermithrax.setLoaderPower(0);
            control.vermithrax.setFlywheelPower(0);
            control.driveForTime(0.75, 0.8, 750);
            control.vermithrax.initArm();
            Thread.sleep(1000);
            control.driveForTime(-0.2, 0.4, 500);
            // Rotate to face drop zone
            Thread.sleep(1000);
            control.vermithrax.toggleArmLift(); // Drop arm
            Thread.sleep(1000);
            control.vermithrax.toggleGripState(); // Let go of wobble
            Thread.sleep(1000);
            control.vermithrax.toggleArmLift(); // Move arm back up
            control.driveForTime(0.2, -0.4, 500); // Rotate back to line
            Thread.sleep(1000);
            control.vermithrax.setArmPosition(0); // De-init arm
            Thread.sleep(1000);
        } catch (Exception e) {
            control.vermithrax.setFlywheelPower(0);
            control.vermithrax.setDrivePower(0, 0);
            control.vermithrax.setIntakePower(0);
            control.vermithrax.setLoaderPower(0);
            telemetry.addData("Status", "FATAL RUNTIME ERROR");
            telemetry.addData("ERROR", e.getStackTrace());
        }
        while(opModeIsActive()) {
            telemetry.addData("position", control.vermithrax.motorArm.getCurrentPosition());
            telemetry.update();
        }
    }
}
