/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.commands.*;
import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Subsystem;
import static frc.robot.Constants.*;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final DriveSubsystem driveSubsystem = new DriveSubsystem();
  private final LimelightSubsystem limelightSubsystem = new LimelightSubsystem();
  private final ClimbSubsystem climbSubsystem = new ClimbSubsystem();
  private final ShooterSubsystem shooterSubsystem = new ShooterSubsystem();
  private final HWheelSubsystem hWheelSubsystem = new HWheelSubsystem();

  private final JoystickDriveCommand driveCommand = new JoystickDriveCommand(driveSubsystem);

  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    TargetCommand tc = new TargetCommand(driveSubsystem, limelightSubsystem, shooterSubsystem);
    bTarget.createButton().whenHeld(new ParallelCommandGroup(tc, new ShooterCommand(shooterSubsystem, () -> tc.isTargetAcquired())));

    bBalanceAuto.createButton().whenHeld(new TrolleyBalanceCommand(climbSubsystem));
    bBalanceLeft.createButton().whenHeld(new TrolleyLeftCommand(climbSubsystem));
    bBalanceRight.createButton().whenHeld(new TrolleyRightCommand(climbSubsystem));
    bIntake.createButton().whenHeld(new DriveCommandGroup(driveSubsystem, new IntakeCommand(shooterSubsystem)));
    bShoot.createButton().whenHeld(new DriveCommandGroup(driveSubsystem, new ShooterCommand(shooterSubsystem)));
    bRaiseHWheel.createButton().whenPressed(new DriveCommandGroup(driveSubsystem, new HwheelRaiseCommand(hWheelSubsystem)));
    bLowerHWheel.createButton().whenPressed(new DriveCommandGroup(driveSubsystem, new HwheelLowerCommand(hWheelSubsystem)));
    bRaiseLift.createButton().whenPressed(new RaiseLiftCommand(climbSubsystem));
    bLowerLift.createButton().whenPressed(new LowerLiftCommand(climbSubsystem));
    bRaiseTrolley.createButton().whenPressed(new RaiseTrolleyCommand(climbSubsystem));
    bLowerTrolley.createButton().whenPressed(new LowerTrolleyCommand(climbSubsystem));
  }


  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    return new AutoCommand(driveSubsystem, limelightSubsystem, shooterSubsystem);
  }

  public Subsystem getDriveSubsystem(){
    return driveSubsystem;
  }


  public JoystickDriveCommand getJoystickDrive() {
    return driveCommand;
  }

}



