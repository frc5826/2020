package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

import static frc.robot.Constants.bConveyor;

//Holds the button and joystick (GenericHID) for a command
public class ButtonTuple {
    public final int button;
    public final GenericHID controller;

    public ButtonTuple(GenericHID controller, int button){
        this.controller = controller;
        this.button = button;
    }

    public JoystickButton createButton(){
        return new JoystickButton(controller, button);
    }

    public int getButton(){
        return button;
    }

    public GenericHID getController(){
        return controller;
    }

    public boolean isPressed(){
        return controller.getRawButton(button);
    }

}
