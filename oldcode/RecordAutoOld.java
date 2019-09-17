package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.sun.tools.javac.util.ArrayUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;


@TeleOp(name="RecordAutoW",group="jv")
public class RecordAutoOld extends LinearOpMode {
    HornTailConfig r = new HornTailConfig();

    String path = "/storage/emulated/0/code.txt";
    String outputtxt = "";


    @Override
    public void runOpMode(){
        r.init(hardwareMap);
        waitForStart();
        while(opModeIsActive()){
            double drive = .5 * -gamepad1.left_stick_y;
            double turn = .5 * gamepad1.left_stick_x;
            double left = drive + turn;
            double right = drive - turn;

            r.setMotors(left, right);

            if(r.left1.getPower() != 0 && r.left2.getPower() != 0 && r.right1.getPower() != 0 && r.right2.getPower() != 0){

                double startl = r.left2.getCurrentPosition();
                double startr = r.right2.getCurrentPosition();
                double endl = r.left2.getCurrentPosition();
                double endr = r.right2.getCurrentPosition();
                List<Double> lp = new ArrayList<>();
                List<Double> rp = new ArrayList<>();
                double lp_ = 0;
                double rp_ = 0;
                double lp__ = 0;
                double rp__ = 0;

                while(opModeIsActive() && r.left1.getPower() != 0 && r.left2.getPower() != 0 && r.right1.getPower() != 0 && r.right2.getPower() != 0){

                    double d = .5 * -gamepad1.left_stick_y;
                    double t = .5 * gamepad1.left_stick_x;

                    lp_ = d + t;
                    rp_ = d - t;

                    lp.add(lp_);
                    rp.add(rp_);

                    r.setMotors(lp_,rp_);


                    endl = r.left2.getCurrentPosition();
                    endr = r.right2.getCurrentPosition();

                    if(gamepad1.y){

                        r.setMotors(0,0);

                    }

                }
                double l = endl - startl;
                double r = endr - startr;
                lp__ = average(lp);
                rp__ = average(rp);
                outputtxt += "rcautodrive("+Double.toString(l) + ", " + Double.toString(r)+", " + Double.toString(lp__)+", " + Double.toString(rp__)+ ");" + System.lineSeparator();


            }

            if(gamepad1.dpad_right){

                try{PrintWriter pw  = new PrintWriter(path); pw.print(outputtxt);pw.close();}catch (IOException e){}

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
