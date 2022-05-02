package frc.robot.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.I2C;

import com.revrobotics.Rev2mDistanceSensor;
import com.revrobotics.Rev2mDistanceSensor.Port;

public class Infrared {

    /* Variables */
    private Rev2mDistanceSensor infraredSensor;
    private Port infraredSensorPort;

    public Infrared() {

        /* TODO: This might not be the right port */
        infraredSensorPort = Port.kOnboard;
        infraredSensor = new Rev2mDistanceSensor(infraredSensorPort);

        /**
        * Before measurements can be read from the sensor, setAutomaticMode(true)
        * must be called. This starts a background thread which will periodically
        * poll all enabled sensors and store their measured range.
        */
        infraredSensor.setAutomaticMode(true);

    }

    public void periodic() {


        /* Gets the range of the sensors and stores them in a variable */
        double distance = infraredSensor.getRange();

        /* TODO: change the distance at which the sensor detects a ball, it's currently a placeholder */
        /* TODO: These names Present/Missing suck. infraredUndefined Change them */
        /* Lets the driver know via SmartDashboard if there is a ball in a specific spot */
        if (distance <= 1) {
            SmartDashboard.putString("infraredUndefined", "Present");
        }

        else {
            SmartDashboard.putString("infraredUndefined", "Missing");
        }

    }

}
