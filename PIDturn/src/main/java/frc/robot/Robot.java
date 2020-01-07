/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;

/**
 * This is a demo program showing the use of the DifferentialDrive class.
 * Runs the motors with arcade steering.
 */
public class Robot extends TimedRobot {
  private final PWMVictorSPX leftMotor1 = new PWMVictorSPX(8);
  private final PWMVictorSPX leftMotor2 = new PWMVictorSPX(9);

  private final PWMVictorSPX rightMotor1 = new PWMVictorSPX(7);
  private final PWMVictorSPX rightMotor2 = new PWMVictorSPX(6);

  private final SpeedControllerGroup leftSpeedContollers = new SpeedControllerGroup(leftMotor1, leftMotor2);
  private final SpeedControllerGroup rightSpeedContollers = new SpeedControllerGroup(rightMotor1, rightMotor2);

  private final DifferentialDrive m_robotDrive = new DifferentialDrive(leftSpeedContollers, rightSpeedContollers);
  private final Joystick m_stick = new Joystick(0);

  private final int angle = 90;
  private final Gyro gyro = new ADXRS450_Gyro();
  

  private final GyroTurn turn = new GyroTurn(gyro, m_robotDrive);

  @Override
  public void robotInit() {
    gyro.calibrate();
    turn.setSetpoint(angle);

  }
  public void teleopPeriodic() {
    // Drive with arcade drive.
    // That means that the Y axis drives forward
    // and backward, and the X turns left and right.
    turn.execute();
    
  }
}
