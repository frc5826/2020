/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.DigitalInput;

public class BallLifterSubsystem extends SubsystemBase {
    private DigitalInput beamBreakSensor = new DigitalInput(5);


    public BallLifterSubsystem() {

    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run

        if(isBroken()){
            //motor.set(1);
            System.out.println("Beam is broken");
          } else {
            //motor.set(0);
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
