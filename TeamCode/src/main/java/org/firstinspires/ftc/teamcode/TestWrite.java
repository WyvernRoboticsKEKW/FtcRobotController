package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="Test Write")
public class TestWrite extends LinearOpMode {

    Argorok argorok = new Argorok();

    @Override
    public void runOpMode() {
        argorok.init(hardwareMap);

        Control control = new Control(argorok);
        MacroManager macroManager = new MacroManager(this, control);

        boolean claw = false;

        waitForStart();
        while(!gamepad1.back && opModeIsActive()){
            control.runMecanum(gamepad1.left_stick_x,-gamepad1.left_stick_y,
                               gamepad1.right_trigger-gamepad1.left_trigger,
                               "field");
            control.liftPower(gamepad1.a ? (gamepad1.b ? 0 : 0.25) : (gamepad1.b ? -0.25 : 0));
            claw = !(gamepad1.left_bumper && gamepad1.right_bumper) ?
                    gamepad1.right_bumper : claw;
            control.runClamp(claw);
        }
        while(gamepad1.back && opModeIsActive()){
            sleep(33);
        }
        macroManager.add(new Macro(control,"testWrite"));
        macroManager.recordMacro("testWrite");
        macroManager.saveMacro("testWrite", "testWrite.dat");
    }
}