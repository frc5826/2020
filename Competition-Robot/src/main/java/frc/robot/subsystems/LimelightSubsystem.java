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
    pipeline.forceSetDouble(0);
  }
  public void setGreenLight(boolean light) {
    NetworkTableEntry tled = table.getEntry("ledMode");
    tled.setDouble(light ? 0 : 1);
  }
  public double getTargetDistance(){

    NetworkTableEntry tz = table.getEntry("tz");
    double z = tz.getDouble(0.0);
    
    return z;
  }
  public double getPipeline(){
    NetworkTableEntry pipeline = table.getEntry("pipeline");
    return pipeline.getDouble(-1);

  }
  public double getTargetAngleOffset(){

    NetworkTableEntry tx = table.getEntry("tx");
    double x = tx.getDouble(0.0);

    return x;
    
  }
  public void setPipeline(double value){
    NetworkTableEntry pipeline = table.getEntry("pipeline");
    pipeline.forceSetDouble(value);
  }
  public double getHeightAngle(){
      
    NetworkTableEntry ty = table.getEntry("ty");
    double y = ty.getDouble(0.0);

    return y;

  }

  public boolean isTargetVisable(){
    NetworkTableEntry ta = table.getEntry("ta");
    double a = ta.getDouble(0.0);

    if (java.lang.Math.abs(a) < 0.05) {
      return false;
    } else {
      return true;
    }


  }

  public boolean isCentered() {

    NetworkTableEntry tx = table.getEntry("tx");
    double x = tx.getDouble(0.0);

    if (java.lang.Math.abs(x) < 1) {
      return true;
    } else {
      return false;
    }
  }
}
