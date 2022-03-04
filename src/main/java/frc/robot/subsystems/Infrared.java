package frc.robot.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.I2C;

import com.revrobotics.Rev2mDistanceSensor;
import com.revrobotics.Rev2mDistanceSensor.Port;

public class Infrared {

    /* Variables */
    private Rev2mDistanceSensor infraredSensor;

    private int infraredSensorPort;
    
    private I2C multiplexer;

    public Infrared() {

        /* TODO: This port is a placeholder, the range is 0-7 */
        infraredSensorPort = 4;

        multiplexer = new I2C(I2C.Port.kOnboard, 0x70);

        multiplexer.write(0x70, 1 << infraredSensorPort);
        infraredSensor = new Rev2mDistanceSensor(Port.kOnboard);

        /**
        * Before measurements can be read from the sensor, setAutomaticMode(true)
        * must be called. This starts a background thread which will periodically
        * poll all enabled sensors and store their measured range.
        */
        infraredSensor.setAutomaticMode(true);

    }

    public void periodic() {

        /* TODO: change the distance at which the sensor detects a ball, it's currently a placeholder */
        /* Gets the range of the sensors and stores them in a variable */
        multiplexer.write(0x70, 1 << infraredSensorPort);
        double distance = infraredSensor.getRange();

        /* TODO: These names (infrared0/infrared1 and Present/Missing) suck. Change them */
        /* Lets the driver know via SmartDashboard if there is a ball in a specific spot */
        if (distance <= 1) {
            SmartDashboard.putString("infraredUndefined", "Present");
        }
        else {
            SmartDashboard.putString("infraredUndefined", "Missing");
        }

    }

}
