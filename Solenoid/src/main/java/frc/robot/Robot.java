/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.TimedRobot;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  HashMap<Integer, DoubleSolenoid> solenoids = new HashMap<>();
  List<DoubleSolenoid.Value> values = Arrays.asList(DoubleSolenoid.Value.kForward, DoubleSolenoid.Value.kOff, DoubleSolenoid.Value.kReverse);
  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */

   int iterations = 0;
   int currentValue = 0;  


  @Override
  public void robotInit() {
    solenoids.put(0, new DoubleSolenoid(11, 1,0));
    // solenoids.put(2, new DoubleSolenoid(11, 3,2));
    // solenoids.put(1, new DoubleSolenoid(11, 4,5));
    // solenoids.put(3, new DoubleSolenoid(11, 6,7));
  }

  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the
   * LabVIEW Dashboard, remove all of the chooser code and uncomment the
   * getString line to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional comparisons to
   * the switch structure below with additional strings. If using the
   * SendableChooser make sure to add them to the chooser code above as well.
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
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    // iterations++;
    // currentSolenoid = (iterations / 50) % 4;
    // DoubleSolenoid solenoid = solenoids.get(currentSolenoid);
    // DoubleSolenoid preSolenoid = solenoids.get((currentSolenoid + 3) % 4);

    // if(solenoid != null){
    //   solenoid.set(DoubleSolenoid.Value.kForward);
    // }
    // if(preSolenoid != null){
    //   preSolenoid.set(DoubleSolenoid.Value.kReverse);
    // }
    
    if(iterations++ % 150 == 0){
      DoubleSolenoid.Value value = values.get(currentValue++ % values.size());
      System.out.println(value);
      solenoids.values().forEach(s -> s.set(value));
    }


    // if(iterations++ % 250 == 0){
    //   System.out.println("Forward");
    //   solenoids.values().forEach(s -> s.set(DoubleSolenoid.Value.kForward));
    // }
    // else if(iterations % 120 == 0){
    //   System.out.println("Reverse");
    //   solenoids.values().forEach(s -> s.set(DoubleSolenoid.Value.kReverse));
    // }
    // else if(iterations % 60 == 0){
    //   System.out.println("Off");
    //   solenoids.values().forEach(s -> s.set(DoubleSolenoid.Value.kOff));
    // }


  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
