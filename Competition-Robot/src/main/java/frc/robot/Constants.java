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

    public static final int psLiftFwd = 0;
    public static final int psLiftRev = 1;
    public static final int psHWheelFwd = 2;
    public static final int psHWheelRec = 3;
    public static final int psRClimbFwd = 4;
    public static final int psRClimbRev = 5;
    public static final int psLClimbFwd = 6;
    public static final int psLClimbRev = 7;



    //Commands
    //Target
    public static final double kTargetP = 0.050;
    public static final double kTargetI = 0.0025;
    public static final double kTargetD = 0.0065;

    public static final double kLLHeightAngle = -.2;
    public static final double kTargetTurn = 0.6;
    public static final double kTargetErrorMargin = 0.2;

    //Shooter, Intake & Conveyor
    public static final double kIntakeSpeed = 1.0;
    public static final double kShootSpeed = 1.0;
    public static final double kInputDelay = 35;
    public static final double kConSpeed = 0.5;
    public static final double kTrolley = .2;


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