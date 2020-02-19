package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.DigitalInput;
import static frc.robot.Constants.*;


public class ShooterSubsystem extends SubsystemBase {
  private DigitalInput beamBreakSensor = new DigitalInput(diBeamBreak);

  private final WPI_TalonSRX shooterMotor= new WPI_TalonSRX(scShooter);
  public final WPI_TalonSRX intakeMotor = new WPI_TalonSRX(scIntake);
  public final WPI_TalonSRX conveyorMotor = new WPI_TalonSRX(scLift);

  private static final int kMaxAmps = 40;
  private int brokenIterationCount = 0;
  int pov = joystick.getPOV(0);

  public ShooterSubsystem() {
    shooterMotor.setInverted(true);
    intakeMotor.setInverted(true);
    conveyorMotor.setInverted(true);

    //TODO - Is it better to add explicit limits or to disable completely
    shooterMotor.enableCurrentLimit(false);

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
    //Dpad top three points
    if(pov == 45 || pov == 0 || pov == 315){
      conveyorMotor.set(kConSpeed);
    }
    //Dpad bottom three points
    else if(pov == 180 || pov == 135|| pov == 225){
      conveyorMotor.set(-kConSpeed);
    } else {
      if(isBroken()){
        conveyorMotor.set(kConSpeed);
        //System.out.println("Beam is broken");
      } else if(brokenIterationCount++ > kInputDelay){
        conveyorMotor.set(0);
        //System.out.println("Beam is NOT broken");
        brokenIterationCount = 0;
      }
    }

  }

  boolean isBroken(){
    return !beamBreakSensor.get();
  }

  boolean isNotBroken(){
    return beamBreakSensor.get();
  }

  public void stopShooter(){
    shoot(-1);
  }

  //Min 0.0, Max = 1.0
  public double shoot(double percent){
    if(percent > 0){
      System.out.println("percent " + percent);
      shooterMotor.set(percent);
    }
    else {
      shooterMotor.set(0);
    }

    return getShooterCurrent();
  }

  public double getShooterCurrent(){
    return shooterMotor.getSupplyCurrent();
  }
}



