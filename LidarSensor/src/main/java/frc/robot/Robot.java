/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

//import edu.wpi.first.wpilibj.Joystick;
//import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.TimedRobot;
//import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.DigitalInput;

import org.usfirst.frc.team5826.robot.LidarLitePWM;

import edu.wpi.first.wpilibj.Counter;

/**
 * This is a demo program showing the use of the DifferentialDrive class.
 * Runs the motors with arcade steering.
 */
public class Robot extends TimedRobot {
  //private final PWMVictorSPX m_leftMotor = new PWMVictorSPX(0);
  //private final PWMVictorSPX m_rightMotor = new PWMVictorSPX(1);
  //private final DifferentialDrive m_robotDrive = new DifferentialDrive(m_leftMotor, m_rightMotor);
  //private final Joystick m_stick = new Joystick(0);
  private int counter;
  private LidarLitePWM lidarLeft = new LidarLitePWM(new DigitalInput(8));
  private LidarLitePWM lidarRight= new LidarLitePWM(new DigitalInput(7));
  @Override
  public void robotInit() {
    counter = 0;
  }

  @Override
  public void teleopPeriodic() {
    // Drive with arcade drive.
    // That means that the Y axis drives forward
    // and backward, and the X turns left and right.
    //m_robotDrive.arcadeDrive(m_stick.getY(), m_stick.getX());

    if(counter == 100) {
      double sonarValueLeft = lidarLeft.getDistance();
      double sonarValueRight = lidarRight.getDistance();
      System.out.println("The left distance (inches) is " + (sonarValueLeft / 2.54));
      System.out.println("The right distance (inches) is" + (sonarValueRight / 2.54));
      counter = 0;
    } else {
      counter = counter + 1;
    }
  }
}
