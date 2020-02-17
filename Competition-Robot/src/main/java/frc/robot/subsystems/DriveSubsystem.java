package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import static frc.robot.Constants.*;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;


public class DriveSubsystem extends SubsystemBase {
    private final WPI_TalonSRX leftMotor1 = new WPI_TalonSRX(scDriveLeft1);
    private final WPI_TalonSRX leftMotor2 = new WPI_TalonSRX(scDriveLeft2);
    private final WPI_TalonSRX rightMotor1 = new WPI_TalonSRX(scDriveRight1);
    private final WPI_TalonSRX rightMotor2 = new WPI_TalonSRX(scDriveRight2);
    public final WPI_TalonSRX hwheel = new WPI_TalonSRX(scHWheel);

    public final SpeedControllerGroup leftSpeedControllers = new SpeedControllerGroup(leftMotor1, leftMotor2);
    public final SpeedControllerGroup rightSpeedControllers = new SpeedControllerGroup(rightMotor1, rightMotor2);

  public DriveSubsystem() {
      hwheel.setInverted(true);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public SpeedController getLeftSpeedController(){
      return leftSpeedControllers;
  }
  public SpeedController getRightSpeedController(){
      return rightSpeedControllers;
  }
}
