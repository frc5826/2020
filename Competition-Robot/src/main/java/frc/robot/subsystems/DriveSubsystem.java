package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;


public class DriveSubsystem extends SubsystemBase {
    private final WPI_VictorSPX leftMotor1 = new WPI_VictorSPX(4);
    private final WPI_VictorSPX leftMotor2 = new WPI_VictorSPX(3);

    private final WPI_VictorSPX rightMotor1 = new WPI_VictorSPX(2);
    private final WPI_VictorSPX rightMotor2 = new WPI_VictorSPX(1);

    private final SpeedControllerGroup leftSpeedContollers = new SpeedControllerGroup(leftMotor1, leftMotor2);
    private final SpeedControllerGroup rightSpeedContollers = new SpeedControllerGroup(rightMotor1, rightMotor2);

    public final DifferentialDrive robotDrive = new DifferentialDrive(leftSpeedContollers, rightSpeedContollers);

    public final WPI_TalonSRX hwheel = new WPI_TalonSRX(5);

  public DriveSubsystem() {

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
