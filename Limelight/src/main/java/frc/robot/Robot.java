/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.TimedRobot;


/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends TimedRobot {
    NetworkTable table;
  

  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
      table = NetworkTableInstance.getDefault().getTable("limelight");
      NetworkTableEntry pipeline = table.getEntry("pipeline");
      pipeline.forceSetDouble(7);
  }

  /**
   * This function is run once each time the robot enters autonomous mode.
   */
  @Override
  public void autonomousInit() {
    
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    
  }

  /**
   * This function is called once each time the robot enters teleoperated mode.
   */
  @Override
  public void teleopInit() {
  }

  int counter = 0;
  

  /**
   * This function is called periodically during teleoperated mode.
   */
  @Override
  public void teleopPeriodic() {
    if(counter == 100){
      counter = 0;
      NetworkTableEntry tx = table.getEntry("tx");
      NetworkTableEntry ta = table.getEntry("ta");
      NetworkTableEntry tv = table.getEntry("tv");
  
      double target = tv.getDouble(0.0);
      double x = tx.getDouble(0.0);
      double area = ta.getDouble(0.0);
  
      System.out.println("Target: " + target);
      System.out.println("x: " + x);
      System.out.println("Area: " + area);
    }
    else{counter = counter + 1;}

    
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
