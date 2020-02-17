package frc.robot.commands;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.LimelightSubsystem;
import static frc.robot.Constants.*;

/**
 * An example command that uses an example subsystem.
 */
public class TargetCommand extends CommandBase {

    protected final DriveSubsystem driveSubsystem;
    private final LimelightSubsystem limelightSubsystem;

    private double rcw;
    private double driveY;

    double previous_error, integral = 0;
    int setpoint = Integer.MAX_VALUE;
    double error;

    public TargetCommand(DriveSubsystem driveSubsystem, LimelightSubsystem limelightSubsystem){

        this.driveSubsystem = driveSubsystem;
        this.limelightSubsystem = limelightSubsystem;

        addRequirements(driveSubsystem, limelightSubsystem);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() { }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        if(limelightSubsystem.isTargetVisable()){
            setpoint = (int)(Constants.gyro.getAngle() + limelightSubsystem.getTargetAngleOffset());
            System.out.println("rcw " + rcw);
            PID();
            TargetDistance();
            driveSubsystem.getDiffDrive().arcadeDrive(driveY, rcw);
            driveHWheel();
        }
        else{
            driveSubsystem.getDiffDrive().arcadeDrive(0, kTargetTurn);
        }

    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
    }

    public void PID() {
        error = setpoint - gyro.getAngle(); // Error = Target - Actual
        this.integral += (error * .02); // Integral is increased by the error*time (which is .02 seconds using normal
        final double derivative = (error - this.previous_error) / .02;
        this.rcw = kTargetP * error + kTargetI * this.integral + kTargetD * derivative;
        this.previous_error = error;
    }

    public void TargetDistance() {
        if(limelightSubsystem.getHeightAngle() > kLLHeightAngle + kTargetErrorMargin) {
            driveY = -.5;
        }
        else if(limelightSubsystem.getHeightAngle() < kLLHeightAngle - kTargetErrorMargin) {
            driveY = .5;
        } else {
            driveY = 0;
        }
    }

    //Method to override in the auto version of this command
    public void driveHWheel() {
        driveSubsystem.getHWheel().set(Constants.joystick.getX() * -1);
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return !bTarget.isPressed();
    }

}