package frc.robot;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoMode;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.CvSink;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Camera {
    private UsbCamera camera1;
    public void initialize(){
       // forwardCam = new UsbCamera("cam0",0);
       //   forwardCam.setBrightness(5);
        //   forwardCam.setResolution(640,480);
        camera1 = CameraServer.getInstance().startAutomaticCapture(0);
        camera1.setResolution(640,480);
        camera1.setBrightness(10);
        camera1.setFPS(30);


    }

}
