// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

/**
 * Limelight subsystem. Handles the Limelight 2+, the camera we use for computer vision.
 */
public class Limelight extends SubsystemBase {
    private final NetworkTable limelightTable;
    private double tv, tx, ty, ta;

    /** Creates a new Limelight. */
    public Limelight() {
        this.limelightTable = NetworkTableInstance.getDefault().getTable("limelight");
        /* Turn off the Limelight's LEDs by default. Commands that use it will turn them on. */
        limelightTable.getEntry("ledMode").setNumber(1.0);
    }

    /**
     * Runs once per scheduler run. Gets all the NetworkTable values from the Limelight.
     */
    @Override
    public void periodic() {
        tv = limelightTable.getEntry("tv").getDouble(0);
        tx = limelightTable.getEntry("tx").getDouble(0);
        ty = limelightTable.getEntry("ty").getDouble(0);
        ta = limelightTable.getEntry("ta").getDouble(0);
    }

    /**
     * Gets the horizontal offset from the crosshair to the currently active target.
     * @return tx; the horizontal offset from the corsshair to the target.
     */
    public double getTX() {
        return tx;
    }

    /**
     * Gets the vertical offset from the crosshair to the currently active target.
     * @return ty; the vertical offset from the corsshair to the target.
     */
    public double getTY() {
        return ty;
    }

    /**
     * Gets the area of the target. 0% to 100% of image.
     * @return ta; the area of the target.
     */
    public double getTA() {
        return ta;
    }

    /**
     * Returns true if the Limelight has a valid target.
     * @return A boolean; true if the Limelight has a valid target.
     */
    public boolean hasValidTarget() {
        return tv == 1.0;
    }
}
