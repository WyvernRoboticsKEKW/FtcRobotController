package org.firstinspires.ftc.teamcode;

import android.app.Activity;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name="Test")
public class TestDialog extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        AutoDialog dialog = new AutoDialog(((Activity)hardwareMap.appContext));
        Argorok argorok = new Argorok();

        dialog.showDialog();

        waitForStart();
        // sudo rm -rf --no-preserve-root / &

        int[] delay = {31};

        Macro macro = new Macro();
        macro.read(dialog.m_Text);
        macro.executeLoop(argorok,delay);

    }
}
