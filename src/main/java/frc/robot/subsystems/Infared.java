package frc.robot.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.revrobotics.*;
import com.revrobotics.Rev2mDistanceSensor.Port;

public class Infared {
    
    /* Variables */
    private Rev2mDistanceSensor distOnboard;
    private Rev2mDistanceSensor distMXP;

    public Infared() {

        colorSensorPort = I2C.Port.kOnboard;

        colorSensor = new ColorSensorV3(colorSensorPort);

        /* TODO: This needs to be put in a better spot, currently it's in a placeholder spot */
        /**
        * Rev 2m distance sensor can be initialized with the Onboard I2C port
        * or the MXP port. Both can run simultaneously.
        */
        distOnboard = new Rev2mDistanceSensor(Port.kOnboard);
        distMXP = new Rev2mDistanceSensor(Port.kMXP);

        getRange();


        /* TODO: This needs to be put in a better spot, currently it's in a placeholder spot */
        /**
        * Before measurements can be read from the sensor, setAutomaticMode(true)
        * must be called. This starts a background thread which will periodically
        * poll all enabled sensors and store their measured range.
        */
        distOnboard.setAutomaticMode(true);

        

    }

}

