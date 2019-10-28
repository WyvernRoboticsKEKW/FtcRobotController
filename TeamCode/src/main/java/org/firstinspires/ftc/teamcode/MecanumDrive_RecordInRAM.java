package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@TeleOp(name="Mecanum Drive RAM Record")

public class MecanumDrive_RecordInRAM extends LinearOpMode {

    private Argorok argorok = new Argorok();
    private final double theta = Math.PI / 4;
    private final double trans_factor = 0.5;
    private final double turn_factor = 0.5;

    @Override
    public void runOpMode() throws InterruptedException {
        argorok.init(hardwareMap);

        boolean recording = false;


        waitForStart();

        List<Float> x    = new ArrayList<>(); // list of x inputs
        List<Float> y    = new ArrayList<>(); // list of y inputs
        List<Float> turn = new ArrayList<>(); // list of turn inputs

        while(opModeIsActive()){
            boolean prevBack = false;
            while(!recording) {
                runOperations(gamepad1.left_stick_x,
                             -gamepad1.left_stick_y,
                              gamepad1.right_trigger - gamepad1.left_trigger);

                if(gamepad1.back && !prevBack){
                    recording = true;
                    prevBack = true;
                }
                sleep(30);
            }
            while(recording){
                runOperations(gamepad1.left_stick_x,
                             -gamepad1.left_stick_y,
                              gamepad1.right_trigger - gamepad1.left_trigger);
                // Record Values in Lists
                x.add(gamepad1.left_stick_x);
                y.add(gamepad1.left_stick_y);
                turn.add(gamepad1.right_trigger - gamepad1.left_trigger);
                if(!gamepad1.back){
                    prevBack = false;
                }
                if(gamepad1.back && !prevBack){
                    recording = false;
                }
                sleep(27);
            }
        }
        Date date = new Date( );
        SimpleDateFormat dateFormat = new SimpleDateFormat("yy.MM.dd.hh.mm.ss.SSS");
        try{
            createFile("Recording." + dateFormat.format(date),x,y,turn);
        } catch(IOException e){
            e.printStackTrace();
        }
    }
    private void createFile(String fileName, List<Float> x, List<Float> y, List<Float> turn) throws IOException {
        OutputStream out = new FileOutputStream("/storage/emulated/0/"+fileName);
        DataOutputStream dataOut = new DataOutputStream(out);
        for(int i = 0; i < x.size(); i++) {
            dataOut.writeFloat(x.get(i));
            dataOut.writeFloat(y.get(i));
            dataOut.writeFloat(turn.get(i));
        }
    }
    private void runOperations(double x, double y, double turn){
        // Axis Rotation
        double x_output = trans_factor * ((x * Math.cos(theta)) + (y * Math.sin(theta)));
        double y_output = trans_factor * ((x * (-Math.sin(theta))) + (y * Math.cos(theta)));
        // Get Turn Input
        turn *= turn_factor;
        // Apply Outputs
        argorok.frontLeft.setPower(x_output + turn);
        argorok.backLeft.setPower(y_output + turn);
        argorok.frontRight.setPower(y_output - turn);
        argorok.backRight.setPower(x_output - turn);
    }
}

