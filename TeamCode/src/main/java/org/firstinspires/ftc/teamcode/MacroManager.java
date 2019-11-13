package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import java.util.ArrayList;
import java.util.List;

public class MacroManager {

    Control control;
    LinearOpMode opmode;

    List<Macro> macros = new ArrayList<>();

    MacroManager(LinearOpMode opmode, Control control){

        this.opmode = opmode;

        this.control = control;

    }

    public void add(Macro macro) {
        macros.add(macro);
    }

    public void addFile(String path, String name) {
        Macro macro = new Macro(control, "/storage/emulated/0/" + path, name);
        macros.add(macro);
    }

    public void recordMacro(String name) {
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
        while(opmode.opModeIsActive() && !opmode.gamepad1.back){
            float x = opmode.gamepad1.left_stick_x;
            float y = -opmode.gamepad1.left_stick_y;
            float turn = opmode.gamepad1.right_trigger - opmode.gamepad1.left_trigger;
            control.runMecanum(x,y,turn,macro.getMode());
            macro.record(x,y,turn);
            opmode.sleep(27);
        }
    }

    public void saveMacro(String path){

    }

}
