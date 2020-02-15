package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import frc.robot.subsystems.DriveSubsystem;

public class DriveCommandGroup extends ParallelRaceGroup {

    public DriveCommandGroup(DriveSubsystem drive, CommandBase parallelCommand) {
        addCommands(

        new JoystickDriveCommand(drive), parallelCommand

        );
    }
}