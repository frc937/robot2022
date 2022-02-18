package frc.robot.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.I2C;

import com.revrobotics.*;
import com.revrobotics.Rev2mDistanceSensor;
import com.revrobotics.Rev2mDistanceSensor.Port;

public class Infared {
    
    /* Variables */
    private Rev2mDistanceSensor distMXP;

    // Putting I2C before Port here seems to break stuff, although it may be needed.
    private Port infraredSensorPort0;
    private Port infraredSensorPort1;
    private Rev2mDistanceSensor infraredSensor0;
    private Rev2mDistanceSensor infraredSensor1;

    public Infared() {

        // Putting I2C before Port here seems to break stuff, although it may be needed.
        /* TODO: These ports are not seperated and do need to be seperated */
        infraredSensorPort0 = Port.kOnboard;
        infraredSensorPort1 = Port.kOnboard;
        /* In the rev docs for ir sensors, this variable is labeled distOnboard */
        infraredSensor0 = new Rev2mDistanceSensor(infraredSensorPort0);
        infraredSensor1 = new Rev2mDistanceSensor(infraredSensorPort1);

        /* TODO: This needs to be put in a better spot, currently it's in a placeholder spot */
        /**
        * Rev 2m distance sensor can be initialized with the Onboard I2C port
        * or the MXP port. Both can run simultaneously.
        */
        distMXP = new Rev2mDistanceSensor(Port.kMXP);

        /* TODO: This needs to be put in a better spot, currently it's in a placeholder spot */
        /**
        * Before measurements can be read from the sensor, setAutomaticMode(true)
        * must be called. This starts a background thread which will periodically
        * poll all enabled sensors and store their measured range.
        */
        infraredSensor0.setAutomaticMode(true);
        infraredSensor1.setAutomaticMode(true);

    }

    public double infraredDist() {

        return infraredSensor0.getRange();
        //return infraredSensor1.getRange();


    }

}

