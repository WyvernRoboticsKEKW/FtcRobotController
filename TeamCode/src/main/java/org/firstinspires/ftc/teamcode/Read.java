package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name="Read")
public class Read extends LinearOpMode {

    Argorok argorok = new Argorok();

    @Override
    public void runOpMode() {
        argorok.init(hardwareMap);

        Control control = new Control(argorok);
        Macro macro = new Macro();
        macro.read("autonomous.dat");
        macro.init(argorok);
        waitForStart();
        macro.executeLoop(argorok,17);
    }
}