/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SPI;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants.  This class should not be used for any other purpose.  All constants should be
 * declared globally (i.e. public static).  Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {

    public static Joystick joystick = new Joystick(0);
    public static XboxController xbox = new XboxController(1);
    public static AHRS gyro = new AHRS(SPI.Port.kMXP);

    //Buttons
    public static final ButtonTuple bShoot = new ButtonTuple(joystick, 1);//1 (manual shoot)
    public static final ButtonTuple bTarget = new ButtonTuple(joystick, 2);//2 (auto target and shoot)
    public static final ButtonTuple bIntake = new ButtonTuple(joystick, 3);//3 (hold)
    public static final ButtonTuple bRaiseLift = new ButtonTuple(joystick, 10);//10
    public static final ButtonTuple bLowerLift = new ButtonTuple(joystick, 9);//9
    public static final ButtonTuple bBalanceAuto = new ButtonTuple(joystick, 4);//4(auto level)
    public static final ButtonTuple bBalanceLeft = new ButtonTuple(joystick, 5);//5 (manual level left)
    public static final ButtonTuple bBalanceRight = new ButtonTuple(joystick, 6);//6 (manual level right)
    public static final ButtonTuple bLowerHWheel = new ButtonTuple(joystick, 11);//11
    public static final ButtonTuple bRaiseHWheel = new ButtonTuple(joystick, 12);//12
    public static final ButtonTuple bRaiseTrolley = new ButtonTuple(joystick, 8);//8
    public static final ButtonTuple bLowerTrolley = new ButtonTuple(joystick, 7);//7


    //Speed Controllers
    public static final int scDriveRight1 = 1;
    public static final int scDriveRight2 = 2;
    public static final int scDriveLeft2 = 3;
    public static final int scDriveLeft1 = 4;
    public static final int scHWheel = 5;
    public static final int scTrolley = 6;
    public static final int scIntake = 9;
    public static final int scShooter = 8;
    public static final int scLift = 7;

    //Sensors
    public static final int diBeamBreak = 5;

    //Pneumatics
    public static final int pCompressor = 11;

    public static final int psLiftFwd = 1;
    public static final int psLiftRev = 0;
    public static final int psHWheelFwd = 2;
    public static final int psHWheelRec = 3;
    public static final int psClimbDead = 4;
    public static final int psClimbRev = 5;
    public static final int psClimbFwd = 6;
    public static final int psLClimbRev = 7;



    //Commands
    //Target
    public static final double kTargetTurnP = 0.050;
    public static final double kTargetTurnI = 0.0025;
    public static final double kTargetTurnD = 0.0065;

    public static final double kTargetDriveP = 0.050;
    public static final double kTargetDriveI = 0.0025;
    public static final double kTargetDriveD = 0.0065;

    public static final double kLLHeightAngle = 21.7;
    public static final double kTargetTurn = 0.6;
    public static final double kTolerance = 0.3;

    //Shooter, Intake & Conveyor
    public static final double kIntakeSpeed = 1.0;
    public static final double kShootSpeed = 1.0;
    public static final double kShootRPM = 100.0;
    public static final double kInputDelay = 35;
    public static final double kConSpeed = 0.5;
    public static final double kTrolley = .2;


    //Talon speed control
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
    //Limit the value to the abs(max)
    public static double limitSpeed(double value, double max){
        double normedMax = Math.abs(max);
        double output = value;
        output = Math.min(output, normedMax);
        output = Math.max(output, -1 * normedMax);

        if(output != value){
            System.out.println("Speed limited: " + output);
        }

        return output;
    }


}