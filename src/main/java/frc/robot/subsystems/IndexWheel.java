package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class IndexWheel extends SubsystemBase {

    private CANSparkMax indexWheel;

    /** Creates a new Index Wheel. */
    public IndexWheel() {
        indexWheel = new CANSparkMax(Constants.ID_SPARKMAX_CONTROL_WHEEL, MotorType.kBrushed);

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
