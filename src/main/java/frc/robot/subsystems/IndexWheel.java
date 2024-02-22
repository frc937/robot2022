package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.motorcontrol.Talon;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class IndexWheel extends SubsystemBase {

    private Talon indexWheel;

    /** Creates a new Index Wheel. */
    public IndexWheel() {
        indexWheel = new Talon(Constants.ID_TALON_CONTROL_WHEEL);

        indexWheel.setInverted(true);
    }

    /**
     * Turns on the index motor to the speed set in {@link frc.robot.Constants} IF the ball loaded into the robot is the same color as our alliance color.
     */
    public void runIndex() {
        indexWheel.set(Constants.CONTROL_WHEEL_SPEED);
    }

    /**
     * Stops the index motor.
     */
    public void stopIndex() {
        indexWheel.set(0);
    }

    /**
     * Subsystem periodic, called once per scheduler run. Not used here.
     */
    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }
    
}
