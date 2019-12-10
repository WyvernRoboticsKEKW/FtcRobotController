package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;

public class Argorok {

    DcMotor frontLeft = null;
    DcMotor backLeft = null;
    DcMotor frontRight = null;
    DcMotor backRight = null;

    BNO055IMU imu = null;

    DcMotor lift = null;

    Servo leftClaw = null;
    Servo rightClaw = null;

    HardwareMap hwmap = null;

    int cameraMonitorViewId = 0;
    VuforiaLocalizer.Parameters vuforiaParameters = null;

    VuforiaLocalizer vuforia = null;

    public void init(HardwareMap hwm){

        hwmap = hwm;

        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit           = BNO055IMU.AngleUnit.RADIANS;
        parameters.accelUnit           = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        parameters.calibrationDataFile = "BNO055IMUCalibration.json"; // see the calibration sample opmode
        parameters.loggingEnabled      = true;
        parameters.loggingTag          = "IMU";
        parameters.accelerationIntegrationAlgorithm = new JustLoggingAccelerationIntegrator();

        imu = hwmap.get(BNO055IMU.class, "imu");
        imu.initialize(parameters);

        frontRight = hwmap.get(DcMotor.class, "frontRight");
        frontLeft = hwmap.get(DcMotor.class, "frontLeft");
        backRight = hwmap.get(DcMotor.class, "backRight");
        backLeft = hwmap.get(DcMotor.class, "backLeft");

        frontRight.setDirection(DcMotor.Direction.FORWARD);
        backRight.setDirection(DcMotor.Direction.FORWARD);

        frontLeft.setDirection(DcMotor.Direction.REVERSE);
        backLeft.setDirection(DcMotor.Direction.REVERSE);

        lift = hwmap.get(DcMotor.class, "lift");
        //lift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        lift.setDirection(DcMotor.Direction.REVERSE);
        lift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        leftClaw = hwmap.get(Servo.class, "leftClaw");
        rightClaw = hwmap.get(Servo.class, "rightClaw");

        leftClaw.setDirection(Servo.Direction.REVERSE);
        rightClaw.setDirection(Servo.Direction.FORWARD);
        leftClaw.setPosition(0.53);
        rightClaw.setPosition(0.53);

    }

    public void vuforiaInit() {
        cameraMonitorViewId = hwmap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hwmap.appContext.getPackageName());
        vuforiaParameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId);

        vuforiaParameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;
        vuforiaParameters.vuforiaLicenseKey = "AS0Qfpn/////AAABmdFAn2iov0QImOtp/XgRhFxAvPqzhw/GpmCGnRZekfTfhkIpoyTT1NbJ2he6/39JxvL24yjivPORVedIEIKNr0q31Z5OtRpyJa38Y44wJg7RMlcQ0DcfJM3IHu032uYWae4B9domr2u1LAO2bgBh3y+eCzcDda0Q7K1PUMlRgXKnH2aFALTTy4nbjPHtY0PFIRAm/TCZTqDgONIJeBnkUiVGwIUXYMKKbUQMu+8ZnxIt1eFwScmiMh3oI0M6zpoXfiX1ud7XZUFHoq0QLplViaYn7JGA4SYP0r5QKeOi+4F5GI0mgqzrrOPv6FLVUQwJkjgGshRuf/uqcm3wBbu28grz+RZH0FVL7v1mni4+S7Oz";
        vuforia = ClassFactory.getInstance().createVuforia(vuforiaParameters);

    }

}
