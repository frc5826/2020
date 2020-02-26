package frc.robot.commands;


import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.LimelightSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.PID;

import static frc.robot.Constants.*;

/**
 * An example command that uses an example subsystem.
 */
public class TargetCommand extends CommandBase {

    protected final DriveSubsystem driveSubsystem;
    private final LimelightSubsystem limelightSubsystem;
    private final ShooterSubsystem shooterSubsystem;
    private PID pidTurn;
    private PID pidDrive;

    private double rcw;
    private double driveY;

    double previous_error, integral = 0;
    int setpoint = Integer.MAX_VALUE;
    double error;

    public TargetCommand(DriveSubsystem driveSubsystem, LimelightSubsystem limelightSubsystem, ShooterSubsystem shoot){
        this.shooterSubsystem = shoot;
        this.driveSubsystem = driveSubsystem;
        this.limelightSubsystem = limelightSubsystem;
        addRequirements(driveSubsystem, limelightSubsystem);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        pidTurn = new PID(kTargetTurnP, kTargetTurnI, kTargetTurnD, kTargetTurnMax, kTargetTurnMin, kTargetTurnTolerance);
        pidDrive = new PID(kTargetDriveP, kTargetDriveI, kTargetDriveI, kTargetDriveMax, kTargetDriveMin, kTargetDriveTolerance);
    }
    // Called every time the scheduler runs while the command is scheduled.

    int i = 0;
    @Override
    public void execute() {
        if(limelightSubsystem.isTargetVisable()){
            pidTurn.setSetpoint(Constants.gyro.getAngle() + limelightSubsystem.getTargetAngleOffset());
            pidDrive.setSetpoint(kLLHeightAngle);
            pidTurn.calculate(gyro.getAngle());
            pidDrive.calculate(limelightSubsystem.getHeightAngle());
            driveSubsystem.getDiffDrive().arcadeDrive(pidDrive.getOutput(), pidTurn.getOutput());
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

    //Method to override in the auto version of this command
    public void driveHWheel() {
        driveSubsystem.driveHWheel(Constants.joystick.getX() * -1);
    }

    //TODO - Implement when the robot is in a good spot to shoot
    public boolean isTargetAcquired() {
        if (Math.abs(pidDrive.getError()) < kTargetDriveTolerance &&
                Math.abs(pidTurn.getError()) < kTargetTurnTolerance &&
                limelightSubsystem.isTargetVisable()) {
            return true;
        } else {
            return false;
        }
    }
}