package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.CommandBase;

public class AutoStrafeL extends CommandBase {
    double power;
    StormFly stormFly;
    MecanumDriveSubsystem sub;
    public AutoStrafeL(double power, StormFly stormFly, MecanumDriveSubsystem sub){
        this.power = power;
        this.stormFly = stormFly;
        this.sub = sub;
    }
    public void execute(){
        sub.strafe(-0.5);

    }
    public boolean isFinished(){
        return true;
    }
    public void end(boolean Interupted){
        try {
            Thread.sleep(600);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        stormFly.mecanumDrive.stop();
    }

}


