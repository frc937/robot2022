// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.PIDSubsystem;
import frc.robot.Constants;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.Encoder;

public class Shooter extends PIDSubsystem {

    private CANSparkMax flywheel;
    private CANSparkMax controlWheel;

    private Encoder flywheelEncoder;

    /** Creates a new Shooter. */
    public Shooter() {
        super(new PIDController(Constants.kFLYWHEEL_P, Constants.kFLYWHEEL_I, Constants.kFLYWHEEL_D));

        flywheel = new CANSparkMax(Constants.ID_SPARKMAX_FLYWHEEL, MotorType.kBrushed);
        controlWheel = new CANSparkMax(Constants.ID_SPARKMAX_CONTROL_WHEEL, MotorType.kBrushed);

        flywheelEncoder = new Encoder(Constants.DIO_PIN_FLYWHEEL_ENCODER_0, Constants.DIO_PIN_FLYWHEEL_ENCODER_1);

        setSetpoint(Constants.FLYWHEEL_SETPOINT);
    }

    @Override
    public void useOutput(double output, double setpoint) {
        flywheel.setVoltage(output + setpoint);
    }

    @Override
    public double getMeasurement() {
        return flywheelEncoder.getRate();
    }

    public void runFeeder() {
        controlWheel.set(Constants.CONTROL_WHEEL_SPEED);
    }

    public void stopFeeder() {
        controlWheel.set(0);
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }
}
