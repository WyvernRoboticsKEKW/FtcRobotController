package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import java.util.ArrayList;
import java.util.List;

public class MacroManager {

    Control control;
    Drive opmode;

    List<Macro> macros = new ArrayList<>();

    MacroManager(Drive opmode, Control control){

        this.opmode = opmode;

        this.control = control;

    }

    public Macro find (String name) {
        Macro macro = null;
        for(Macro m : macros){
            if(m.getName() == name){
                macro = m;
            }
        }
        if(macro == null){
            try {
                throw new Exception();
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }

        return macro;
    }

    public void add(Macro macro) {
        macros.add(macro);
    }

    public void addFile(String path, String name) {
        Macro macro = new Macro(control, "/storage/emulated/0/" + path, name);
        macros.add(macro);
    }

    public void recordMacro(String name) {
        Macro macro = find(name);

        macro.reset();

        while(opmode.opModeIsActive() && !opmode.gamepad1.dpad_down){
            float x = opmode.gamepad1.left_stick_x;
            float y = -opmode.gamepad1.left_stick_y;
            float turn = opmode.gamepad1.right_trigger - opmode.gamepad1.left_trigger;
            //float lift = opmode.gamepad1.a ? (opmode.gamepad1.b ? 0f : -0.5f) : (opmode.gamepad1.b ? 0.9f : 0f);
            float lift;
            if(opmode.liftMode){
                if (opmode.gamepad1.a) {
                    lift = -0.5f;
                } else if (opmode.gamepad1.b) {
                    lift = 0.9f;
                } else {
                    lift = 0f;
                }
            } else {
                //TODO static mode
                lift = 0f;
            }
            opmode.clamp = (opmode.gamepad1.x && !opmode.prevx) != opmode.clamp;
            opmode.prevx = opmode.gamepad1.x;
            opmode.liftMode = (opmode.gamepad1.y && !opmode.prevy) != opmode.liftMode;
            opmode.prevy = opmode.gamepad1.y;
            control.runMecanum(x,y,turn,macro.getMode());
            control.liftPower(lift);
            control.runClamp(opmode.clamp);
            macro.record(x,y,turn,lift,opmode.clamp);
            opmode.sleep(27);
        }
    }

    public void saveMacro(String name, String path){
        Macro macro = null;
        for(Macro m : macros){
            if(m.getName() == name){
                macro = m;
            }
        }
        if(macro == null){
            try {
                throw new Exception();
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
        macro.save(path);
    }

}
