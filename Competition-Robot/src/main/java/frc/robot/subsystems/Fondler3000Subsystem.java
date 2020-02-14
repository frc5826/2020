package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.DigitalInput;



public class Fondler3000Subsystem extends SubsystemBase {
  private DigitalInput beamBreakSensor = new DigitalInput(5);

  private final WPI_TalonSRX shooterMotor= new WPI_TalonSRX(8);
  public final WPI_TalonSRX intakeMotor = new WPI_TalonSRX(9);
  public final WPI_TalonSRX conveyorMotor = new WPI_TalonSRX(7);

  private static final int kMaxAmps = 40;

  public Fondler3000Subsystem() {
    //TODO - Is it better to add explicit limits or to disable completely
    shooterMotor.enableCurrentLimit(true);

    /*
    CIM specs
    https://www.vexrobotics.com/217-2000.html#Docs_&_Downloads

    Breaker specs
    https://tinyurl.com/t4o23zu

    Good docs on how limiting works
    https://phoenix-documentation.readthedocs.io/en/latest/ch13_MC.html
     */

    //CIM stall @ 133A
    //133A/40A -> 332.5% ~> 400% overload -> 0.3 - 0.6 trip
    //Rounding amps and time down to be safe
    shooterMotor.configPeakCurrentDuration(300);
    shooterMotor.configPeakCurrentLimit(130);

    //Shouldn't trip at 100%
    shooterMotor.configContinuousCurrentLimit(kMaxAmps);
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
    return !beamBreakSensor.get();
  }

  public void stopShooter(){
    shoot(-1);
  }

  public void shoot(double percent){
    if(percent > 0){
      if(percent > 100){
        System.out.println("Shoot was supplied with a percent larger than 100% (" + percent + "). Setting to 100%.");
        percent = 100;
      }
      shooterMotor.set(ControlMode.Current, percent * kMaxAmps);
    }
    else {
      shooterMotor.set(ControlMode.Current, 0);
    }
  }

  public double getShooterCurrent(){
    return shooterMotor.getSupplyCurrent();
  }

  // This method will be called once per scheduler run
}



