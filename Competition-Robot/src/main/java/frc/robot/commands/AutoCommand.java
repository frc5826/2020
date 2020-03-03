package frc.robot.commands;

import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.LimelightSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

import static frc.robot.Constants.*;

public class AutoCommand extends TargetCommand {
    protected boolean isBackingup = true;

    public AutoCommand(DriveSubsystem driveSubsystem, LimelightSubsystem limelightSubsystem, ShooterSubsystem shoot) {
        super(driveSubsystem, limelightSubsystem, shoot);
    }

    @Override
    public void execute(){
        if (isBackingup && limelightSubsystem.getHeightAngle() > kBackupAngle && limelightSubsystem.isTargetVisable()){
            driveSubsystem.getDiffDrive().arcadeDrive(kBackupSpeed,0);
        } else{
            super.execute();
            isBackingup = false;
        }
    }

    @Override
    public boolean isTargetAcquired() {
        if (isBackingup) {
            return false;
        } else {
            return super.isTargetAcquired();
        }
    }

    @Override
    public void driveHWheel() {
        //No-op in autonomous
    }
}
