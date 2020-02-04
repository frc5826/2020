package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;



public class ClimbSubsystem extends SubsystemBase {
    //private final WPI_VictorSPX leftMotor1 = new WPI_VictorSPX(4);
    //private final WPI_VictorSPX leftMotor2 = new WPI_VictorSPX(3);

    //private final WPI_VictorSPX rightMotor1 = new WPI_VictorSPX(2);
    //private final WPI_VictorSPX rightMotor2 = new WPI_VictorSPX(1);

    public final WPI_TalonSRX trolleyMotor = new WPI_TalonSRX(6);

  public ClimbSubsystem() {

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }


}
