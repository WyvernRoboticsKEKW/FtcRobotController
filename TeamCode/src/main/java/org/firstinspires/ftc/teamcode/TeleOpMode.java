package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name = "Default_OpMode", group = "OpModes")
public class TeleOpMode extends OpMode {

    private final ElapsedTime runtime = new ElapsedTime();
    ControlInterpreter control = new ControlInterpreter();
    DriveController dControl = new DriveController(hardwareMap);

    /*
     * Code to run ONCE when the driver hits INIT
     */
    @Override
    public void init() {
        telemetry.addData("Status", "Initialized");
        control.init(hardwareMap);
    }

    /*
     * Code to run REPEATEDLY after the driver hits INIT, but before they hit PLAY
     * note: we probably won't need this
     */
    @Override
    public void init_loop() {
    }

    /*
     * Code to run ONCE when the driver hits PLAY
     */
    @Override
    public void start() {
        runtime.reset();
        telemetry.addData("Status", "Starting...");
    }

    /*
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */
    @Override
    public void loop() {
        //control.update(gamepad1);
        try {
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData("ControllerData", control.update(gamepad1));
        }
        catch(Exception e) {
            dControl.setFlywheelPower(0);
            dControl.setDrivePower(0,0);
            telemetry.addData("FATAL ERROR", "FATAL RUNTIME ERROR");
        }
    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
    }
}
