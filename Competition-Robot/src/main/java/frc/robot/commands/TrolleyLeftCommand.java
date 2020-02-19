/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import frc.robot.subsystems.ClimbSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;
import static frc.robot.Constants.*;

/**
 * An example command that uses an example subsystem.
 */
public class TrolleyLeftCommand extends CommandBase {
  
  private final ClimbSubsystem climbSubsystem;


  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public TrolleyLeftCommand(ClimbSubsystem subsystem) {
    climbSubsystem = subsystem;
    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    
  }

  // 
  @Override
  public void execute() {
    climbSubsystem.trolleyMotor.set(kTrolley);
  }

  // 
  @Override
  public void end(boolean interrupted) {
    climbSubsystem.trolleyMotor.set(0);
  }
}
