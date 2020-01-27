package frc.robot.commands;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.LimelightSubsystem;

/**
 * An example command that uses an example subsystem.
 */
public class AutoCommand extends CommandBase {

    private final DriveSubsystem driveSubsystem;
    private final LimelightSubsystem limelightSubsystem;
    private double rcw;

    double P = 0.068;
    double I = 0;
    double D = 0.01;
    double previous_error, integral = 0;
    int setpoint = 0;
    DifferentialDrive robotDrive;
    AHRS gyro;

    public AutoCommand(DriveSubsystem driveSubsystem, LimelightSubsystem limelightSubsystem){
        
        this.driveSubsystem = driveSubsystem;
        this.limelightSubsystem = limelightSubsystem;

        addRequirements(driveSubsystem, limelightSubsystem);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {

        robotDrive = new DifferentialDrive(driveSubsystem.getLeftSpeedController(), driveSubsystem.getRightSpeedController());
        gyro = Constants.gyro;

    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        if(limelightSubsystem.isTargetVisable()){
            setpoint = (int)limelightSubsystem.getTargetAngleOffset();

            PID();
            robotDrive.arcadeDrive(0, rcw);
        }
        else{
            robotDrive.arcadeDrive(0, 0.2);
        }
        
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }

    public void PID() {
        final double error = setpoint - gyro.getAngle(); // Error = Target - Actual
        this.integral += (error * .02); // Integral is increased by the error*time (which is .02 seconds using normal
                                        // IterativeRobot)
        final double derivative = (error - this.previous_error) / .02;
        this.rcw = P*error + I*this.integral + D*derivative;
        this.previous_error = error;
    }

}