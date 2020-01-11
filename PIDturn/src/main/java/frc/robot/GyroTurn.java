/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import com.kauailabs.navx.frc.AHRS;




/**
 * This is a demo program showing the use of the DifferentialDrive class.
 * Runs the motors with arcade steering.
 */
public class GyroTurn {
    double P = 1;
    double I = 68;
    double D = 0;
    int integral, previous_error, setpoint = 0;
    DifferentialDrive robotDrive;
    AHRS gyro;
    private double rcw;


    public GyroTurn(final AHRS gyro, final DifferentialDrive robotDrive) {
        this.gyro = gyro;
        this.robotDrive = robotDrive;
    }

    public void setSetpoint(final int setpoint) {
        this.setpoint = setpoint;
    }

    public void PID() {
        final double error = setpoint - gyro.getAngle(); // Error = Target - Actual
        this.integral += (error * .02); // Integral is increased by the error*time (which is .02 seconds using normal
                                        // IterativeRobot)
        final double derivative = (error - this.previous_error) / .02;
        this.rcw = P*error + I*this.integral + D*derivative;
    }

    public void execute()
    {
        PID();
       // System.out.println("rcw - " + rcw);
       // System.out.println("angle - " + gyro.getAngle());
        rcw = Math.max(-.5, rcw);
        rcw = Math.min(.5, rcw);
        robotDrive.arcadeDrive(0, rcw);
    }
}
