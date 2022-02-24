// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Shooter;

/**
 * A command to run the control wheel for the shooter
 * 
 * <p>Used to push the ball into the flywheel so it can be shot
 */
public class RunControlWheel extends CommandBase {
    Shooter shooter;

    /**
     * Creates a new RunControlWheel. 
     * @param shooter Takes a shooter subsystem for dependency injection
    */
    public RunControlWheel(Shooter shooter) {
        this.shooter = shooter;
        addRequirements(shooter);
    }

    /**
     * Called when the command is initially scheduled. Turns on the wheel.
     */
    @Override
    public void initialize() {
        shooter.runFeeder();
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {}

    /**
     * Called when the command ends. Stops the wheel.
     */
    @Override
    public void end(boolean interrupted) {
        shooter.stopFeeder();
    }

    /** 
     * Returns true when the command should end. Will always return false, as this command is designed to be used with .whenHeld() or similar methods.
     */
    @Override
    public boolean isFinished() {
        return false;
    }
}
