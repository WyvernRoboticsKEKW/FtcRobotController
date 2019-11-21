package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="Write")
public class Write extends Drive {

    Argorok argorok = new Argorok();

    @Override
    public void runOpMode() {
        argorok.init(hardwareMap);

        Control control = new Control(argorok);
        MacroManager macroManager = new MacroManager(this, control);

        waitForStart();
        while(!gamepad1.back && opModeIsActive()){
            control.runMecanum(gamepad1.left_stick_x,-gamepad1.left_stick_y,
                               gamepad1.right_trigger-gamepad1.left_trigger,
                               "field");
            control.liftPower(gamepad1.a ? (gamepad1.b ? 0 : -0.5) : (gamepad1.b ? 0.9 : 0));
            clamp = (gamepad1.x && !prevx) != clamp;
            prevx = gamepad1.x;
            control.runClamp(clamp);
        }
        while(gamepad1.back && opModeIsActive()){
            sleep(27);
        }
        macroManager.add(new Macro(control,"testWrite"));
        macroManager.recordMacro("testWrite");
        macroManager.saveMacro("testWrite", "testWrite.dat");
        boolean runningMacro = false;
        while(opModeIsActive()) {
            if (gamepad1.start && !runningMacro) {
                runningMacro = true;
                macroManager.find("testWrite").reset();
            } else if(runningMacro && macroManager.find("testWrite").isFinished()) {
                runningMacro = false;
            }
            if (runningMacro && !macroManager.find("testWrite").isFinished()) {
                macroManager.find("testWrite").execute();
            }
            control.runMecanum(gamepad1.left_stick_x,-gamepad1.left_stick_y,
                    gamepad1.right_trigger-gamepad1.left_trigger,
                    "field");
            control.liftPower(gamepad1.a ? (gamepad1.b ? 0 : -0.5) : (gamepad1.b ? 0.9 : 0));
            clamp = (gamepad1.x && !prevx) != clamp;
            prevx = gamepad1.x;
            control.runClamp(clamp);

        }
    }
}