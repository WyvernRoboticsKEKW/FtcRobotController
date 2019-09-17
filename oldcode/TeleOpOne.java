package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="TeleOpOne", group = "jv")
public class TeleOpOne extends OpMode {

    IDontCare robot = new IDontCare();

    @Override
    public void init() {
        robot.init(hardwareMap);
    }

    @Override
    public void loop() {
        double left = -gamepad1.left_stick_y;
        double right = -gamepad1.right_stick_y;
        robot.topLeft.setPower(left);
        robot.bottomLeft.setPower(left);
        robot.topRight.setPower(right);
        robot.bottomRight.setPower(right);
    }
}
