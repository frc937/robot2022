package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class IndexWheel extends SubsystemBase {

    private CANSparkMax controlWheel;

    /** Creates a new Index Wheel. */
    public IndexWheel() {
        controlWheel = new CANSparkMax(Constants.ID_SPARKMAX_CONTROL_WHEEL, MotorType.kBrushed);
    }

    /**
     * Turns on the feeder motor to the speed set in {@link frc.robot.Constants} IF the ball loaded into the robot is the same color as our alliance color.
     */
    public void runFeeder() {
        controlWheel.set(Constants.CONTROL_WHEEL_SPEED);
    }

    /**
     * Stops the feeder motor.
     */
    public void stopFeeder() {
        controlWheel.set(0);
    }

    /**
     * Subsystem periodic, called once per scheduler run. Not used here.
     */
    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }
    
}
