package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.DigitalInput;
import static frc.robot.Constants.*;
import com.ctre.phoenix.motorcontrol.can.*;
import com.ctre.phoenix.motorcontrol.*;


public class ShooterSubsystem extends SubsystemBase {
  private DigitalInput beamBreakSensor = new DigitalInput(diBeamBreak);

  private final WPI_TalonSRX shooterMotor = new WPI_TalonSRX(scShooter);
  private final WPI_TalonSRX intakeMotor = new WPI_TalonSRX(scIntake);
  private final WPI_TalonSRX conveyorMotor = new WPI_TalonSRX(scLift);

  private static final int kMaxAmps = 40;
  private int brokenIterationCount = 0;
  private int pov = joystick.getPOV(0);
  private boolean shootMode = false;
  private boolean beamBroken = false;

  private int counter = 0;

  public ShooterSubsystem() {
    shooterMotor.configFactoryDefault();
    intakeMotor.configFactoryDefault();
    conveyorMotor.configFactoryDefault();

    shooterMotor.setInverted(true);
    intakeMotor.setInverted(true);
    conveyorMotor.setInverted(true);

    //TODO - Is it better to add explicit limits or to disable completely
    shooterMotor.enableCurrentLimit(false);

    shooterMotor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder,
            kPIDLoopIdx,
            kTimeoutMs);

    /* Config the Velocity closed loop gains in slot0 */
    shooterMotor.config_kF(kPIDLoopIdx, 0, kTimeoutMs);
    shooterMotor.config_kP(kPIDLoopIdx, kShooterP, kTimeoutMs);
    shooterMotor.config_kI(kPIDLoopIdx, kShooterI, kTimeoutMs);
    shooterMotor.config_kD(kPIDLoopIdx, kShooterD, kTimeoutMs);
    shooterMotor.configPeakOutputForward(kShooterPeakOutput, kTimeoutMs);
    shooterMotor.configPeakOutputReverse(-kShooterPeakOutput, kTimeoutMs);
    shooterMotor.configClosedloopRamp(kRampRate);
  }

  public boolean isFastEnough() {
    int sensorVelocity = shooterMotor.getSelectedSensorVelocity(kPIDLoopIdx);

    // Note - getSelectedSensorVelocity returns 0 when encoder disconnected
    // If getSelectedSensorVelocity returns 0 for 5?? loops then just return true
    if(sensorVelocity == 0 && counter++ > 5){
      return true;
    } else if (sensorVelocity > 0){
      // Only reset the counter if the sensor is working
      counter = 0;
    }

    return Math.abs((kShootRPM - sensorVelocity) /  kShootRPM) < kShootRPMThreshold;
  }

  @Override
  public void periodic() {
    pov = joystick.getPOV(0);

    //Dpad top three points
    if (pov == 45 || pov == 0 || pov == 315 || xbox.getYButtonPressed()) {
      conveyorMotor.set(kConSpeed);
    }
    //Dpad bottom three points
    else if (pov == 180 || pov == 135 || pov == 225 || xbox.getBButtonPressed()) {
      conveyorMotor.set(-kConSpeed);
    }
    else if(!shootMode) {
      if (isBroken()) {
        conveyorMotor.set(kConSpeed);
        beamBroken = true;
      } else if (beamBroken && brokenIterationCount++ > kInputDelay) {  // Not Broken
        conveyorMotor.set(0);
        brokenIterationCount = 0;
        beamBroken = false;
      } else if (!beamBroken) {   // Not in shoot mode and we are not coming out of beam break operation
        conveyorMotor.set(0);
      }
    }
    else{  // Not in shoot mode and no pov value
      conveyorMotor.set(0);
    }
  }

  public void setShootMode(boolean b){
    shootMode = b;
  }

  boolean isBroken(){
    return !beamBreakSensor.get();
  }

  boolean isNotBroken(){
    return beamBreakSensor.get();
  }

  public void stopShooter(){
    shooterMotor.set(0);
  }

  //Min 0.0, Max = 1.0
  public double spinShoot(){
    //100 is full speed
    //10 is RPM
    //8192 is count per full revolution
    //600 is units per minute
    shooterMotor.set(ControlMode.Velocity, kShootRPM);
   // shooterMotor.set(1);
    return getShooterCurrent();
  }

  public double getShooterCurrent(){
    return shooterMotor.getSupplyCurrent();
  }

  public void startIntake(){
    intakeMotor.set(kIntakeSpeed);
  }

  public void stopIntake() {
    intakeMotor.set(0);
  }

  public void reverseIntake() { intakeMotor.set(-kIntakeSpeed); }

  public void spinConveyor() {
    conveyorMotor.set(kConSpeed);
  }

  public void stopConveyor() {
    conveyorMotor.set(0);
  }
}



