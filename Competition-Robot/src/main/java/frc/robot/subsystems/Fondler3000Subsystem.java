package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;



public class Fondler3000Subsystem extends SubsystemBase {

  public final WPI_TalonSRX shooterMotor= new WPI_TalonSRX(8);
  public final WPI_TalonSRX intakeMotor = new WPI_TalonSRX(9);
  public final WPI_TalonSRX conveyorMotor = new WPI_TalonSRX(7);

  public Fondler3000Subsystem() {

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }


}
