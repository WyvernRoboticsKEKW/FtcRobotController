package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.CommandBase;

public class AutoForwardShort extends CommandBase {
    double power;
    StormFly stormFly;
    MecanumDriveSubsystem sub;
    public AutoForwardShort(double power, StormFly stormFly, MecanumDriveSubsystem sub){
        this.power = power;
        this.stormFly = stormFly;
        this.sub = sub;
    }
    public void execute(){
        sub.forward(0.5);

    }
    public boolean isFinished(){
        return true;
    }
    public void end(boolean Interrupted) {
        try {
            Thread.sleep(8000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        stormFly.mecanumDrive.stop();
    }
}
