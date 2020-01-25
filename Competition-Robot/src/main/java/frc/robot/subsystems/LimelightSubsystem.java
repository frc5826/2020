/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class LimelightSubsystem extends SubsystemBase {
  /**
   * Creates a new ExampleSubsystem.
   */

  private NetworkTable table;

  public LimelightSubsystem() {
    table = NetworkTableInstance.getDefault().getTable("limelight");
    NetworkTableEntry pipeline = table.getEntry("pipeline");
    pipeline.forceSetDouble(7);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public boolean isCentered() {

    NetworkTableEntry tx = table.getEntry("tx");
    double x = tx.getDouble(0.0);

    if (x < 1) {
      return true;
    } else {
      return false;
    }
    
      
    
  }
}
