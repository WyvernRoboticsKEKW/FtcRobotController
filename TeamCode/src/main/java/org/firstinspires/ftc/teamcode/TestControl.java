package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="Test Control")
public class TestControl extends LinearOpMode {

    Argorok argorok = new Argorok();

    @Override
    public void runOpMode() {
        argorok.init(hardwareMap);
        Control control = new Control(argorok);
        boolean clamp = false;

        waitForStart();
        while (opModeIsActive()) {
            control.runMecanum(gamepad1.left_stick_x, -gamepad1.left_stick_y, gamepad1.right_trigger - gamepad1.left_trigger, "field");
            if(gamepad1.a){
                control.liftPower(0.25);
            } else if(gamepad1.b){
                control.liftPower(-0.25);
            } else {
                control.liftPower(0);
            }
            if(gamepad1.right_bumper){
                clamp = true;
            } else if(gamepad1.left_bumper){
                clamp = false;
            }
            control.runClamp(clamp);
        }
    }
}