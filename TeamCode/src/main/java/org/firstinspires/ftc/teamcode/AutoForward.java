package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.CommandBase;

public class AutoForward extends CommandBase {
    double power;
    StormFly stormFly;
    MecanumDriveSubsystem sub;
    public AutoForward(double power, StormFly stormFly, MecanumDriveSubsystem sub){
        this.power = power;
        this.stormFly = stormFly;
        this.sub = sub;
    }
    public void execute(){
        sub.forward(1);

    }
    public boolean isFinished(){
        return true;
    }
    public void end(boolean Interupted) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        stormFly.mecanumDrive.stop();
    }
}
