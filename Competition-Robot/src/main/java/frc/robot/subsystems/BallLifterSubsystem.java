/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.DigitalInput;

public class BallLifterSubsystem extends SubsystemBase {
    private DigitalInput beamBreakSensor = new DigitalInput(5);
    public final WPI_TalonSRX lifterMotor= new WPI_TalonSRX(7);


    public BallLifterSubsystem() {

    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run

        if(isBroken()){
            lifterMotor.set(0.5);
            //System.out.println("Beam is broken");
          } else {
            lifterMotor.set(0);
            //System.out.println("Beam is NOT broken");
          }
    }

    boolean isBroken(){
        return beamBreakSensor.get();
    }

    boolean isNotBroken(){
        return 
        !beamBreakSensor.get();
    }

}
