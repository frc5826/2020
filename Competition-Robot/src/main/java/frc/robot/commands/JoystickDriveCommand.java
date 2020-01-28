/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import frc.robot.subsystems.DriveSubsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;

/**
 * An example command that uses an example subsystem.
 */
public class JoystickDriveCommand extends CommandBase {
  
  private final DriveSubsystem m_subsystem;
  private DifferentialDrive robotDrive;
  

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public JoystickDriveCommand(DriveSubsystem subsystem) {
    m_subsystem = subsystem;
    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    robotDrive = new DifferentialDrive(m_subsystem.getLeftSpeedController(), m_subsystem.getRightSpeedController());
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    robotDrive.arcadeDrive(Constants.joystick.getY(), Constants.joystick.getZ());
    m_subsystem.hwheel.set(Constants.joystick.getX()*-1);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}