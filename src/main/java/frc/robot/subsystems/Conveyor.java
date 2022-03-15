package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Conveyor extends SubsystemBase {

    /* Variables */
    private CANSparkMax conveyor;

    /** Creates a new Conveyor. */
    public Conveyor() {
        conveyor = new CANSparkMax(Constants.ID_SPARKMAX_CONVEYOR, MotorType.kBrushed);

        conveyor.setInverted(false);
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

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }
}
