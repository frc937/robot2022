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
    private boolean isUp;
    private boolean moving;
    private boolean moveUp;

    public SkrungleLifter() {
        lifterLeft = new CANSparkMax(Constants.ID_SPARKMAX_SKRUNGLE_LIFT_L, MotorType.kBrushed);
        lifterRight = new CANSparkMax(Constants.ID_SPARKMAX_SKRUNGLE_LIFT_R, MotorType.kBrushed);
        lifterLeft.follow(lifterRight,true);
        swLiftUp = new DigitalInput(Constants.ID_SCRUNGLE_UP_SWITCH);
        swLiftDown = new DigitalInput(Constants.ID_SCRUNGLE_DOWN_SWITCH);
        resetMotors();
        moveUp = false;
        moving = false;
    }

    public void liftScrungles() {
        if (!isUp) {
            startMotors(1);
        }
    }

    public void lowerScrungles() {
        if (isUp) {
            startMotors(0);
        }
    }

    private boolean getUpSwitchState() {
        return swLiftUp.get();
    }

    private boolean getDownSwitchState() {
        return swLiftDown.get();
    }

    private void startMotors(int state) {
        if (state == 1) {
            lifterRight.set(Constants.SKRUNGLE_LIFT_SPEED);
            moveUp = true;
        } else if (state == 0) {
            lifterRight.set(-1*Constants.SKRUNGLE_LIFT_SPEED);
            moveUp = false;
        }

    }

    private void stopMotors() {
        lifterRight.stopMotor();
        moving = false;

    }

    private void resetMotors() {
        if (!getUpSwitchState()) {
            startMotors(1);
        }
    }

    @Override
    public void periodic() {
        if (moving) {
            if ((getDownSwitchState()) & (!moveUp)) {
                stopMotors();
            } else if ((getUpSwitchState()) & (moveUp)) {
                stopMotors();
            }
        }
    }

    @Override
    public void simulationPeriodic() {
        // This method will be called once per scheduler run during simulation
    }
}
