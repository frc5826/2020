package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import static frc.robot.Constants.*;

public class HWheelSubsystem extends SubsystemBase {
    private final DoubleSolenoid wheeLift = new DoubleSolenoid(pCompressor, psHWheelFwd, psHWheelRec);

    public HWheelSubsystem() {
        lowerHWheel();
        SmartDashboard.putBoolean("hwheel", isHwheelDown());
    }
    public void periodic(){
        SmartDashboard.getBoolean("hwheel",isHwheelDown());
    }
    public boolean isHwheelDown(){
        if(wheeLift.get() == DoubleSolenoid.Value.kForward){
            return true;
        }else{
            return false;
        }
    }
    public void raiseHWheel() {
        wheeLift.set(DoubleSolenoid.Value.kReverse);
    }

    public void lowerHWheel() {
        wheeLift.set(DoubleSolenoid.Value.kForward);
    }
}
