package frc.robot;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

class LimeLight {
    NetworkTable table;
    DifferentialDrive robotDrive;

    LimeLight(DifferentialDrive robotDrive) {
        this.robotDrive = robotDrive;
    }

    public void start() {
        table = NetworkTableInstance.getDefault().getTable("limelight");
        NetworkTableEntry pipeline = table.getEntry("pipeline");
        pipeline.forceSetDouble(7);
    }

    public void follow() {
        NetworkTableEntry tx = table.getEntry("tx");
        NetworkTableEntry ta = table.getEntry("ta");
        NetworkTableEntry tv = table.getEntry("tv");

        double target = tv.getDouble(0.0);
        double x = tx.getDouble(0.0);
        double area = ta.getDouble(0.0);

        if (area < 1 && target == 1) {
            robotDrive.arcadeDrive(-0.55, x / Math.abs(x) * Math.min(Math.abs(x / 10), 0.5));
        } else if (area > 2 && target == 1) {
            robotDrive.arcadeDrive(0.55, x / Math.abs(x) * (Math.min(Math.abs(x / 10), 0.5)));
        } else {
            robotDrive.arcadeDrive(0, 0);
        }
    }

    public void target() {

    }
}