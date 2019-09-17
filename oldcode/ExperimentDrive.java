package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="exp_drive",group="jv")
public class ExperimentDrive extends OpMode {
    HornTailConfig rob = new HornTailConfig();
    int mode = 0;
    int STICKY_DRIVE = 0;
    int TANK_DRIVE = 1;
    int SLOW_STICKY = 2;
    int FAST_STEER = 3;
    int FAST_DRIVE = 4;
    int SLOW_TANK = 5;

    @Override
    public void init() {
        rob.init(hardwareMap);
    }

    @Override
    public void loop() {
        switchMode();
        if(mode == STICKY_DRIVE){
            double d = -gamepad1.left_stick_y;
            double t = gamepad1.left_stick_x;
            rob.setMotors(d+t,d-t);
        }if(mode == TANK_DRIVE){
            rob.setMotors(-gamepad1.left_stick_y,-gamepad1.right_stick_y);
        }if(mode == SLOW_STICKY){
            double d = -.5*gamepad1.left_stick_y;
            double t = .5*gamepad1.left_stick_x;
            rob.setMotors(d+t,d-t);
        }if(mode == FAST_STEER){
            double d = -.65*gamepad1.left_stick_y;
            double t = gamepad1.left_stick_x;
            rob.setMotors(d+t,d-t);
        }if(mode == FAST_DRIVE){
            double d = -gamepad1.left_stick_y;
            double t = .65*gamepad1.left_stick_x;
            rob.setMotors(d+t,d-t);
        }if(mode == SLOW_TANK){
            rob.setMotors(-.5*gamepad1.left_stick_y,-.5*gamepad1.right_stick_y);
        }
    }

    private void switchMode(){
        if (gamepad1.left_stick_button)
            mode = STICKY_DRIVE;
        if (gamepad1.right_stick_button)
            mode = TANK_DRIVE;
        if (gamepad1.dpad_up)
            mode = SLOW_STICKY;
        if (gamepad1.dpad_left)
            mode = FAST_STEER;
        if (gamepad1.dpad_right)
            mode = FAST_DRIVE;
        if (gamepad1.dpad_down)
            mode = SLOW_TANK;
    }
}
