/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;
import edu.wpi.first.wpilibj.Joystick;
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
    public static AHRS gyro = new AHRS(SPI.Port.kMXP);

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