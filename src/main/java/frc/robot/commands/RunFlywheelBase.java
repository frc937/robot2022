// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Flywheel;

/* This is very intentionally NOT inlined, because we may want to do power optimizations later. */
/**
 * Command to run the flywheel. Run as a default command.
 */
public class RunFlywheelBase extends CommandBase {
    Flywheel flywheel;
    boolean done;

    /** Constructor for RunFlywheel */
    public RunFlywheelBase(Flywheel flywheelSubsystem) {
        addRequirements(flywheelSubsystem);
        flywheel = flywheelSubsystem;
        done = false;
    }

    /**
     * Initialization for RunFlywheel command. Starts the flywheel and sets done to true, which is returned by isFinished.
     */
    @Override
    public void initialize() {
        flywheel.setVelocity(Constants.FLYWHEEL_SPEED_BASE);
        done = true;
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return done;
    }
}
