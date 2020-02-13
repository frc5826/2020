package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.DoubleSolenoid;


public class ClimbSubsystem extends SubsystemBase {

    public final WPI_TalonSRX trolleyMotor = new WPI_TalonSRX(6);
    public final DoubleSolenoid leftPiston = new DoubleSolenoid(11, 6, 7);
    public final DoubleSolenoid rightPiston = new DoubleSolenoid(11, 4, 5);
    public final DoubleSolenoid liftPiston = new DoubleSolenoid(11, 0, 1);

  public ClimbSubsystem() {

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }


}
