package frc.robot;

import edu.wpi.first.wpilibj.DigitalInput;

class BeamBreakSensor{
    private DigitalInput pin;

    BeamBreakSensor(int pin) {
        this.pin = new DigitalInput(pin);
    }

    boolean isBroken(){
        return !pin.get();
    }

}