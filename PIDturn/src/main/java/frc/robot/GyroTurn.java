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
//import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;




/**
 * This is a demo program showing the use of the DifferentialDrive class.
 * Runs the motors with arcade steering.
 */
public class GyroTurn {
    int P, I, D = 1;
    int integral, previous_error, setpoint = 0;
    ADXRS450_Gyro gyro;
    DifferentialDrive robotDrive;
    private double rcw;


    public GyroTurn(ADXRS450_Gyro gyro, DifferentialDrive robotDrive){
        this.gyro = gyro;
        this.robotDrive = robotDrive;
    }

    public void setSetpoint(int setpoint)
    {
        this.setpoint = setpoint;
    }

    public void PID(){
        double error = setpoint - gyro.getAngle(); // Error = Target - Actual
        this.integral += (error*.02); // Integral is increased by the error*time (which is .02 seconds using normal IterativeRobot)
        double derivative = (error - this.previous_error) / .02;
        this.rcw = P*error + I*this.integral + D*derivative;
    }

    public void execute()
    {
        PID();
        System.out.println("rcw-" + rcw);
        System.out.println("angle-" + gyro.getAngle());
        rcw = Math.min(.5, rcw);
        rcw = Math.max(-.5, rcw);

        robotDrive.arcadeDrive(0, rcw);
    }
}
