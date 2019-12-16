package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="Write")
public class Write extends LinearOpMode {
    Argorok argorok = new Argorok();
    public boolean driveMode = true; // true = "field" mode    false = "robot" mode
    public boolean clamp = false;
    public boolean prevx = false;
    public boolean prevBack = false;
    public boolean prevy = false;
    public boolean slow = false;
    public boolean recording = false;
    public boolean prevup = false;
    @Override
    public void runOpMode() {
        Control control = new Control(argorok);
        Macro macro = new Macro();
        control.init(hardwareMap);
        waitForStart();
        while (opModeIsActive()) {
            recording =((gamepad1.dpad_up||gamepad2.dpad_up)&&!prevup)!= recording;
            prevup = gamepad1.dpad_up||gamepad2.dpad_up;
            if(recording){
                telemetry.addLine("recording");
                macro.record(argorok);
                sleep(17);
            }
            if (driveMode) {
                if(!slow){
                    control.runMecanum(gamepad1.left_stick_x + gamepad2.left_stick_x, -(gamepad1.left_stick_y + gamepad2.left_stick_y), (gamepad1.right_trigger + gamepad2.right_trigger) - (gamepad1.left_trigger + gamepad2.left_trigger), "auto");
                }else{
                    telemetry.addLine("slow mode");
                    control.runMecanum((gamepad1.left_stick_x + gamepad2.left_stick_x)*0.4, -(gamepad1.left_stick_y + gamepad2.left_stick_y)/2, (gamepad1.right_trigger + gamepad2.right_trigger)*0.4 - (gamepad1.left_trigger + gamepad2.left_trigger)*0.4, "auto");
                }
                telemetry.addLine("field mode");
            } else {
                if (!slow){
                    control.runMecanum(gamepad1.left_stick_x + gamepad2.left_stick_x, -(gamepad1.left_stick_y + gamepad2.left_stick_y), (gamepad1.right_trigger + gamepad2.right_trigger) - (gamepad1.left_trigger + gamepad2.left_trigger), "robot");
                }else{
                    telemetry.addLine("slow mode");
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
            telemetry.update();
        }
        FileManager.delete("autonomous.dat");
        macro.write(macro,"autonomous.dat");
    }
}