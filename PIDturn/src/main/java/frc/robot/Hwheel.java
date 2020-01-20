package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.Joystick;

public class Hwheel{
private final WPI_TalonSRX Hwheel = new WPI_TalonSRX(5);
Joystick joystick;
Hwheel(Joystick joystick){
this.joystick=joystick;
}
public void run(){
    Hwheel.set(joystick.getX()*-1);
}
}