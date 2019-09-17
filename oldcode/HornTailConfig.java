package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by Robotics on 9/11/2018.
 */

public class HornTailConfig {

    DcMotor left1 = null;
    DcMotor left2 = null;
    DcMotor right1 = null;
    DcMotor right2 = null;

    HardwareMap hwmp = null;

    ElapsedTime e = new ElapsedTime();

    public void init(HardwareMap hwmp){

        this.hwmp = hwmp;

        left1 = hwmp.dcMotor.get("l1");
        left2 = hwmp.dcMotor.get("l2");
        right1 = hwmp.dcMotor.get("r1");
        right2 = hwmp.dcMotor.get("r2");

        left1.setDirection(DcMotorSimple.Direction.REVERSE);
        left2.setDirection(DcMotorSimple.Direction.REVERSE);
        right1.setDirection(DcMotorSimple.Direction.FORWARD);
        right2.setDirection(DcMotorSimple.Direction.FORWARD);

        left1.setPower(0);
        left2.setPower(0);
        right1.setPower(0);
        right2.setPower(0);

        left2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        right2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        e.reset();

    }

    public void setMotors(double left, double right){

        left1.setPower(left);
        left2.setPower(left);
        right1.setPower(right);
        right2.setPower(right);

    }

    public void drive(double l, double r, double s){

        e.reset();

        while(e.seconds() < s)
            setMotors(l, r);
        setMotors(0, 0);

    }

}
