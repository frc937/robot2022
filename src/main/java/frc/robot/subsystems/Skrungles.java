package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Skrungles extends SubsystemBase {

    /* Variables */
    private CANSparkMax skrungles;

    /** Creates a new Wibbly Skrungles. */
    public Skrungles() {
        skrungles = new CANSparkMax(Constants.ID_SPARKMAX_SKRUNGLES, MotorType.kBrushed);

        skrungles.setInverted(false);
    }

    /**
     * Runs the Wibbly Skrungles.
     */
    public void runSkrungles() {
        skrungles.set(Constants.SKRUNGLE_SPEED);
    }

    /**
     * Stops the Wibbly Skrungles.
     */
    public void stopSkrungles() {
        skrungles.set(0);
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }
}
