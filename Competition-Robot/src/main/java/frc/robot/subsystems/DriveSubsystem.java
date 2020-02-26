package frc.robot.subsystems;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import static frc.robot.Constants.*;
import edu.wpi.first.wpilibj.SpeedControllerGroup;


public class DriveSubsystem extends SubsystemBase {
    private final WPI_TalonSRX leftMotor1 = new WPI_TalonSRX(scDriveLeft1);
    private final WPI_TalonSRX leftMotor2 = new WPI_TalonSRX(scDriveLeft2);
    private final WPI_TalonSRX rightMotor1 = new WPI_TalonSRX(scDriveRight1);
    private final WPI_TalonSRX rightMotor2 = new WPI_TalonSRX(scDriveRight2);
    private final WPI_TalonSRX hwheel = new WPI_TalonSRX(scHWheel);

    private final SpeedControllerGroup leftSpeedControllers = new SpeedControllerGroup(leftMotor1, leftMotor2);
    private final SpeedControllerGroup rightSpeedControllers = new SpeedControllerGroup(rightMotor1, rightMotor2);

    private DifferentialDrive diffDrive = new DifferentialDrive(leftSpeedControllers, rightSpeedControllers);

    public DriveSubsystem() {
        hwheel.setInverted(true);
        rightMotor1.configOpenloopRamp(kRampRate);
        rightMotor2.configOpenloopRamp(kRampRate);
        leftMotor1.configOpenloopRamp(kRampRate);
        leftMotor2.configOpenloopRamp(kRampRate);
        hwheel.configOpenloopRamp(kRampRate);
        diffDrive.arcadeDrive(0,0);
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }

    public DifferentialDrive getDiffDrive(){
        return diffDrive;
    }

    public void driveHWheel(double speed) {
        hwheel.set(speed);
    }
}
