/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.Encoder;

/**
 * This is a demo program showing the use of the DifferentialDrive class. Runs
 * the motors with arcade steering.
 */
public class Robot extends TimedRobot {
  //private final WPI_VictorSPX leftMotor1 = new WPI_VictorSPX(4);
  //private final WPI_VictorSPX leftMotor2 = new WPI_VictorSPX(3);
  private final WPI_TalonSRX leftMotor1 = new WPI_TalonSRX(4);
  private final WPI_TalonSRX leftMotor2 = new WPI_TalonSRX(3);

  //private final WPI_VictorSPX rightMotor1 = new WPI_VictorSPX(2);
  //private final WPI_VictorSPX rightMotor2 = new WPI_VictorSPX(1);
  private final WPI_TalonSRX rightMotor1 = new WPI_TalonSRX(2);
  private final WPI_TalonSRX rightMotor2 = new WPI_TalonSRX(1);

  private final SpeedControllerGroup leftSpeedContollers = new SpeedControllerGroup(leftMotor1, leftMotor2);
  private final SpeedControllerGroup rightSpeedContollers = new SpeedControllerGroup(rightMotor1, rightMotor2);

  private final DifferentialDrive m_robotDrive = new DifferentialDrive(leftSpeedContollers, rightSpeedContollers);
  private final Joystick m_stick = new Joystick(0);
  private double throttle = 0;
  private final Hwheel hwheel = new Hwheel(m_stick, throttle);
  private final int angle = 90;
  private final AHRS gyro = new AHRS(SPI.Port.kMXP);

  Encoder leftEncoder = new Encoder(0, 1);
  Encoder rightEncoder = new Encoder(2, 3);

  private final GyroTurn turn = new GyroTurn(gyro, m_robotDrive);

  @Override
  public void robotInit() {
    turn.setSetpoint(angle);
  }

  public void teleopPeriodic() {
    throttle = ((m_stick.getThrottle() * -1) + 1) / 2; // throttle 0-1
    // driving mode selector
    //System.out.println(leftEncoder.get());
    if (m_stick.getRawButton(1)) {
      turn.execute();
    } else {
      m_robotDrive.arcadeDrive(m_stick.getY() * throttle * -1, m_stick.getZ() * 0.75 * throttle);
      hwheel.run();
    }
    if (m_stick.getRawButtonPressed(7)) {
      turn.setSetpoint(angle);
    }

    // ball shooter code
  }
}
