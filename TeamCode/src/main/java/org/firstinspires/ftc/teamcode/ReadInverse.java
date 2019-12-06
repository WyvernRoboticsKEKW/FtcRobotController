package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name="ReadInverse")
public class ReadInverse extends LinearOpMode {

    Argorok argorok = new Argorok();

    @Override
    public void runOpMode() {
        argorok.init(hardwareMap);

        Control control = new Control(argorok);
        Macro macro = new Macro(control, "autonomous.dat", "autonomous");
        macro.setMode("auto");

        waitForStart();
        macro.executeInverseLoop();
    }
}