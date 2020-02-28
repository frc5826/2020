package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
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

    private final SpeedControllerGroup leftSpeedControllers;
    private final SpeedControllerGroup rightSpeedControllers;
    private final DifferentialDrive diffDrive;

    public DriveSubsystem() {
        leftMotor1.configFactoryDefault();
        leftMotor2.configFactoryDefault();
        rightMotor1.configFactoryDefault();
        rightMotor2.configFactoryDefault();
        hwheel.configFactoryDefault();

        leftMotor1.setNeutralMode(NeutralMode.Brake);
        leftMotor2.setNeutralMode(NeutralMode.Brake);
        rightMotor1.setNeutralMode(NeutralMode.Brake);
        rightMotor2.setNeutralMode(NeutralMode.Brake);
        hwheel.setNeutralMode(NeutralMode.Brake);

        rightMotor1.configOpenloopRamp(kRampRate);
        rightMotor2.configOpenloopRamp(kRampRate);
        leftMotor1.configOpenloopRamp(kRampRate);
        leftMotor2.configOpenloopRamp(kRampRate);
        hwheel.configOpenloopRamp(kRampRate);

        hwheel.setInverted(true);

        leftSpeedControllers = new SpeedControllerGroup(leftMotor1, leftMotor2);
        rightSpeedControllers = new SpeedControllerGroup(rightMotor1, rightMotor2);
        diffDrive = new DifferentialDrive(leftSpeedControllers, rightSpeedControllers);

        diffDrive.arcadeDrive(0,0);

    }

    public DifferentialDrive getDiffDrive(){
        return diffDrive;
    }

    public void driveHWheel(double speed) {
        hwheel.set(speed);
    }
}
