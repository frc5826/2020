/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;


public class BallIntakeSubsystem extends SubsystemBase {
    public final WPI_TalonSRX intakeMotor= new WPI_TalonSRX(6);


    public BallIntakeSubsystem() {

    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run

        intakeMotor.set(0.5);
    }

}
