// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class Intake extends SubsystemBase {
    private CANSparkMax scrungles;
    private CANSparkMax conveyor;
    /** Creates a new Intake. */
    public Intake() {
        scrungles = new CANSparkMax(Constants.ID_SPARKMAX_SCRUNGLES, MotorType.kBrushed);
        conveyor = new CANSparkMax(Constants.ID_SPARKMAX_CONVEYOR, MotorType.kBrushed);

        scrungles.setInverted(false);
        conveyor.setInverted(false);
    }

    public void runScrungles() {
        scrungles.set(Constants.SCRUNGLE_SPEED);
    }

    public void stopScrungles() {
        scrungles.set(0);
    }

    public void runConveyor() {
        conveyor.set(Constants.CONVEYOR_SPEED);
    }

    public void stopConveyor() {
        conveyor.set(0);
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }
}
