package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor.RunMode;
import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.MotorControlAlgorithm;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.VoltageSensor;
import com.qualcomm.robotcore.hardware.configuration.MotorControllerConfiguration;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Position;
import org.firstinspires.ftc.robotcore.external.navigation.Velocity;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;

import static org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer.CameraDirection.BACK;

public class Argorok {

    DcMotor frontLeft = null;
    DcMotor backLeft = null;
    DcMotor frontRight = null;
    DcMotor backRight = null;

    BNO055IMU imu = null;

    DcMotor lift = null;

    Servo leftClaw = null;
    Servo rightClaw = null;

    Servo leftFlüp = null;
    Servo rightFlüp = null;

    DcMotor womp = null;

    HardwareMap hwmap = null;

    VoltageSensor vs = null;

    int cameraMonitorViewId = 0;
    VuforiaLocalizer.Parameters vuforiaParameters = null;
    private static final VuforiaLocalizer.CameraDirection CAMERA_CHOICE = BACK;
    private static final boolean PHONE_IS_PORTRAIT = false  ;

    VuforiaLocalizer vuforia = null;
    TFObjectDetector tfod = null;
    public static final String TFOD_MODEL_ASSET = "Skystone.tflite";
    public static final String LABEL_FIRST_ELEMENT = "Stone";
    public static final String LABEL_SECOND_ELEMENT = "Skystone";

    public void init(HardwareMap hwm){

        hwmap = hwm;

        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit           = BNO055IMU.AngleUnit.RADIANS;
        parameters.accelUnit           = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        parameters.calibrationDataFile = "AdafruitIMUCalibration.json"; // see the calibration sample opmode
        parameters.loggingEnabled      = false;
        //parameters.mode = BNO055IMU.SensorMode.;
        //parameters.loggingTag          = "IMU";
        parameters.accelerationIntegrationAlgorithm = new JustLoggingAccelerationIntegrator();

        imu = hwmap.get(BNO055IMU.class, "imu");
        imu.initialize(parameters);

        frontRight = hwmap.get(DcMotor.class, "frontRight");
        frontLeft = hwmap.get(DcMotor.class, "frontLeft");
        backRight = hwmap.get(DcMotor.class, "backRight");
        backLeft = hwmap.get(DcMotor.class, "backLeft");

        leftFlüp = hwmap.get(Servo.class, "leftFlüp");
        rightFlüp = hwmap.get(Servo.class, "rightFlüp");

        leftFlüp.setPosition(0);
        rightFlüp.setPosition(1);

        frontRight.setDirection(DcMotor.Direction.FORWARD);
        backRight.setDirection(DcMotor.Direction.FORWARD);
        frontLeft.setDirection(DcMotor.Direction.REVERSE);
        backLeft.setDirection(DcMotor.Direction.REVERSE);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        lift = hwmap.get(DcMotor.class, "lift");
        lift.setDirection(DcMotor.Direction.REVERSE);
        lift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        try {
            Thread.sleep(17);
        } catch(Exception e){
            e.printStackTrace();
        }

        leftClaw = hwmap.get(Servo.class, "leftClaw");
        rightClaw = hwmap.get(Servo.class, "rightClaw");

        leftClaw.setDirection(Servo.Direction.REVERSE);
        rightClaw.setDirection(Servo.Direction.FORWARD);
        leftClaw.setPosition(0.53);
        rightClaw.setPosition(0.53);

        leftFlüp.setPosition(0);
        rightFlüp.setPosition(1);

        womp = hwmap.get(DcMotor.class, "womp");
        womp.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        womp.setDirection(DcMotor.Direction.FORWARD);
        womp.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        for(VoltageSensor tempvs : hwmap.voltageSensor){
            vs = tempvs;
        }

        imu.startAccelerationIntegration(new Position(), new Velocity(), 1000);
    }
    public void vuforiaInit() {
        cameraMonitorViewId = hwmap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hwmap.appContext.getPackageName());
        vuforiaParameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId);
        //vuforiaParameters.cameraName = hwmap.get(WebcamName.class, "Webcam 1");
        vuforiaParameters.vuforiaLicenseKey = "AS0Qfpn/////AAABmdFAn2iov0QImOtp/XgRhFxAvPqzhw/GpmCGnRZekfTfhkIpoyTT1NbJ2he6/39JxvL24yjivPORVedIEIKNr0q31Z5OtRpyJa38Y44wJg7RMlcQ0DcfJM3IHu032uYWae4B9domr2u1LAO2bgBh3y+eCzcDda0Q7K1PUMlRgXKnH2aFALTTy4nbjPHtY0PFIRAm/TCZTqDgONIJeBnkUiVGwIUXYMKKbUQMu+8ZnxIt1eFwScmiMh3oI0M6zpoXfiX1ud7XZUFHoq0QLplViaYn7JGA4SYP0r5QKeOi+4F5GI0mgqzrrOPv6FLVUQwJkjgGshRuf/uqcm3wBbu28grz+RZH0FVL7v1mni4+S7Oz";
        vuforia = ClassFactory.getInstance().createVuforia(vuforiaParameters);

    }
    public void TFODInit(){
        vuforiaInit();
        int tfodMonitorViewId = hwmap.appContext.getResources().getIdentifier("tfodMonitorViewId", "id", hwmap.appContext.getPackageName());
        TFObjectDetector.Parameters tfodParameters = new TFObjectDetector.Parameters(tfodMonitorViewId);
        tfodParameters.minimumConfidence = 0.8;
        tfod = ClassFactory.getInstance().createTFObjectDetector(tfodParameters, vuforia);
        tfod.loadModelFromAsset(TFOD_MODEL_ASSET, LABEL_FIRST_ELEMENT, LABEL_SECOND_ELEMENT);
    }
    public double getHeading(){
        return imu.getAngularOrientation(AxesReference.INTRINSIC,
                AxesOrder.ZYX,
                AngleUnit.RADIANS).firstAngle;
    }
    public void setMotorsMode(RunMode runMode){
        backLeft.setMode(runMode);
        backRight.setMode(runMode);
        frontLeft.setMode(runMode);
        frontRight.setMode(runMode);
    }
}
/* Califonia's famous landmark, the Golden Gate Bridge, is known just about all over the world. It is located on a peninsula between Marin County */