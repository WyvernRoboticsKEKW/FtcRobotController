package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous(name="recordedauto",group="jv")
public class RecordedAuto extends LinearOpMode {

    HornTailConfig rob = new HornTailConfig();

    @Override
    public void runOpMode() throws InterruptedException {
        //init
        rob.init(hardwareMap);
        rob.left2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rob.right2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rob.left2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rob.right2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        waitForStart();



    }
    private void rcautodrive(double l, double r, double lp, double rp){
        if(opModeIsActive()){
            double olp = rob.left2.getCurrentPosition();
            double orp = rob.right2.getCurrentPosition();
            double nlp = l + olp;
            double nrp = r + orp;
            rob.left2.setTargetPosition((int)nlp);
            rob.right2.setTargetPosition((int)nrp);
            rob.left2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            rob.right2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            rob.left2.setPower(Math.abs(lp));
            rob.right2.setPower(Math.abs(rp));
            rob.left1.setPower(lp);
            rob.right1.setPower(rp);
            rob.e.reset();
            while(opModeIsActive() && rob.left2.isBusy() && rob.right2.isBusy() && (rob.e.seconds() < 7)){

            }
            rob.left1.setPower(0);
            rob.left2.setPower(0);
            rob.right1.setPower(0);
            rob.right2.setPower(0);
            rob.left2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            rob.right2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
    }
}
