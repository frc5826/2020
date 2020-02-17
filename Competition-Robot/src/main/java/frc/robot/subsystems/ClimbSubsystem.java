package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import static frc.robot.Constants.*;

public class ClimbSubsystem extends SubsystemBase {

    public final WPI_TalonSRX trolleyMotor = new WPI_TalonSRX(scTrolley);

    public final DoubleSolenoid leftPiston = new DoubleSolenoid(pCompressor, psLClimbFwd, psLClimbRev);
    public final DoubleSolenoid rightPiston = new DoubleSolenoid(pCompressor, psRClimbFwd, psRClimbRev);
    public final DoubleSolenoid liftPiston = new DoubleSolenoid(pCompressor, psLiftFwd, psLiftRev);

  public ClimbSubsystem() {
      trolleyMotor.setInverted(true);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }


}
