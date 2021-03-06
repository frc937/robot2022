// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.lang.Math;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Drive;
import frc.robot.subsystems.Limelight;

/**
 * A command to use the Limelight to aim the robot
 * 
 * <p>Designed to be used with .whenHeld for obvious safety reasons, but isFinished() will return true when the robot is aimed.
 * This is so that this can be used with a command group to automate aiming AND shooting.
 */
public class AimWithLimelight extends CommandBase {
    private Drive drive;
    private Limelight limelight;
    private boolean finished;
    private int counter;

    /** 
     * Creates a new AimWithLimelight.
     * @param drive Takes a drive subsystem for dependency injection
     * @param limelight Takes a limelight subsystem for dependency injection
     */
    public AimWithLimelight(Drive drive, Limelight limelight) {
        this.drive = drive;
        this.limelight = limelight;
        this.finished = false;
        this.counter = 0;
        addRequirements(drive, limelight);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        this.finished = false;
        this.counter = 0;
        limelight.turnOnLEDs();
    }

    /**
     * Called every time the scheduler runs while the command is scheduled. Moves the robot to aim into the upper hub.
     */
    @Override
    public void execute() {
        if (limelight.hasValidTarget()) {
            double z = limelight.getTX() * Constants.LIMELIGHT_STEER_STRENGTH;
            double y = (Constants.LIMELIGHT_DIST_FROM_TARGET - ((Constants.UPPER_HUB_TAPE_HEIGHT - Constants.LIMELIGHT_MOUNT_HEIGHT)/Math.tan((Constants.LIMELIGHT_MOUNT_ANGLE + limelight.getTY()) * (Math.PI / 180.0)))) * Constants.LIMELIGHT_DRIVE_STRENGTH;

            if (z > Constants.LIMELIGHT_SPEED_LIMIT) {
                z = Constants.LIMELIGHT_SPEED_LIMIT;
            }

            drive.moveMecanumRobot(y * -1.0, 0.0, z);

            boolean isAngled = Math.abs(limelight.getTX()) < Constants.LIMELIGHT_TURN_DONE_THRESHOLD;
            boolean isDistanced = Math.abs((Constants.LIMELIGHT_DIST_FROM_TARGET - ((Constants.UPPER_HUB_TAPE_HEIGHT - Constants.LIMELIGHT_MOUNT_HEIGHT)/Math.tan((Constants.LIMELIGHT_MOUNT_ANGLE + limelight.getTY()) * (Math.PI / 180.0))))) <= Constants.LIMELIGHT_DISTANCE_DONE_THRESHOLD;

            if (isAngled && isDistanced) {
                counter++;
                if (counter > 5) {
                    this.finished = true;
                }
            }
        }
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        limelight.turnOffLEDs();
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return this.finished;
    }
}
