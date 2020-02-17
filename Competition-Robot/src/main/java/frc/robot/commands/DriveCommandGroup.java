package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.HWheelSubsystem;

public class DriveCommandGroup extends ParallelRaceGroup {

    public DriveCommandGroup(DriveSubsystem drive, CommandBase parallelCommand) {
        addCommands(

        new JoystickDriveCommand(drive), parallelCommand

        );
    }
}