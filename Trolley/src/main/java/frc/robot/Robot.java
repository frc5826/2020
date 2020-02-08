/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Compressor;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.TimedRobot;

/**
 * This is a demo program showing the use of the DifferentialDrive class. Runs
 * the motors with arcade steering.
 */
public class Robot extends TimedRobot {
  private final WPI_TalonSRX leftMotor1 = new WPI_TalonSRX(1);
  private final WPI_TalonSRX leftMotor2 = new WPI_TalonSRX(2);
  private final WPI_TalonSRX rightMotor1 = new WPI_TalonSRX(3);
  private final WPI_TalonSRX rightMotor2 = new WPI_TalonSRX(4);

  private final SpeedControllerGroup leftSpeedControllers = new SpeedControllerGroup(leftMotor1, leftMotor2);
  private final SpeedControllerGroup rightSpeedControllers = new SpeedControllerGroup(rightMotor1, rightMotor2);
  DifferentialDrive robotDrive = new DifferentialDrive(leftSpeedControllers, rightSpeedControllers);
  
  
  private final Joystick m_stick = new Joystick(0);
  private final AHRS gyro = new AHRS(SPI.Port.kMXP);
  private final WPI_TalonSRX trolley = new WPI_TalonSRX(6);
  private double speed;
  private final DoubleSolenoid piston = new DoubleSolenoid(11,2,3);

  int i = 0;

  @Override
  public void robotInit() {
  }

  public void teleopPeriodic() {
    if(m_stick.getRawButton(8)) {
      piston.set(DoubleSolenoid.Value.kForward);
    }
    else{
      piston.set(DoubleSolenoid.Value.kReverse);
    }

    robotDrive.arcadeDrive(m_stick.getY() , m_stick.getZ() * 0.75);
    
    speed = gyro.getPitch() / 20;
    trolley.set(speed);

    if(i++ % 100 == 0){
      System.out.println("Pitch " + gyro.getPitch());
      System.out.println("Speed " + speed);

    }
    
    // ball shooter code
  }
}
