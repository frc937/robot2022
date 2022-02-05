// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.Encoder;

public class Shooter extends SubsystemBase {

    private CANSparkMax flywheel;
    private CANSparkMax controlWheel;

    private Encoder flywheelEncoder;
    private Encoder controlWheelEncoder;
    
    private PIDController flywheelPIDController;
    private PIDController controlWheelPIDController;

    /** Creates a new Shooter. */
    public Shooter() {
        flywheel = new CANSparkMax(Constants.ID_SPARKMAX_FLYWHEEL, MotorType.kBrushed);
        controlWheel = new CANSparkMax(Constants.ID_SPARKMAX_CONTROL_WHEEL, MotorType.kBrushed);

        flywheelEncoder = new Encoder(Constants.DIO_PIN_FLYWHEEL_ENCODER_0, Constants.DIO_PIN_FLYWHEEL_ENCODER_1);
        controlWheelEncoder= new Encoder(Constants.DIO_PIN_CONTROL_WHEEL_ENCODER_0, Constants.DIO_PIN_CONTROL_WHEEL_ENCODER_1);

        flywheelPIDController = new PIDController(Constants.kFLYWHEEL_P, Constants.kFLYWHEEL_I, Constants.kFLYWHEEL_D);
        controlWheelPIDController = new PIDController(Constants.kCONTROL_WHEEL_P, Constants.kCONTROL_WHEEL_I, Constants.kCONTROL_WHEEL_D);
    }

    

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }
}
