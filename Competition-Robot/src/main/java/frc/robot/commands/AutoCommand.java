package frc.robot.commands;

import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.LimelightSubsystem;

public class AutoCommand extends TargetCommand {

    public AutoCommand(DriveSubsystem driveSubsystem, LimelightSubsystem limelightSubsystem) {
        super(driveSubsystem, limelightSubsystem);
    }

    @Override
    public void driveHWheel() {
        //No-op in autonomous
    }

    @Override
    public boolean isFinished() {
        //Run until canceled in auto
        return false;
    }
}
