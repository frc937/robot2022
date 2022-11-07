// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Conveyor;
import frc.robot.subsystems.IndexWheel;

/**
 * A command to run the control wheel for the shooter
 * 
 * <p>Used to push the ball into the flywheel so it can be shot
 */
public class RunIndexWheel extends CommandBase {
    IndexWheel index;
    Conveyor conveyor;

    /**
     * Creates a new RunControlWheel. 
     * @param shooter Takes a shooter subsystem for dependency injection
    */
    public RunIndexWheel(IndexWheel indexSubsystem, Conveyor conveyorSubsystem) {
        index = indexSubsystem;
        conveyor = conveyorSubsystem;
        addRequirements(indexSubsystem);
    }

    /**
     * Called when the command is initially scheduled. Turns on the wheel.
     */
    @Override
    public void initialize() {
        index.runIndex();
        conveyor.runConveyorForward();
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {}

    /**
     * Called when the command ends. Stops the wheel.
     */
    @Override
    public void end(boolean interrupted) {
        index.stopIndex();
        conveyor.stopConveyor();
    }

    /** 
     * Returns true when the command should end. Will always return false, as this command is designed to be used with .whenHeld() or similar methods.
     */
    @Override
    public boolean isFinished() {
        return false;
    }
}
