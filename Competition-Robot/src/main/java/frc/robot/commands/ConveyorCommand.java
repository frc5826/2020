/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import frc.robot.subsystems.ClimbSubsystem;
import frc.robot.subsystems.Fondler3000Subsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;

/**
 * An example command that uses an example subsystem.
 */
public class ConveyorCommand extends CommandBase {
  
  private final Fondler3000Subsystem fondler3000Subsystem;
  

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public ConveyorCommand(Fondler3000Subsystem subsystem) {
    fondler3000Subsystem = subsystem;
    addRequirements(fondler3000Subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {

  }

  // Called every time the scheduler runs while the command is scheduled. This is the shooter. you idiot.
  @Override
  public void execute() {
    if(Constants.xbox.getYButton()){
        fondler3000Subsystem.conveyorMotor.set(1);

    } else if(Constants.xbox.getBButton()) {
        fondler3000Subsystem.conveyorMotor.set(-1);

    } else {
        fondler3000Subsystem.conveyorMotor.set(0);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    fondler3000Subsystem.conveyorMotor.set(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return Constants.joystick.getRawButton(7);
  }
}
