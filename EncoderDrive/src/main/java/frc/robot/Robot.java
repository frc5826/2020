/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;

/**
 * This is a demo program showing the use of the DifferentialDrive class.
 * Runs the motors with arcade steering.
 */
public class Robot extends TimedRobot {
  WPI_TalonSRX talon = new WPI_TalonSRX(5);
  Joystick joy =new Joystick(0);
  /* String for output */
  StringBuilder _sb = new StringBuilder();

  /* Loop tracker for prints */
  int _loops = 0;
  
  public void robotInit() {
    talon.configFactoryDefault();
    talon.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder,
    Constants.kPIDLoopIdx, 
    Constants.kTimeoutMs);
    

		talon.configNominalOutputForward(0, Constants.kTimeoutMs);
		talon.configNominalOutputReverse(0, Constants.kTimeoutMs);
		talon.configPeakOutputForward(1, Constants.kTimeoutMs);
		talon.configPeakOutputReverse(-1, Constants.kTimeoutMs);

		/* Config the Velocity closed loop gains in slot0 */
		talon.config_kF(Constants.kPIDLoopIdx, Constants.kGains_Velocit.kF, Constants.kTimeoutMs);
		talon.config_kP(Constants.kPIDLoopIdx, Constants.kGains_Velocit.kP, Constants.kTimeoutMs);
		talon.config_kI(Constants.kPIDLoopIdx, Constants.kGains_Velocit.kI, Constants.kTimeoutMs);
		talon.config_kD(Constants.kPIDLoopIdx, Constants.kGains_Velocit.kD, Constants.kTimeoutMs);
    
   
  }

  @Override
  public void teleopPeriodic() {
		  /* Get Talon/Victor's current output percentage */
		  double motorOutput = talon.getMotorOutputPercent();
    		/* Prepare line to print */
		  _sb.append("\tout:");
		  /* Cast to int to remove decimal places */
		  _sb.append((int) (motorOutput * 100));
		  _sb.append("%");	// Percent

		  _sb.append("\tspd:");
		  _sb.append(talon.getSelectedSensorVelocity(Constants.kPIDLoopIdx));
		  _sb.append("u"); 	// Native units
      double leftYstick = -1 * joy.getY();

			/**
			 * Convert 500 RPM to units / 100ms.
			 * 4096 Units/Rev * 500 RPM / 600 100ms/min in either direction:
			 * velocity setpoint is in units/100ms
       **/

      if(joy.getRawButton(1)){
        double targetV = leftYstick * 10.0 * 8192 / 600;
        talon.set(ControlMode.Velocity, targetV);

        	/* Append more signals to print when in speed mode. */
			  _sb.append("\terr:");
			  _sb.append(talon.getClosedLoopError(Constants.kPIDLoopIdx));
			  _sb.append("\ttrg:");
			  _sb.append(targetV);
      } else {
			talon.set(ControlMode.PercentOutput, leftYstick);
    }
    
            /* Print built string every 10 loops */
		if (++_loops >= 10) {
			_loops = 0;
			System.out.println(_sb.toString());
        }
    /* Reset built string */
		_sb.setLength(0);
    
  }

  public static class Gains {
    public final double kP;
    public final double kI;
    public final double kD;
    public final double kF;
    public final int kIzone;
    public final double kPeakOutput;
    
    public Gains(double _kP, double _kI, double _kD, double _kF, int _kIzone, double _kPeakOutput){
      kP = _kP;
      kI = _kI;
      kD = _kD;
      kF = _kF;
      kIzone = _kIzone;
      kPeakOutput = _kPeakOutput;
    }
  }

  static class Constants {
    /**
     * Which PID slot to pull gains from. Starting 2018, you can choose from
     * 0,1,2 or 3. Only the first two (0,1) are visible in web-based
     * configuration.
     */
    public static final int kSlotIdx = 0;
  
    /**
     * Talon SRX/ Victor SPX will supported multiple (cascaded) PID loops. For
     * now we just want the primary one.
     */
    public static final int kPIDLoopIdx = 0;
  
    /**
     * Set to zero to skip waiting for confirmation, set to nonzero to wait and
     * report to DS if action fails.
     */
      public static final int kTimeoutMs = 30;
  
    /**
     * PID Gains may have to be adjusted based on the responsiveness of control loop.
       * kF: 1023 represents output value to Talon at 100%, 7200 represents Velocity units at 100% output
       * 
     * 	                                    			  kP   kI   kD   kF          Iz    PeakOut */
      public final static Gains kGains_Velocit = new Gains( 0.25, 0.001, 20, 1023.0/7200.0,  300,  1.00);
  }
}
