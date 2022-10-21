// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ScrungleLifter extends SubsystemBase {
    /** Creates a new ScrungleLifter. */
    private CANSparkMax lifterLeft;
    private CANSparkMax lifterRight;
    private DigitalInput swLiftUp;
    private DigitalInput swLiftDown;
    private boolean isUp;
    private boolean moving;
    private boolean moveUp;
    public ScrungleLifter() {
        lifterLeft = new CANSparkMax(Constants.ID_SPARKMAX_SKRUNGLE_LIFT_L, MotorType.kBrushed);
        lifterLeft.setInverted(true);
        lifterRight = new CANSparkMax(Constants.ID_SPARKMAX_SKRUNGLE_LIFT_R, MotorType.kBrushed);
        swLiftUp = new DigitalInput(Constants.ID_SCRUNGLE_UP_SWITCH);
        swLiftDown = new DigitalInput(Constants.ID_SCRUNGLE_DOWN_SWITCH);
        isUp = true; // assumes robot starts with intake is up
        moveUp = false;
        moving = false;
    }

    public void liftScrungles() {
        if (!isUp) {
            startMotors("up");
        }
    }

    private boolean getUpSwitchState() {
        return swLiftUp.get();
    }

    private boolean getDownSwitchState() {
        return swLiftDown.get();
    }

    private void startMotors(String state) {
        if (state.equals("up")) {
            lifterLeft.set(Constants.SKRUNGLE_LIFT_SPEED);
            lifterRight.set(Constants.SKRUNGLE_LIFT_SPEED);
            moveUp = true;
        } else if (state.equals("down")) {
            lifterLeft.set(-Constants.SKRUNGLE_LIFT_SPEED);
            lifterRight.set(-Constants.SKRUNGLE_LIFT_SPEED);
            moveUp = false;
        }

    }

    private void stopMotors(){
        lifterRight.stopMotor();
        lifterLeft.stopMotor();
        moving = false;
        
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
