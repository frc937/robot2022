package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.cscore.UsbCamera;
import edu.wpi.first.cscore.VideoSource.ConnectionStrategy;

public class Camera extends SubsystemBase{

    private int port;
    private UsbCamera camera;

    public Camera(int port) {
        this.port = port;

    }

    public void startCamera() {
        camera = CameraServer.startAutomaticCapture(this.port);

        camera.setConnectionStrategy(ConnectionStrategy.kKeepOpen);

        System.out.println("Camera started");

    }

}