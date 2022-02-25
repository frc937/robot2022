package frc.robot.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.I2C;

import com.revrobotics.*;
import com.revrobotics.Rev2mDistanceSensor;
import com.revrobotics.Rev2mDistanceSensor.Port;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;

public class Infrared {
    
    /* Variables */
    private Rev2mDistanceSensor distMXP;

    // Putting I2C before Port here seems to break stuff, although it may be needed
    private Rev2mDistanceSensor infraredSensor0;
    private Rev2mDistanceSensor infraredSensor1;

    // These variables have currently replaced the previous to follow an example. 
    private int infraredSensorPort0;
    private int infraredSensorPort1;
    private I2C multiplexer;

    public Infrared() {

        /* TODO: These ports are placeholders, the range is 0-7 */
        infraredSensorPort0 = 4;
        infraredSensorPort1 = 2;

        multiplexer = new I2C(I2C.Port.kOnboard, 0x70);

        multiplexer.write(0x70, 1 << infraredSensorPort0);
        infraredSensor0 = new Rev2mDistanceSensor(Port.kOnboard);
        multiplexer.write(0x70, 1 << infraredSensorPort1);
        infraredSensor1 = new Rev2mDistanceSensor(Port.kOnboard);

        addChild("infrared0",infraredSensor0);
        addChild("infrared1",infraredSensor1);

        /**
        * Before measurements can be read from the sensor, setAutomaticMode(true)
        * must be called. This starts a background thread which will periodically
        * poll all enabled sensors and store their measured range.
        */
        infraredSensor0.setAutomaticMode(true);
        infraredSensor1.setAutomaticMode(true);

    }

    public void periodic() {

        /* TODO: change the distance at which the sensor detects a ball, it's currently a placeholder */
        /* Gets the range of the sensors and stores them in a variable */
        multiplexer.write(0x70, 1 << infraredSensorPort0);
        double distance0 = infraredSensor0.getRange();
        multiplexer.write(0x70, 1 << infraredSensorPort1);
        double distance1 = infraredSensor1.getRange();

        /* TODO: These names (infrared0/infrared1 and Present/Missing) suck. Change them */
        /* Lets the driver know via SmartDashboard if there is a ball in a specific spot */
        if (distance0 <= 1) {
            SmartDashboard.putString("infrared0", "Present");
        }
        else {
            SmartDashboard.putString("infrared0", "Missing");
        }
    
        if (distance1 <= 1) {
            SmartDashboard.putString("infrared1", "Present");
        }
        else {
            SmartDashboard.putString("infrared1", "Missing");
        }

    }

}
