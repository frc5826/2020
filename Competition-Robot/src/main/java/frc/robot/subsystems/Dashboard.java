package frc.robot.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

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
      limelightSubsystem.setGreenLight(SmartDashboard.getBoolean("GreenLight", true));
    }
}
