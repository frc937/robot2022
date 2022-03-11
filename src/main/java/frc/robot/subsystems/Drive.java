// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
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
        frontLeft = configTalon(Constants.ID_TALON_FRONT_LEFT);
        rearLeft = configTalon(Constants.ID_TALON_REAR_LEFT);
        frontRight = configTalon(Constants.ID_TALON_FRONT_RIGHT);
        rearRight = configTalon(Constants.ID_TALON_REAR_RIGHT);

        /* Sets default drive directions */
        /*frontLeft.setInverted(false);
        rearLeft.setInverted(false);
        frontRight.setInverted(false);
        rearRight.setInverted(false);*/

        /* Initializes a mecanum drivetrain */ 
        drivetrain = new MecanumDrive(frontLeft, rearLeft, frontRight, rearRight);
    }

    /**
     * A method to create and configure Talon SRXs for the drivetrain for us so we don't have to have a tonne of boilerplate.
     * @param id The CAN ID of the Talon to configure
     * @return The configured Talon.
     */
    private WPI_TalonSRX configTalon(int id) {
        WPI_TalonSRX talon = new WPI_TalonSRX(id);

        talon.configFactoryDefault();

        talon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);

        /* add one to the ID cause the arr is off by one (see Constants.java) */
        talon.setSensorPhase(Constants.DRIVE_SENSOR_PHASE[id + 1]);

        talon.setInverted(Constants.DRIVE_INVERTED[id + 1]);

        /* Phoenix examples has peak output, nominal output, and close-loop error config here. I don't really know what those do, so I'm not worrying about them for now. */

        /* PIDF gains. Loop will always just be the default one, since we aren't using any of the other loops. */
        talon.config_kP(0, Constants.DRIVE_GAINS[0]);
        talon.config_kI(0, Constants.DRIVE_GAINS[1]);
        talon.config_kD(0, Constants.DRIVE_GAINS[2]);
        talon.config_kF(0, Constants.DRIVE_GAINS[3]);

        return talon;
    }


    public void moveRobot(double y, double x, double z) {
        /* Sets the default drive mode to Cartesian */
        //drivetrain.driveCartesian(x, y, z, ahrs.getAngle());
        drivetrain.driveCartesian(y, x, z);
    }

    public void moveField(double y, double x, double z) {
        drivetrain.driveCartesian(y, x, z, ahrs.getAngle());
    }

    /**
     * Position PID move method. Params are in inches of movement on that axis, so movePositionPID(1.0, 0.0, 0.0); would move the bot 1 inch to the right.
     * @param y Y setpoint. Positive is right.
     * @param x X setpoint. Positive is forward.
     * @param z Z setpoint. Positive is clockwise (I THINK).
     */
    public void movePositionPID(double y, double x, double z) {
        /* multiply vars so 1 input = 1 inch of movement */
        x = (x * 4096) / (8 * Math.PI);
        y = (y * 4096) / (8 * Math.PI);
        z = (z * 4096) / (8 * Math.PI);
        frontLeft.set(ControlMode.Position, (x-y-z));
        rearLeft.set(ControlMode.Position, (-x-y-z));
        frontRight.set(ControlMode.Position, (x+y+z));
        rearRight.set(ControlMode.Position, (-x+y+z));
    }

    /**
     * Velocity PID move method. Params are between -1 and 1, standard joystick notation things.
     * @param y Y setpoint. Positive is right.
     * @param x X setpoint. Positive is forward.
     * @param z Z setpoint. Positive is clockwise (I THINK).
     */
    public void moveVelocityPID(double y, double x, double z) {
        /* multiply vars so -1 to +1 as input works */
        y = y * Constants.DRIVE_VELOCITY_PID_MAX_SPEED * 4096 / 600;
        x = x * Constants.DRIVE_VELOCITY_PID_MAX_SPEED * 4096 / 600;
        z = z * Constants.DRIVE_VELOCITY_PID_MAX_SPEED * 4096 / 600;
        frontLeft.set(ControlMode.Velocity, (x-y-z));
        rearLeft.set(ControlMode.Velocity, (-x-y-z));
        frontRight.set(ControlMode.Velocity, (x+y+z));
        rearRight.set(ControlMode.Velocity, (-x+y+z));
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
