package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;


@TeleOp(name="Drive",group="jv")
public class Drive extends LinearOpMode {
    DrogonConfig robot = new DrogonConfig();
    private double speed = .37;
    private boolean intakeOpen = false;
    //private double intPower = 0;
    private boolean hooked = false;
    private boolean tank = true;
    private boolean locked = false;
    private double arm_speed = .5;
    private boolean hang_mode = false;

    @Override
    public void runOpMode() throws InterruptedException {
        robot.init(hardwareMap,telemetry);
        robot.teleopinit();
        waitForStart();
        while(opModeIsActive()){
            arm();
            toggleIntake();
            toggleHook();
            toggleLock();
            drive();
            extension();
            telemetry.addData("tank:", tank);
            telemetry.addData("speed:",speed);
            //telemetry.addData("intakePower:",intPower);
            telemetry.addData("intakeOpen:",intakeOpen);
            telemetry.addData("locked:",locked);
            telemetry.addData("hooked:",hooked);
            telemetry.addData("arm_speed:",arm_speed);
            telemetry.addData("hang_mode:",hang_mode);
            telemetry.update();
        }
    }

    private void toggleIntake(){
        if(gamepad2.left_trigger > 0.5){
            intakeOpen=!intakeOpen;
            while(gamepad2.left_trigger > 0.5){
                telemetry.addLine("looping");
                telemetry.addData("g2lt:",gamepad2.left_trigger);
                telemetry.update();
            }
        }
        if(intakeOpen)
            robot.intake0.setPosition(.44);
        else
            robot.intake0.setPosition(.87);
        if(gamepad2.right_trigger > 0.5){
            robot.intake.setPower(1);
        }else if(gamepad2.right_bumper){
            robot.intake.setPower(-1);
        }else{
            robot.intake.setPower(0);
        }
    }
    private void toggleHook(){
        if(gamepad2.b) {
            hooked = !hooked;
            while (gamepad1.b) {
                sleep(26);
            }
        }
        if(hooked){
            robot.hook.setPosition(1);
        }else{
            robot.hook.setPosition(0);
        }
    }
    private void toggleLock(){
        if(gamepad2.y) {
            locked = !locked;
            while (gamepad2.y) {
                sleep(19);
            }
        }
        if(locked){
            robot.lock1.setPosition(0);
            robot.lock2.setPosition(1);
        }else{
            robot.lock1.setPosition(.47);
            robot.lock2.setPosition(0);
        }
    }
    private void drive(){
        if(gamepad1.right_trigger > 0.5){
            if(speed == .37)
                speed = .19;
            else
                speed = .37;
            while(gamepad1.right_trigger > 0.5){
                telemetry.addLine("looping");
                telemetry.addData("g1rt:",gamepad1.right_trigger);
                telemetry.update();
            }
        }
        if(gamepad1.left_trigger > 0.5){
            tank=!tank;
            while(gamepad1.left_trigger > 0.5){
                telemetry.addLine("looping");
                telemetry.addData("g1lt:",gamepad1.left_trigger);
                telemetry.update();
            }
        }
        if(tank){
            robot.setMotors(-speed*gamepad1.left_stick_y,-speed*gamepad1.right_stick_y);
        }else{
            double d = -gamepad1.left_stick_y - gamepad1.right_stick_y;
            double t = gamepad1.left_stick_x + gamepad1.right_stick_x;
            robot.setMotors(speed*(d+t), speed*(d-t));
        }
    }
    private void extension(){
        if(-gamepad2.right_stick_y < 0){
            robot.extention.setPower(-.25*gamepad2.right_stick_y);
        }else{
            robot.extention.setPower(-.5*gamepad2.right_stick_y);
        }
    }
    private void arm(){
        if(gamepad2.a)
            arm_speed = .5;
        if(gamepad2.x)
            arm_speed = 1;
        if(gamepad2.dpad_up){
            hang_mode=!hang_mode;
            while(gamepad2.dpad_up){
                sleep(19);
            }
        }
        if(hang_mode){
            robot.arm1.setPower(1);
            robot.arm2.setPower(1);
        }else if(-gamepad2.left_stick_y<0) {
            robot.arm1.setPower(.5*arm_speed * gamepad2.left_stick_y);
            robot.arm2.setPower(.5*arm_speed * gamepad2.left_stick_y);
        }else{
            robot.arm1.setPower(arm_speed * gamepad2.left_stick_y);
            robot.arm2.setPower(arm_speed * gamepad2.left_stick_y);
        }
    }
}
