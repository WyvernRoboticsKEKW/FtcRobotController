package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="Drive")
public class Drive extends LinearOpMode {

    Argorok argorok = new Argorok();
    public boolean driveMode = true; // true = "field" mode    false = "robot" mode
    public boolean clamp = false;
    public boolean prevx = false;
    public boolean prevBack = false;
    public boolean prevLeft = false;
    public boolean prevRight = false;
    public boolean prevy = false;
    public boolean slow = false;
    public boolean vwomped = false;

    @Override
    public void runOpMode() {
        Control control = new Control(argorok);
        control.init(hardwareMap);
        waitForStart();
        while (opModeIsActive()) {
            if (driveMode) {
                if(!slow){
                    control.runMecanum(gamepad1.left_stick_x + gamepad2.left_stick_x, -(gamepad1.left_stick_y + gamepad2.left_stick_y), (gamepad1.right_trigger + gamepad2.right_trigger) - (gamepad1.left_trigger + gamepad2.left_trigger), "field");
                }else{
                    control.runMecanum((gamepad1.left_stick_x + gamepad2.left_stick_x)*0.4, -(gamepad1.left_stick_y + gamepad2.left_stick_y)*0.4, (gamepad1.right_trigger + gamepad2.right_trigger)*0.4 - (gamepad1.left_trigger + gamepad2.left_trigger)*0.4, "field");
                }
                telemetry.addLine("field mode");
            } else {
                if (!slow){
                    control.runMecanum(gamepad1.left_stick_x + gamepad2.left_stick_x, -(gamepad1.left_stick_y + gamepad2.left_stick_y), (gamepad1.right_trigger + gamepad2.right_trigger) - (gamepad1.left_trigger + gamepad2.left_trigger), "robot");
                }else{
                    control.runMecanum((gamepad1.left_stick_x + gamepad2.left_stick_x) * 0.4, -(gamepad1.left_stick_y + gamepad2.left_stick_y) * 0.4, (gamepad1.right_trigger + gamepad2.right_trigger) * 0.4 - (gamepad1.left_trigger + gamepad2.left_trigger) * 0.4, "robot");
                }
                telemetry.addLine("robot mode");
            }
            driveMode = ((gamepad1.back||gamepad2.back) && !prevBack) != driveMode;
            prevBack = (gamepad1.back||gamepad2.back);
            if (gamepad1.start||gamepad2.start) {
                control.resetHeading();
            }
            if (gamepad1.a||gamepad2.a) {
                control.liftPower(-0.5);
            } else if (gamepad1.b||gamepad2.b) {
                control.liftPower(1.0);
            } else {
                control.liftPower(0);
            }
            clamp = ((gamepad1.x||gamepad2.x) && !prevx) != clamp;
            prevx = (gamepad1.x||gamepad2.x);
            control.runClamp(clamp);
            slow = ((gamepad1.y||gamepad2.y) && !prevy) != slow;
            prevy = (gamepad1.y||gamepad2.y);
            if(argorok.womp.isBusy()) {
                argorok.womp.setPower(.7);
            } else {
                argorok.womp.setPower(0);
            }
            boolean left = gamepad1.left_bumper||gamepad2.left_bumper;
            boolean right = gamepad1.right_bumper||gamepad2.right_bumper;
            control.runVWOMP(left?0.7:(right?(-0.7):0));
            telemetry.update();
        }
    }
}
