// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

/**
 * Shooter subsystem. Handles the shooter we use to launch balls into the target.
 * 
 * <p>Is a PIDSubsystem because we need veolocity PID handled by WPI on the flywheel to ensure it always runs at a constant speed so we're always on target.
 */
public class Shooter extends SubsystemBase {

    private CANSparkMax flywheel;
    private CANSparkMax controlWheel;

    private CANEncoder flywheelEncoder;

    private CANPIDController flywheelPID;

    /** Creates a new Shooter. */
    public Shooter() {
        flywheel = new CANSparkMax(Constants.ID_SPARKMAX_FLYWHEEL, MotorType.kBrushed);
        controlWheel = new CANSparkMax(Constants.ID_SPARKMAX_CONTROL_WHEEL, MotorType.kBrushed);

        flywheelEncoder = flywheel.getEncoder();

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
