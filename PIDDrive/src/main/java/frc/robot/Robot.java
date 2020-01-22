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
 * This is a demo program showing the use of the DifferentialDrive class.
 * Runs the motors with arcade steering.
 */
public class Robot extends TimedRobot {
  private final WPI_VictorSPX leftMotor1 = new WPI_VictorSPX(4);
  private final WPI_VictorSPX leftMotor2 = new WPI_VictorSPX(3);

  private final WPI_VictorSPX rightMotor1 = new WPI_VictorSPX(2);
  private final WPI_VictorSPX rightMotor2 = new WPI_VictorSPX(1);

  private final SpeedControllerGroup leftSpeedContollers = new SpeedControllerGroup(leftMotor1, leftMotor2);
  private final SpeedControllerGroup rightSpeedContollers = new SpeedControllerGroup(rightMotor1, rightMotor2);

  private final DifferentialDrive m_robotDrive = new DifferentialDrive(leftSpeedContollers, rightSpeedContollers);
  private final Joystick m_stick = new Joystick(0);
  private double throttle = 0;
  private final Hwheel hwheel = new Hwheel(m_stick);

  double P = 0.068;
  double I = 0;
  double D = 0.01;
  double setpoint = 92;
  double previous_error, integral = 0;
  private double rcw;

  Encoder leftEncoder = new Encoder(0, 1);
	Encoder rightEncoder = new Encoder(2, 3);

  int count = 0;


  @Override
  public void robotInit(){

  }

  @Override
  public void teleopInit() {
    resetEncoders();
    if(leftEncoder.get() < 10 && leftEncoder.get() > -10){
      System.out.println("Encoders reset");
    }
    else {
      System.out.println("ENCODERS NOT RESET");

    }
  }

  public void teleopPeriodic() {
    
    PID();

    double drive = Math.min(rcw, .5);
    drive = Math.max(drive, -.5);

    m_robotDrive.curvatureDrive(drive, straighten(), false);
    
    System.out.println("Drive: " + drive);

    // if(count++ % 10 == 0){
    //   System.out.println(rightEncoder.get() + ", " + leftEncoder.get());
    //   System.out.println("Previous Error: " + previous_error);
    //   System.out.println("RCW: " + rcw);
    // }
  }

  public void resetEncoders(){
    leftEncoder.reset();
    rightEncoder.reset();
  }

  public double straighten(){
    double dif = (rightEncoder.get() - leftEncoder.get()) / 50.0;
    
    double turn = Math.min(dif, .5);
    turn = Math.max(turn, -.5);

    return turn;
  }

  public void PID() {
    final double cm_driven = (leftEncoder.get() / -4.56);
    final double error = setpoint - cm_driven; // Error = Target - Actual
    
    if(count++ % 10 == 0){
      System.out.println("leftEncoder: " + leftEncoder.get());
      System.out.println("cm_driven: " + cm_driven);
      System.out.println("setpoint: " + setpoint);
      System.out.println("error: " + error);
    }

    this.integral += (error * .02); // Integral is increased by the error*time (which is .02 seconds using normal
                                    // IterativeRobot)
    final double derivative = (error - this.previous_error) / .02;
    this.rcw = P*error + I*this.integral + D*derivative;
    this.previous_error = error;
  }

}