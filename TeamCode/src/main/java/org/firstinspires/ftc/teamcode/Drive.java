package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="Drive")
public class Drive extends LinearOpMode {

    Argorok argorok = new Argorok();

    //public boolean liftMode = true; // true = "static" mode   false = "continuous" mode

    public boolean driveMode = true; // true = "field" mode    false = "robot" mode

    public boolean clamp = false;

    public boolean prevx = false;

    public boolean prevBack = false;

    public boolean prevy = false;
    public boolean slow = false;

    public boolean preva = false;
    public boolean prevb = false;

    public int liftHeight = 0;

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
                if(gamepad1.dpad_left||gamepad2.dpad_left){
                    recordingMacro = true;
                }
                if (gamepad1.left_bumper||gamepad2.left_bumper) {
                    currentMacro = "Macro1";
                } else if (gamepad1.right_bumper||gamepad2.right_bumper) {
                    currentMacro = "Macro2";
                }

                if(gamepad1.dpad_up||gamepad2.dpad_up){
                    runningMacro = true;
                }
                if(gamepad1.dpad_right||gamepad2.dpad_right){
                    runningReverse = true;
                }

                if (driveMode) {
                    if(!slow)
                        control.runMecanum(gamepad1.left_stick_x + gamepad2.left_stick_x, -(gamepad1.left_stick_y + gamepad2.left_stick_y), (gamepad1.right_trigger + gamepad2.right_trigger) - (gamepad1.left_trigger + gamepad2.left_trigger), "field");
                    else
                        control.runMecanum((gamepad1.left_stick_x + gamepad2.left_stick_x)*0.4, -(gamepad1.left_stick_y + gamepad2.left_stick_y)/2, (gamepad1.right_trigger + gamepad2.right_trigger)*0.4 - (gamepad1.left_trigger + gamepad2.left_trigger)*0.4, "field");
                    telemetry.addLine("field mode");
                } else {
                    if(!slow)
                        control.runMecanum(gamepad1.left_stick_x + gamepad2.left_stick_x, -(gamepad1.left_stick_y + gamepad2.left_stick_y), (gamepad1.right_trigger + gamepad2.right_trigger) - (gamepad1.left_trigger + gamepad2.left_trigger), "robot");
                    else
                        control.runMecanum((gamepad1.left_stick_x + gamepad2.left_stick_x)*0.4, -(gamepad1.left_stick_y + gamepad2.left_stick_y)*0.4, (gamepad1.right_trigger + gamepad2.right_trigger)*0.4 - (gamepad1.left_trigger + gamepad2.left_trigger)*0.4, "robot");
                    telemetry.addLine("robot mode");
                }

                driveMode = ((gamepad1.back||gamepad2.back)&& !prevBack) != driveMode;
                prevBack = (gamepad1.back||gamepad2.back);
                if (gamepad1.start||gamepad2.start) {
                    control.resetHeading();
                }
                //if (liftMode) {
                    //argorok.lift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                    if (gamepad1.a||gamepad2.a) {
                        control.liftPower(-0.5);
                    } else if (gamepad1.b||gamepad2.b) {
                        control.liftPower(0.9);
                    } else {
                        control.liftPower(0);
                    }
                //}
//                else {
//
//                    if((gamepad1.a||gamepad2.a)&&!preva) {
//                        liftHeight = (liftHeight + 4) % 5;
//                    } else if ((gamepad1.b||gamepad2.b)&&!prevb){
//                        liftHeight = (liftHeight + 1) % 5;
//                    }
//                    preva = (gamepad1.a||gamepad2.a);
//                    prevb = (gamepad1.b||gamepad2.b);
//                    argorok.lift.setTargetPosition(200*liftHeight);
//                    control.liftPower(0.9);
//
//                    telemetry.addLine("Current Position: "+argorok.lift.getCurrentPosition());
//                }
                clamp = ((gamepad1.x||gamepad2.x) && !prevx) != clamp;
                prevx = (gamepad1.x||gamepad2.x);
                control.runClamp(clamp);

                slow = ((gamepad1.y||gamepad2.y) && !prevy) != slow;

                prevy = (gamepad1.y||gamepad2.y);

                //argorok.lift.setMode(DcMotor.RunMode.RUN_TO_POSITION);

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