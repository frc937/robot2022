// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.*;
import frc.robot.Constants;

public class Drive extends SubsystemBase {

    /** Variables */
    WPI_TalonSRX frontLeft;
    WPI_TalonSRX rearLeft;
    WPI_TalonSRX frontRight;
    WPI_TalonSRX rearRight;

    MecanumDrive drivetrain;

    /** Creates a new drivetrain subsystem. */
    public Drive() {
        /** Initializes drive motor controllers */
        frontLeft = new WPI_TalonSRX(Constants.ID_TALON_FRONT_LEFT);
        rearLeft = new WPI_TalonSRX(Constants.ID_TALON_REAR_LEFT);
        frontRight = new WPI_TalonSRX(Constants.ID_TALON_FRONT_RIGHT);
        rearRight = new WPI_TalonSRX(Constants.ID_TALON_REAR_RIGHT);

        /** Sets default drive directions */
        frontLeft.setInverted(false);
        rearLeft.setInverted(false);
        frontRight.setInverted(false);
        rearRight.setInverted(false);
        
        /** Initializes a mecanum drivetrain */ 
        drivetrain = new MecanumDrive(frontLeft, rearLeft, frontRight, rearRight);
    }

    public void move(double x, double y, double z,) {
        /** Sets the default drive mode to Cartesian */
        drivetrain.driveCartesian(x, y, z);
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }

    @Override
    public void simulationPeriodic() {
        // This method will be called once per scheduler run during simulation
    }
}
