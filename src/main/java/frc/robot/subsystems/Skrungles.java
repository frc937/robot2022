package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Skrungles extends SubsystemBase {

    /* Variables */
    private CANSparkMax skrungles;
    private CANSparkMax conveyor;

    /** Creates a new Intake. */
    public Skrungles() {
        skrungles = new CANSparkMax(Constants.ID_SPARKMAX_SKRUNGLES, MotorType.kBrushed);
        conveyor = new CANSparkMax(Constants.ID_SPARKMAX_CONVEYOR, MotorType.kBrushed);

        skrungles.setInverted(false);
        conveyor.setInverted(false);
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

    /**
     * Runs the conveyor forward, to move balls further into the robot and closer to the shooter.
     */
    public void runConveyorForward() {
        conveyor.set(Constants.CONVEYOR_SPEED);
    }

    /**
     * Runs the conveyor backwards, to move balls out of the robot.
     */
    public void runConveyorReverse() {
        conveyor.set(Constants.CONVEYOR_SPEED * -1);
    }

    /**
     * Stops the conveyor.
     */
    public void stopConveyor() {
        conveyor.set(0);
    }

    /**
     * Safety method that stops all motors associated with this subsystem.
     */
    public void stopAll() {
        skrungles.set(0);
        conveyor.set(0);
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }
}
