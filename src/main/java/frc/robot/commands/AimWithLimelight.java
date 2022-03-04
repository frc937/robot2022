// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Drive;
import frc.robot.subsystems.Limelight;

/**
 * A command to use the Limelight to aim the robot
 * 
 * <p>Currently set up to be used with .whenHeld() or similar methods, but could easily be altered to work as a oneshot.
 */
public class AimWithLimelight extends CommandBase {
    private Drive drive;
    private Limelight limelight;

    /** 
     * Creates a new AimWithLimelight.
     * @param drive Takes a drive subsystem for dependency injection
     * @param limelight Takes a limelight subsystem for dependency injection
     */
    public AimWithLimelight(Drive drive, Limelight limelight) {
        this.drive = drive;
        this.limelight = limelight;
        addRequirements(drive, limelight);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {}

    /**
     * Called every time the scheduler runs while the command is scheduled. Moves the robot to aim into the upper hub.
     */
    @Override
    public void execute() {
        if (limelight.hasValidTarget()) {
            double z = limelight.getTX() * Constants.LIMELIGHT_STEER_STRENGTH;
            double x = (Constants.LIMELIGHT_DIST_FROM_TARGET - ((Constants.UPPER_HUB_TAPE_HEIGHT - Constants.LIMELIGHT_MOUNT_HEIGHT)/Math.tan((Constants.LIMELIGHT_MOUNT_ANGLE + limelight.getTY()) * (Math.PI / 180.0)))) * Constants.LIMELIGHT_DRIVE_STRENGTH;

            if (z > Constants.LIMELIGHT_SPEED_LIMIT) {
                z = Constants.LIMELIGHT_SPEED_LIMIT;
            }
            
            drive.moveRobot(0.0, x, z);
        }
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {}

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }
}
