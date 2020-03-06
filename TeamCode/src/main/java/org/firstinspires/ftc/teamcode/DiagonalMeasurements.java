package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import java.util.List;

@Autonomous(name="test")
public class DiagonalMeasurements extends LinearOpMode {
    Argorok argorok = new Argorok();
    @Override
    public void runOpMode() throws InterruptedException {
        Control control = new Control(argorok);
        control.init(hardwareMap);
        waitForStart();

//        control.voltageRunMecanum(-0.23,0.52,-0.01,29.5);
        control.voltageRunMecanum(0,1,0,1000);
    }
}
