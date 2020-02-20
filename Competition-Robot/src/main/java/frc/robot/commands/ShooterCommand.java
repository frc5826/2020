/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import frc.robot.subsystems.ShooterSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;

import java.util.function.BooleanSupplier;

import static frc.robot.Constants.*;

/**
 * An example command that uses an example subsystem.
 */
public class ShooterCommand extends CommandBase {

  private final ShooterSubsystem shooterSubsystem;
  private BooleanSupplier shouldShoot;

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public ShooterCommand(ShooterSubsystem subsystem, BooleanSupplier shouldShoot) {
    shooterSubsystem = subsystem;
    this.shouldShoot = shouldShoot;
    addRequirements(shooterSubsystem);
  }

  public ShooterCommand(ShooterSubsystem subsystem) {
    this(subsystem, () -> true);
  }



  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled. This is the shooter. you idiot.
  @Override
  public void execute() {
    if(shouldShoot.getAsBoolean()){
      double current = shooterSubsystem.shoot(kShootSpeed);
      System.out.println("current " + current);

    }
    else{
      shooterSubsystem.stopShooter();
    }

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    shooterSubsystem.stopShooter();
  }

}
