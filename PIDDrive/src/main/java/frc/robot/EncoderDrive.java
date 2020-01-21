package frc.robot;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class EncoderDrive {
    private static final int clicksPerCM = 1;

    double P = 0;
    double I = 0;
    double D = 0.0;
    double previous_error, integral = 0;
    int setpoint = 0;
    DifferentialDrive robotDrive;
    Encoder leftEncoder;
    Encoder rightEncoder;
    

    public EncoderDrive(Encoder leftEncoder, Encoder rightEncoder, DifferentialDrive robotDrive){
        this.leftEncoder = leftEncoder;
        this.rightEncoder = rightEncoder;
        this.robotDrive = robotDrive;
    }

    public void setSetpoint(final int setpoint) {
        this.setpoint = setpoint;
    }

    public void PID() {
        
    }

}
