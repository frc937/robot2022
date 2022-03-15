package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMax.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Flywheel extends SubsystemBase {

    private CANSparkMax flywheel;
    private CANSparkMax controlWheel;

    /*private RelativeEncoder flywheelEncoder;*/

    private SparkMaxPIDController flywheelPID;

    /** Creates a new Shooter. */
    public Shooter() {
        flywheel = new CANSparkMax(Constants.ID_SPARKMAX_FLYWHEEL, MotorType.kBrushed);
        controlWheel = new CANSparkMax(Constants.ID_SPARKMAX_CONTROL_WHEEL, MotorType.kBrushed);

        /*flywheelEncoder = flywheel.getEncoder();*/

        flywheelPID = flywheel.getPIDController();

        /* This may need to set more constants, like setIZone(), setFF(), or setOutputRange(). idk for now. */
        flywheelPID.setP(Constants.kFLYWHEEL_P);
        flywheelPID.setI(Constants.kFLYWHEEL_I);
        flywheelPID.setD(Constants.kFLYWHEEL_D);
        flywheel.burnFlash();
    }

    public void setVelocity(double velocity) {
        flywheelPID.setReference(velocity, ControlType.kVelocity);
    }

    public void testFlywheel() {
        flywheel.set(0.7);
    }

    public void stopFlywheel() {
        flywheel.set(0);
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
     * Safety method to bring ALL motors controlled by this subsystem to a complete stop.
     */
    public void stopAll() {
        flywheel.set(0);
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
