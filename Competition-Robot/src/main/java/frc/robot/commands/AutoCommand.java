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

    double P = 0.050;
    double I = 0.0025;
    double D = 0.0065;
    double previous_error, integral = 0;
    int setpoint = Integer.MAX_VALUE;
    DifferentialDrive robotDrive;
    AHRS gyro;
    double error;

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
            setpoint = (int)(Constants.gyro.getAngle() + limelightSubsystem.getTargetAngleOffset());
        //    System.out.println("setpoint " + setpoint);
           System.out.println("rcw " + rcw);
        //   System.out.println("Angle " + Constants.gyro.getAngle());
        //   System.out.println("offset " + limelightSubsystem.getTargetAngleOffset());
        //   System.out.println("error " + error);
            PID();
            robotDrive.arcadeDrive(0, rcw);
            driveSubsystem.hwheel.set(Constants.joystick.getX() * -1);
           
        }
        else{
            robotDrive.arcadeDrive(0, 0.6);
        }
        
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return !Constants.joystick.getRawButton(1);
    }

    public void PID() {
        error = setpoint - gyro.getAngle(); // Error = Target - Actual
        this.integral += (error * .02); // Integral is increased by the error*time (which is .02 seconds using normal
                                        // IterativeRobot)
        final double derivative = (error - this.previous_error) / .02;
        this.rcw = P*error + I*this.integral + D*derivative;
        this.previous_error = error;
    }

}