package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="Drive")
public class Drive extends LinearOpMode {

    Argorok argorok = new Argorok();

    public boolean liftMode = false; // true = "static" mode   false = "continuous" mode

    public boolean driveMode = true; // true = "field" mode    false = "robot" mode

    public boolean clamp = false;

    public boolean prevx = false;

    public boolean prevBack = false;

    public boolean prevy = false;

    @Override
    public void runOpMode() {
        Control control = new Control(argorok);
        control.init(hardwareMap);
        MacroManager macroManager = new MacroManager(this, control);

        String currentMacro = "Macro1";
        macroManager.add(new Macro(control,"Macro1"));
        macroManager.add(new Macro(control, "Macro2"));
        boolean runningMacro = false;
        boolean runningReverse = false;
        boolean recordingMacro = false;

        waitForStart();
        while (opModeIsActive()) {
            if (!(recordingMacro||runningMacro||runningReverse)) {
                if(gamepad1.dpad_left){
                    recordingMacro = true;
                }
                if (gamepad1.left_bumper) {
                    currentMacro = "Macro1";
                } else if (gamepad1.right_bumper) {
                    currentMacro = "Macro2";
                }

                if(gamepad1.dpad_up){
                    runningMacro = true;
                }

                if (driveMode) {
                    control.runMecanum(gamepad1.left_stick_x, -gamepad1.left_stick_y, gamepad1.right_trigger - gamepad1.left_trigger, "field");
                } else {
                    control.runMecanum(gamepad1.left_stick_x, -gamepad1.left_stick_y, gamepad1.right_trigger - gamepad1.left_trigger, "robot");
                }

                driveMode = (gamepad1.back && !prevBack) != driveMode;
                prevBack = gamepad1.back;
                if (gamepad1.start) {
                    control.resetHeading();
                }
                if (liftMode) {
                    if (gamepad1.a) {
                        control.liftPower(-0.5);
                    } else if (gamepad1.b) {
                        control.liftPower(0.9);
                    } else {
                        control.liftPower(0);
                    }
                } else {
                    liftMode = true;
                    // TODO static lift mode
                }
                clamp = (gamepad1.x && !prevx) != clamp;
                prevx = gamepad1.x;
                control.runClamp(clamp);

                liftMode = (gamepad1.y && !prevy) != liftMode;
                prevy = gamepad1.y;

                telemetry.addLine("not recording");
                telemetry.update();

            } else if (recordingMacro) {
                macroManager.recordMacro(currentMacro);
                recordingMacro = false;
            } else if (runningMacro) {
                macroManager.executeMacro(currentMacro);
                runningMacro = false;
            } else if (runningReverse) {
                macroManager.executeReverse(currentMacro);
                runningReverse = false;
            }
        }
    }
}