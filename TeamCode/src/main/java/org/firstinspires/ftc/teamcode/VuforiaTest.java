package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
@Autonomous(name="Vuforia Red")
public class VuforiaTest extends LinearOpMode {
    Argorok argorok = new Argorok();
    @Override
    public void runOpMode() throws InterruptedException {

        argorok.init(hardwareMap);
        Control control = new Control(argorok);
        control.vuforiaInit();

        Macro start = new Macro();
        start.read("Start.dat");
        Macro second = new Macro();
        second.read("Second.dat");
        Macro a = new Macro();
        a.read("A.dat");
        Macro b = new Macro();
        b.read("B.dat");
        Macro c = new Macro();
        c.read("C.dat");
        int[] delay = {20};
        waitForStart();
        start.executeLoop(argorok,delay);
        sleep(100);
        telemetry.addLine("scanning");
        telemetry.update();
        //if (control.isStoneVisible()) {
            int[] delayc = {31};
            telemetry.addLine("C");
            telemetry.update();
            c.executeLoop(argorok, delayc);
        /*} else {
            telemetry.addLine("second scan");
            telemetry.update();
            second.executeLoop(argorok, delay);
            if (control.isStoneVisible()) {
                int[] delayb = {32};
                telemetry.addLine("B");
                telemetry.update();
                b.executeLoop(argorok, delayb);
            } else {
                telemetry.addLine("A");
                telemetry.update();
                a.executeLoop(argorok, delay);
            }
        }*/
    }
}