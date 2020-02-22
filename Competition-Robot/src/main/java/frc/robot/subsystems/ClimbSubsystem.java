package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import static frc.robot.Constants.*;

public class ClimbSubsystem extends SubsystemBase {

    public final WPI_TalonSRX trolleyMotor = new WPI_TalonSRX(scTrolley);

    private final DoubleSolenoid lowerPiston = new DoubleSolenoid(pCompressor, psClimbFwd, psLClimbRev);
    private final DoubleSolenoid raisePiston = new DoubleSolenoid(pCompressor, psClimbDead, psClimbRev);
    private final DoubleSolenoid liftPiston = new DoubleSolenoid(pCompressor, psLiftFwd, psLiftRev);

  public ClimbSubsystem() {
        lowerLift();
        lowerTrolley();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }


    public void lowerLift() {
        liftPiston.set(DoubleSolenoid.Value.kForward);
    }

    public void raiseLift() {
        liftPiston.set(DoubleSolenoid.Value.kReverse);
    }

    public void lowerTrolley() {
        raisePiston.set(DoubleSolenoid.Value.kReverse);
        lowerPiston.set(DoubleSolenoid.Value.kForward);
    }

    public void raiseTrolley() {
        raisePiston.set(DoubleSolenoid.Value.kForward);
        lowerPiston.set(DoubleSolenoid.Value.kReverse);
    }
}
