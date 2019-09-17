package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;

import java.lang.reflect.Method;
import java.util.List;

import static java.lang.Thread.sleep;

public class DrogonConfig {


    DcMotor left1 = null; // Port 0 Hub 0
    DcMotor left2 = null; // Port 1 Hub 0
    DcMotor right1 = null; // Port 2 Hub 0
    DcMotor right2 = null; // Port 3 Hub 0

    DcMotor arm1 = null; // Port 0 Hub 1
    DcMotor arm2 = null; // Port 1 Hub 1

    DcMotor intake = null; //Port 2 Hub 1

    Servo intake0 = null; //Port 0 Hub 1
    Servo lock1 = null;
    Servo lock2 = null;
    Servo hook = null;

    DcMotor extention = null;

    private static final String TFOD_MODEL_ASSET = "RoverRuckus.tflite";
    private static final String LABEL_GOLD_MINERAL = "Gold Mineral";
    private static final String LABEL_SILVER_MINERAL = "Silver Mineral";
    private static final String VUFORIA_KEY = "AS0Qfpn/////AAABmdFAn2iov0QImOtp/XgRhFxAvPqzhw/GpmCGnRZekfTfhkIpoyTT1NbJ2he6/39JxvL24yjivPORVedIEIKNr0q31Z5OtRpyJa38Y44wJg7RMlcQ0DcfJM3IHu032uYWae4B9domr2u1LAO2bgBh3y+eCzcDda0Q7K1PUMlRgXKnH2aFALTTy4nbjPHtY0PFIRAm/TCZTqDgONIJeBnkUiVGwIUXYMKKbUQMu+8ZnxIt1eFwScmiMh3oI0M6zpoXfiX1ud7XZUFHoq0QLplViaYn7JGA4SYP0r5QKeOi+4F5GI0mgqzrrOPv6FLVUQwJkjgGshRuf/uqcm3wBbu28grz+RZH0FVL7v1mni4+S7Oz";
    private VuforiaLocalizer vuforia;
    public TFObjectDetector tfod;
    //big gear: 65mm
    //small gear: 42mm

    //ColorSensor cs = null;
    ElapsedTime e = new ElapsedTime();
    Telemetry telem = null;

    HardwareMap hwmap;


    public void init(HardwareMap hwmap,Telemetry tel) {

        this.hwmap = hwmap;

        telem = tel;

        left1 = hwmap.get(DcMotor.class, "l1");
        left2 = hwmap.get(DcMotor.class, "l2");
        right1 = hwmap.get(DcMotor.class, "r1");
        right2 = hwmap.get(DcMotor.class, "r2");

        arm1 = hwmap.get(DcMotor.class, "a1");
        arm2 = hwmap.get(DcMotor.class, "a2");

        intake = hwmap.get(DcMotor.class, "int");
        extention = hwmap.get(DcMotor.class, "ext");

        left1.setDirection(DcMotor.Direction.REVERSE);
        left2.setDirection(DcMotor.Direction.REVERSE);

        right1.setDirection(DcMotor.Direction.FORWARD);
        right2.setDirection(DcMotor.Direction.FORWARD);

        arm1.setDirection(DcMotor.Direction.REVERSE);
        arm2.setDirection(DcMotor.Direction.FORWARD);

        extention.setDirection(DcMotor.Direction.FORWARD);

        left1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        left2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        right1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        right2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        arm1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        arm2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        intake.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        //extention.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        // intake.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        arm2.setPower(0);
        arm1.setPower(0);
        intake.setPower(0);
        extention.setPower(0);

        intake0 = hwmap.get(Servo.class, "int0");
        lock1 = hwmap.get(Servo.class, "lock1");
        lock2 = hwmap.get(Servo.class, "lock2");
        hook = hwmap.get(Servo.class, "hook");


        intake0.setPosition(.87);

        //cs = hwmap.get(ColorSensor.class, "cs");



    }
    public void teleopinit(){
        lock1.setPosition(.47);
        lock2.setPosition(0);
    }
    public void autoinit(){
        lock1.setPosition(0);
        lock2.setPosition(1);
        hook.setPosition(1);


    }
    public void setMotors(double left, double right){

        left1.setPower(left);
        left2.setPower(left);
        right1.setPower(right);
        right2.setPower(right);

    }
    public void setAllMotors(double l1, double l2, double r1, double r2, double a1,double a2, double inta){

        left1.setPower(l1);
        left2.setPower(l2);
        right1.setPower(r1);
        right2.setPower(r2);
        arm1.setPower(a1);
        arm2.setPower(a2);
        intake.setPower(inta);

    }
    public void encmotors(double l1, double lp1, double l2, double lp2, double r1, double rp1, double r2, double rp2){
        double gl1 = l1 + left1.getCurrentPosition();
        double gl2 = l2 + left2.getCurrentPosition();
        double gr1 = r1 + right1.getCurrentPosition();
        double gr2 = r2 + right2.getCurrentPosition();

        left1.setTargetPosition((int)gl1);
        left2.setTargetPosition((int)gl2);
        right1.setTargetPosition((int)gr1);
        right2.setTargetPosition((int)gr2);

        left1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        left2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        right1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        right2.setMode(DcMotor.RunMode.RUN_TO_POSITION);


        e.reset();

        left1.setPower(Math.abs(lp1));
        left2.setPower(Math.abs(lp2));
        right1.setPower(Math.abs(rp1));
        right2.setPower(Math.abs(rp2));


        while((left1.isBusy() || left2.isBusy() || right1.isBusy() || right2.isBusy()) && e.seconds() < 11){
            telem.addLine("moving");
            telem.addData("left1",left1.getCurrentPosition());
            telem.addData("left2",left2.getCurrentPosition());
            telem.addData("right1",right1.getCurrentPosition());
            telem.addData("right2",right2.getCurrentPosition());
            telem.update();
            if(inRange(left1.getCurrentPosition(),5,(int)gl1)){
                break;
            }
            if(inRange(left2.getCurrentPosition(),5,(int)gl2)){
                break;
            }
            if(inRange(right1.getCurrentPosition(),5,(int)gr1)){
                break;
            }
            if(inRange(right2.getCurrentPosition(),5,(int)gr2)){
                break;
            }
        }

        setAllMotors(0,0,0,0,0,0,0);
        left1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        left2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        right1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        right2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


    }

    public void degree (double theta, double power){
        double ratio = (double)(805 / 90);
        theta*=ratio;
        encmotors(-theta,power,-theta,power,theta,power,theta,power);
    }
    public void feet (double l, double r, double p){
        double rat = 950 / 1.41;
        l*=rat;
        r*=rat;
        encmotors(l,p,l,p,r,p,r,p);
    }
    public boolean detectGold(){
        tfod.activate();
        e.reset();
        int g = 0;
        int n = 0;
        while(e.seconds() < 1) {
            List<Recognition> updatedRecognitions = tfod.getUpdatedRecognitions();
            if (updatedRecognitions != null) {
                for (Recognition recognition : updatedRecognitions) {
                    if (recognition.getLabel().equals(LABEL_GOLD_MINERAL)) {
                        g++;
                    }else{
                        n++;
                    }
                }
            }
        }
        tfod.deactivate();
        if(g>n){
            return true;
        }else{
            return false;
        }
    }
    private void initVuforia() {
        /*
         * Configure Vuforia by creating a Parameter object, and passing it to the Vuforia engine.
         */
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();

        parameters.vuforiaLicenseKey = VUFORIA_KEY;
        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;

        //  Instantiate the Vuforia engine
        vuforia = ClassFactory.getInstance().createVuforia(parameters);

        // Loading trackables is not necessary for the Tensor Flow Object Detection engine.
    }
    private void initTfod() {
        int tfodMonitorViewId = hwmap.appContext.getResources().getIdentifier(
                "tfodMonitorViewId", "id", hwmap.appContext.getPackageName());
        TFObjectDetector.Parameters tfodParameters = new TFObjectDetector.Parameters(tfodMonitorViewId);
        tfod = ClassFactory.getInstance().createTFObjectDetector(tfodParameters, vuforia);
        tfod.loadModelFromAsset(TFOD_MODEL_ASSET, LABEL_GOLD_MINERAL, LABEL_SILVER_MINERAL);
    }
    public void activateTfod(){
        tfod.activate();
    }
    public void initOD() {
        initVuforia();

        if (ClassFactory.getInstance().canCreateTFObjectDetector()) {
            initTfod();
        }else{
            telem.addLine("inkompatt");
            telem.update();
        }
    }
    private boolean inRange(int pos,int error,int goal){
            return (goal-error<pos)&&(pos<goal+error);
    }
}
