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
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.VictorSP;
//import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.Encoder;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.Joystick;


/**
 * This is a demo program showing the use of the DifferentialDrive class.
 * Runs the motors with arcade steering.
 */
public class Robot extends TimedRobot {
  //private final PWMVictorSPX m_leftMotor = new PWMVictorSPX(0);
  //private final PWMVictorSPX m_rightMotor = new PWMVictorSPX(1);
  //private final DifferentialDrive m_robotDrive = new DifferentialDrive(m_leftMotor, m_rightMotor);
  //private final Joystick m_stick = new Joystick(0);
  private final Encoder DigitalEncoder1 = new Encoder(6,7,8);
  private final Encoder rs7Encoder = new Encoder(4,5);
  private int counter = 0;
  private double previousValue;
  VictorSP hwheel = new VictorSP(5);
  double speed = 0;
  Joystick stick = new Joystick(0);
  double currentValue = 0;
  int rs7Counter;
  Timer time = new Timer();
  double previousTime;
  double previousDistance;

  @Override
  public void robotInit() {
    time.start();
    previousTime = 0;
    previousDistance = 0;
    speed = -1;
    counter = 0;
    previousValue = 0;
    hwheel.set(0.0);
    rs7Counter = 0;
  }

  @Override
  public void teleopPeriodic() {
    // Drive with arcade drive.
    // That means that the Y axis drives forward
    // and backward, and the X turns left and right.
    //m_robotDrive.arcadeDrive(m_stick.getY(), m_stick.getX());
    
    if(rs7Counter == 20) {
      double seconds = previousTime - time.get();
      double dist = previousDistance - rs7Encoder.getDistance();
      System.out.println("RPM is " + dist/seconds * 60/4 + " Seconds:" + seconds);
      // System.out.println("RS7 Encoder is " + rs7Encoder.getDistance());
      rs7Counter = 0;
      previousDistance = rs7Encoder.getDistance();
      previousTime = time.get();
    } else {
      rs7Counter = rs7Counter + 1;
    }

    if(stick.getRawButton(10)){
      hwheel.set(-0.5);
      counter = 0;
      DigitalEncoder1.reset();

      while(counter < 2){
        currentValue = DigitalEncoder1.getDistance();
        if((currentValue + 200) < previousValue){
          counter = counter + 1;
          System.out.println("counter is " + counter);
        }

        previousValue = currentValue;

      }
      
      hwheel.set(0.0);

    }
  
    if(stick.getRawButton(9)){
      hwheel.set(0.5);
      counter = 0;
      DigitalEncoder1.reset();

      while(counter < 2){
        currentValue = DigitalEncoder1.getDistance();
        if((currentValue - 200) > previousValue){
          counter = counter + 1;
          System.out.println("counter is " + counter);
        }

        previousValue = currentValue;

      }

      hwheel.set(0.0);

    }

}

  
}
