package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.DigitalInput;



public class Fondler3000Subsystem extends SubsystemBase {
  private DigitalInput beamBreakSensor = new DigitalInput(5);

  public final WPI_TalonSRX shooterMotor= new WPI_TalonSRX(8);
  public final WPI_TalonSRX intakeMotor = new WPI_TalonSRX(9);
  public final WPI_TalonSRX conveyorMotor = new WPI_TalonSRX(7);

  public Fondler3000Subsystem() {
    shooterMotor.setSafetyEnabled(false);
  }

  @Override
  public void periodic() {
    if(isNotBroken()){
      conveyorMotor.set(-0.5);
      //System.out.println("Beam is broken");
    } else {
      conveyorMotor.set(0);
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

    // This method will be called once per scheduler run
  }



