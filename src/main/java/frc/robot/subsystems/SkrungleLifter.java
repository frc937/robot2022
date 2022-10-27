// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class SkrungleLifter extends SubsystemBase {
    /** Creates a new ScrungleLifter. */
    private CANSparkMax lifterLeft;
    private CANSparkMax lifterRight;
    private DigitalInput swLiftUp;
    private DigitalInput swLiftDown;
    private boolean reset;

    public SkrungleLifter() {
        lifterLeft = new CANSparkMax(Constants.ID_SPARKMAX_SKRUNGLE_LIFT_L, MotorType.kBrushed);
        lifterRight = new CANSparkMax(Constants.ID_SPARKMAX_SKRUNGLE_LIFT_R, MotorType.kBrushed);
        lifterLeft.follow(lifterRight,true);
        swLiftUp = new DigitalInput(Constants.ID_SCRUNGLE_UP_SWITCH);
        swLiftDown = new DigitalInput(Constants.ID_SCRUNGLE_DOWN_SWITCH);
    }

    public void liftScrungles() {
        if (!swLiftUp.get()) {
            lifterRight.set(Constants.SKRUNGLE_LIFT_SPEED);
        } else {
            lifterRight.set(0);
            reset = false;
        }
    }

    public void lowerScrungles() {
        if (!swLiftDown.get()) {
            lifterRight.set(-1*Constants.SKRUNGLE_LIFT_SPEED);
        } else {
            lifterRight.set(0);
        }
    }

    public void stopMotors() {
        lifterRight.stopMotor();
    }

    public void endCommand() {
        reset = true;
    }

    @Override
    public void periodic() {
        if (reset) {
            liftScrungles();
        }
    }

    @Override
    public void simulationPeriodic() {
        // This method will be called once per scheduler run during simulation
    }
}
