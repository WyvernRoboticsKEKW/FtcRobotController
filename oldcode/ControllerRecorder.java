package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.hardware.Gamepad;

public class ControllerRecorder extends Thread {

    Gamepad gp;

    ControllerRecorder(Gamepad g){

        gp = g;

    }



}
