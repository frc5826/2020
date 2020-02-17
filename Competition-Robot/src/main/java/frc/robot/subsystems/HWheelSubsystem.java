package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import static frc.robot.Constants.*;

public class HWheelSubsystem extends SubsystemBase {
    public final DoubleSolenoid wheelift = new DoubleSolenoid(pCompressor, psHWheelFwd, psHWheelRec);

    public HWheelSubsystem() {

    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }
}
