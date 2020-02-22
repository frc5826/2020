package frc.robot.commands;

import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.LimelightSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class AutoCommand extends TargetCommand {

    public AutoCommand(DriveSubsystem driveSubsystem, LimelightSubsystem limelightSubsystem, ShooterSubsystem shoot) {
        super(driveSubsystem, limelightSubsystem, shoot);
    }

    @Override
    public void driveHWheel() {
        //No-op in autonomous
    }
}
