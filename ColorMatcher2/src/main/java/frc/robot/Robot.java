/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

import com.revrobotics.ColorSensorV3;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorMatch;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
/**
 * This is a demo program showing the use of the DifferentialDrive class.
 * Runs the motors with arcade steering.
 */
public class Robot extends TimedRobot {
  //private final PWMVictorSPX m_leftMotor = new PWMVictorSPX(0);
  //private final PWMVictorSPX m_rightMotor = new PWMVictorSPX(1);
  //private final DifferentialDrive m_robotDrive = new DifferentialDrive(m_leftMotor, m_rightMotor);
  //private final Joystick m_stick = new Joystick(0);
  private final I2C.Port i2cPort = I2C.Port.kOnboard;
  private final ColorSensorV3 m_colorSensor = new ColorSensorV3(i2cPort);
  private final ColorMatch m_colorMatcher = new ColorMatch();
  private final Color kBlueTarget = ColorMatch.makeColor(0.143, 0.427, 0.429);
  private final Color kGreenTarget = ColorMatch.makeColor(0.197, 0.561, 0.240);
  private final Color kRedTarget = ColorMatch.makeColor(0.561, 0.232, 0.114);
  private final Color kYellowTarget = ColorMatch.makeColor(0.361, 0.524, 0.113);
  private final PWMVictorSPX leftMotor1 = new PWMVictorSPX(8);
  private final PWMVictorSPX leftMotor2 = new PWMVictorSPX(9);

  private final PWMVictorSPX rightMotor1 = new PWMVictorSPX(7);
  private final PWMVictorSPX rightMotor2 = new PWMVictorSPX(6);

  private final SpeedControllerGroup leftSpeedContollers = new SpeedControllerGroup(leftMotor1, leftMotor2);
  private final SpeedControllerGroup rightSpeedContollers = new SpeedControllerGroup(rightMotor1, rightMotor2);
  private final DifferentialDrive m_robotDrive = new DifferentialDrive(leftSpeedContollers, rightSpeedContollers);

  private int i = 0;

  @Override
  public void robotInit() {

  }

  @Override
  public void teleopPeriodic() {
    m_robotDrive.arcadeDrive(0, 0);
    // Drive with arcade drive.
    // That means that the Y axis drives forward
    // and backward, and the X turns left and right.
    //m_robotDrive.arcadeDrive(m_stick.getY(), m_stick.getX());

    Color detectedColor = m_colorSensor.getColor();
    
    double red = detectedColor.red;
    double green = detectedColor.green;
    double blue = detectedColor.blue;

    //Only print on 150 iterations so the logs don't get bloated
    if(i++ % 150 == 0){
      System.out.println("Red: " + red);
      System.out.println("Green: " + green);
      System.out.println("Blue: " + blue);
      System.out.println("Proximity: " + m_colorSensor.getProximity());
    }

  }
}
