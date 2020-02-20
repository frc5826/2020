package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import static frc.robot.Constants.*;

public class ClimbSubsystem extends SubsystemBase {

    public final WPI_TalonSRX trolleyMotor = new WPI_TalonSRX(scTrolley);

    private final DoubleSolenoid leftPiston = new DoubleSolenoid(pCompressor, psLClimbFwd, psLClimbRev);
    private final DoubleSolenoid rightPiston = new DoubleSolenoid(pCompressor, psRClimbFwd, psRClimbRev);
    private final DoubleSolenoid liftPiston = new DoubleSolenoid(pCompressor, psLiftFwd, psLiftRev);

  public ClimbSubsystem() {

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
        rightPiston.set(DoubleSolenoid.Value.kReverse);
        leftPiston.set(DoubleSolenoid.Value.kReverse);
    }

    public void raiseTrolley() {
        rightPiston.set(DoubleSolenoid.Value.kForward);
        leftPiston.set(DoubleSolenoid.Value.kForward);
    }
}
