package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@TeleOp(name="Record Auto Drogon", group = "jv")
public class DrRecordAuto extends LinearOpMode {
    DrogonConfig robot = new DrogonConfig();

    String path = "/storage/emulated/0/output.txt";
    String output = "";

    @Override
    public void runOpMode() throws InterruptedException {
        robot.init(hardwareMap,telemetry);
        waitForStart();
        while(opModeIsActive()){
            double dr = .15 * -gamepad1.left_stick_y;
            double tr = .15 * gamepad1.left_stick_x;
            double lf = dr + tr;
            double rt = dr - tr;


            robot.setMotors(lf, rt);

            if(robot.left1.getPower() != 0 || robot.left2.getPower() != 0 || robot.right1.getPower() != 0 || robot.right2.getPower() != 0){
                double sl1 = robot.left1.getCurrentPosition();
                double sl2 = robot.left2.getCurrentPosition();
                double sr1 = robot.right1.getCurrentPosition();
                double sr2 = robot.right2.getCurrentPosition();

                List<Double> lp1 = new ArrayList<>();
                List<Double> lp2 = new ArrayList<>();
                List<Double> rp1 = new ArrayList<>();
                List<Double> rp2 = new ArrayList<>();

                double el1 = robot.left1.getCurrentPosition();
                double el2 = robot.left2.getCurrentPosition();
                double er1 = robot.right1.getCurrentPosition();
                double er2 = robot.right2.getCurrentPosition();


                while(opModeIsActive() && (robot.left1.getPower() != 0 || robot.left2.getPower() != 0 || robot.right1.getPower() != 0 || robot.right2.getPower() != 0)){

                    double d = .15 * gamepad1.left_stick_y;
                    double t = .15 * gamepad1.left_stick_x;
                    double lp1_ = d - t;
                    double lp2_ = d - t;
                    double rp1_ = d + t;
                    double rp2_ = d + t;


                    lp1.add(lp1_);
                    lp2.add(lp2_);
                    rp1.add(rp1_);
                    rp2.add(rp2_);


                    robot.setMotors(lp1_,rp1_);

                    el1 = robot.left1.getCurrentPosition();
                    el2 = robot.left2.getCurrentPosition();
                    er1 = robot.right1.getCurrentPosition();
                    er2 = robot.right2.getCurrentPosition();

                }

                double l1 = el1 - sl1;
                double l2 = el2 - sl2;
                double r1 = er1 - sr1;
                double r2 = er2 - sr2;

                double lp1_ = average(lp1);
                double lp2_ = average(lp2);
                double rp1_ = average(rp1);
                double rp2_ = average(rp2);
                output += "robot.encmotors(" + l1 + ", " + lp1_ + ", " + l2 + ", " + lp2_ + ", " + r1 + ", " + rp1_ + ", " + r2 + ", " + rp2_ + ");" + System.lineSeparator();
            }
            if(gamepad1.dpad_right){
                try{PrintWriter pw  = new PrintWriter(path); pw.print(output);pw.close();}catch (IOException e){}
            }

            if(gamepad1.y){
                output+= "//marker";
            }
        }
    }
    private double average(List<Double> l){
        double temp = 0;
        Double[] tempA = l.toArray(new Double[]{});
        double sum = 0;
        for(int i = 0; i < tempA.length; i++){
            sum+=tempA[i];
        }
        temp = sum / tempA.length;
        return temp;
    }
}
