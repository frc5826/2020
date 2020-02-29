package frc.robot;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoMode;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.CvSink;
import edu.wpi.first.cameraserver.CameraServer;
public class Camera {
    UsbCamera forwardCam;
    CameraServer server;
    public void initialize(){
        forwardCam = new UsbCamera("cam0",0);
        forwardCam.setBrightness(5);
        forwardCam.setResolution(640,480);

        server = CameraServer.getInstance();
        server.startAutomaticCapture(forwardCam);

    }
    public void excecute(){

    }
}
