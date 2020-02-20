package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import static frc.robot.Constants.*;

public class HWheelSubsystem extends SubsystemBase {
    private final DoubleSolenoid wheeLift = new DoubleSolenoid(pCompressor, psHWheelFwd, psHWheelRec);

    public HWheelSubsystem() {

    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }

    public void raiseHWheel() {
        wheeLift.set(DoubleSolenoid.Value.kReverse);
    }

    public void lowerHWheel() {
        wheeLift.set(DoubleSolenoid.Value.kForward);
    }
}
