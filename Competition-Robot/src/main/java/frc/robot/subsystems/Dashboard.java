package frc.robot.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import static frc.robot.Constants.*;
import frc.robot.commands.AutoCommand;

public class Dashboard extends SubsystemBase {
        private final HWheelSubsystem hwheelSubsystem;
        private final LimelightSubsystem limelightSubsystem;
    public Dashboard(HWheelSubsystem hwheel, LimelightSubsystem lime){
        hwheelSubsystem = hwheel;
        limelightSubsystem = lime;
        SmartDashboard.putBoolean("GreenLight", true);
    }
    @Override
    public void periodic() {
      SmartDashboard.putBoolean("hwheel", hwheelSubsystem.isHwheelDown());
      SmartDashboard.putNumber("Backup Angle", kBackupAngle);
      SmartDashboard.putNumber("HeightAngle", kLLHeightAngle);
      SmartDashboard.putNumber("RPMs", kShootRPM);
      SmartDashboard.putNumber("Limelight", limelightSubsystem.getPipeline());
    }
    public double getBackupAngle(){
        return SmartDashboard.getNumber("Backup Angle", kBackupAngle);
    }
    public double getHeightAngle(){
        return SmartDashboard.getNumber("Height Angle", kLLHeightAngle);
    }
    public double getShootRPM(){
        return SmartDashboard.getNumber("RPMs", kShootRPM);
    }
}
