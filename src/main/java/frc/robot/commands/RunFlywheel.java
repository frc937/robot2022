// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.Constants;
import frc.robot.subsystems.Shooter;

/* This is very intentionally NOT inlined, because we may want to do power optimizations later. */
/**
 * Command to run the flywheel. Run as a defualt command.
 */
public class RunFlywheel extends PIDCommand {
    Shooter shooter;
    boolean done;

    /** Constructor for RunFlywheel */
    public RunFlywheel(Shooter shooter) {
        super(
            // The controller that the command will use
            new PIDController(Constants.kFLYWHEEL_P, Constants.kFLYWHEEL_I, Constants.kFLYWHEEL_D),
            // This should return the measurement
            shooter::getMeasurement,
            // This should return the setpoint (can also be a constant)
            Constants.FLYWHEEL_SETPOINT,
            // This uses the output
            output -> {
                // Use the output here
            });
        addRequirements(shooter);
        this.shooter = shooter;
        done = false;
    }

    /**
     * Initialization for RunFlywheel command. Starts the flywheel set done to true, which is returned by isFinished.
     */
    @Override
    public void initialize() {
        shooter.enable();
        done = true;
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return done;
    }
}
