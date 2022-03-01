package frc.robot;

import edu.wpi.first.wpilibj2.command.button.Button;
import edu.wpi.first.wpilibj.GenericHID;

public class TwoButtonCombo extends Button {

    private GenericHID controller;
    private int button1Number;
    private int button2Number;

    public TwoButtonCombo(GenericHID joystick, int button1, int button2) {

        controller = joystick;
        button1Number = button1;
        button2Number = button2;

    }

    @Override
    public boolean get() {
        return controller.getRawButton(button1Number) && controller.getRawButton(button2Number);

    }

}