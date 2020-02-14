/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.AutoCommand;
import frc.robot.commands.ShooterCommand;
import frc.robot.commands.HwheelLowerCommand;
import frc.robot.commands.HwheelRaiseCommand;
import frc.robot.commands.JoystickDriveCommand;
import frc.robot.commands.TrolleyCommand;
import frc.robot.commands.TrolleyRaiseCommand;
import frc.robot.commands.TurnCommand;
import frc.robot.commands.ConveyorCommand;
import frc.robot.commands.IntakeCommand;
import frc.robot.subsystems.BallIntakeSubsystem;
import frc.robot.subsystems.BallLifterSubsystem;
import frc.robot.subsystems.ClimbSubsystem;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.Fondler3000Subsystem;
import frc.robot.subsystems.LimelightSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.PrintCommand;
import edu.wpi.first.wpilibj2.command.Subsystem;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

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
  private final Fondler3000Subsystem fondler3000Subsystem = new Fondler3000Subsystem();
  //private final BallLifterSubsystem ballLifterSubsystem = new BallLifterSubsystem();
  //private final BallIntakeSubsystem ballIntakeSubsystem = new BallIntakeSubsystem();

  private final JoystickDriveCommand joystickDrive = new JoystickDriveCommand(driveSubsystem);
  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();

    //ballIntakeSubsystem.register();
    //ballLifterSubsystem.register();
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    JoystickButton spin = new JoystickButton(Constants.joystick, 7);
    spin.whenPressed(new TurnCommand(driveSubsystem, 90));
    JoystickButton center = new JoystickButton(Constants.joystick, 2);
    center.whenPressed(new AutoCommand(driveSubsystem, limelightSubsystem));
    JoystickButton climb = new JoystickButton(Constants.joystick, 8);
    climb.whenPressed(new TrolleyCommand(climbSubsystem, driveSubsystem));
    JoystickButton intake = new JoystickButton(Constants.joystick, 4);
    intake.whenPressed(new IntakeCommand(fondler3000Subsystem));
    JoystickButton manualConveyor = new JoystickButton(Constants.joystick, 7);
    manualConveyor.whenPressed(new ConveyorCommand(fondler3000Subsystem));
    JoystickButton shoot = new JoystickButton(Constants.joystick, 1);
    shoot.whenPressed(new ShooterCommand(fondler3000Subsystem));
    JoystickButton raise = new JoystickButton(Constants.joystick, 10);
    raise.whenPressed(new HwheelRaiseCommand(driveSubsystem));
    JoystickButton lower = new JoystickButton(Constants.joystick, 9);
    lower.whenPressed(new HwheelLowerCommand(driveSubsystem));
    JoystickButton trolleyRaise = new JoystickButton(Constants.joystick, 11);
    trolleyRaise.whenPressed(new TrolleyRaiseCommand(climbSubsystem));
  }


  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    return new PrintCommand("Running autonomous...");
  }

  public Command getJoystickDrive(){
    return joystickDrive;
  }

  public Subsystem getDriveSubsystem(){
    return driveSubsystem;
  }
}



