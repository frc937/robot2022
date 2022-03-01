// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.PIDSubsystem;
import frc.robot.Constants;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.Encoder;

/**
 * Shooter subsystem. Handles the shooter we use to launch balls into the target.
 * 
 * <p>Is a PIDSubsystem because we need veolocity PID handled by WPI on the flywheel to ensure it always runs at a constant speed so we're always on target.
 */
public class Shooter extends PIDSubsystem {

    private CANSparkMax flywheel;
    private CANSparkMax controlWheel;

    private Encoder flywheelEncoder;

    private SimpleMotorFeedforward flywheelFeedForward;

    /** Creates a new Shooter. */
    public Shooter() {
        super(new PIDController(Constants.kFLYWHEEL_P, Constants.kFLYWHEEL_I, Constants.kFLYWHEEL_D));

        /* A lot of this PID stuff will need tuning, like I'm not entirely sure we need a feedforward for this */
        flywheel = new CANSparkMax(Constants.ID_SPARKMAX_FLYWHEEL, MotorType.kBrushed);
        controlWheel = new CANSparkMax(Constants.ID_SPARKMAX_CONTROL_WHEEL, MotorType.kBrushed);

        flywheelEncoder = new Encoder(Constants.DIO_PIN_FLYWHEEL_ENCODER_0, Constants.DIO_PIN_FLYWHEEL_ENCODER_1);

        flywheelFeedForward = new SimpleMotorFeedforward(Constants.FLYWHEEL_kSVOLTS, Constants.FLYWHEEL_kVVOLT_SECONDS_PER_ROTATION);

        setSetpoint(Constants.FLYWHEEL_SETPOINT);
    }

    /**
     * Sets the flywheel. Takes an output and a setpoint cause PID.
     * 
     * <p> These params should be set from the values in {@link frc.robot.Constants}.
     * @param output The output used by PID
     * @param setpoint The PID setpoint
     */
    @Override
    public void useOutput(double output, double setpoint) {
        flywheel.set(output + flywheelFeedForward.calculate(setpoint));
    }

    /**
     * Returns the flywheel speed.
     * @return The current flywheel speed.
     */
    @Override
    public double getMeasurement() {
        return flywheelEncoder.getRate();
    }

    /**
     * Returns if the flywheel is at its setpoint, i.e. its target speed.
     * @return A boolean; true if the flywheel is at its setpoint
     */
    public boolean atSetpoint() {
        /* 
         * THIS NAME DOES NOT COMPLY WITH THIS PROJECT'S NAMING STANDARDS.
         * Unfortunately, we're stuck with it, because WPILib created the name automatically.
         */
        return m_controller.atSetpoint();
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
