// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.*;
import com.kauailabs.navx.frc.AHRS;

import frc.robot.Constants;

public class Drive extends SubsystemBase {

    /* Variables */
    WPI_TalonSRX frontLeft;
    WPI_TalonSRX rearLeft;
    WPI_TalonSRX frontRight;
    WPI_TalonSRX rearRight;

    MecanumDrive drivetrain;

    AHRS ahrs;

    /** Creates a new drivetrain subsystem. */
    public Drive() {
        /* Initializes drive motor controllers */
        frontLeft = new WPI_TalonSRX(Constants.ID_TALON_FRONT_LEFT);
        rearLeft = new WPI_TalonSRX(Constants.ID_TALON_REAR_LEFT);
        frontRight = new WPI_TalonSRX(Constants.ID_TALON_FRONT_RIGHT);
        rearRight = new WPI_TalonSRX(Constants.ID_TALON_REAR_RIGHT);

        /* Sets default drive directions */
        frontLeft.setInverted(false);
        rearLeft.setInverted(false);
        frontRight.setInverted(true);
        rearRight.setInverted(true);

        frontLeft.setNeutralMode(NeutralMode.Brake);
        rearLeft.setNeutralMode(NeutralMode.Brake);
        frontRight.setNeutralMode(NeutralMode.Brake);
        rearRight.setNeutralMode(NeutralMode.Brake);
        
        /* WHEN SETTING UP PID IN A BIT
         * Instead of writing the same config statements for each
         * motor and creating tonnes of boilerplate, we could create 
         * a method that configures a Talon object that we pass to
         * it.
         * Also, if it turns out that we need to configure lots of 
         * different parts in this manner, we could create a big static
         * class with lots of static methods to configure different
         * components (similar to Constants).
         */

        /* Initializes a mecanum drivetrain */ 
        drivetrain = new MecanumDrive(frontLeft, rearLeft, frontRight, rearRight);
    }


    public void moveMecanumRobot(double y, double x, double z) {
        /* Sets the default drive mode to Cartesian */
        //drivetrain.driveCartesian(x, y, z, ahrs.getAngle());
        drivetrain.driveCartesian(y, x, z);
    }

    public void moveMecanumField(double y, double x, double z) {
        drivetrain.driveCartesian(y, x, z, ahrs.getAngle());
    }

    public void moveSimple(double left, double right) {
        frontLeft.set(left);
        rearLeft.set(left);
        frontRight.set(right);
        rearRight.set(right);
    }

    public void stop() {
        frontLeft.stopMotor();
        frontRight.stopMotor();
        rearLeft.stopMotor();
        rearRight.stopMotor();
    }

    public void resetGyro() {
        ahrs.reset();
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
