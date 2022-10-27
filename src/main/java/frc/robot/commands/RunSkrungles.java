// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.SkrungleLifter;
import frc.robot.subsystems.Skrungles;

/**
 * A command to run the Skrungles, the initial intake mechanism for the robot.
 * 
 * <p>Designed to be used with .whenHeld() or similar methods.
 */
public class RunSkrungles extends CommandBase {
    Skrungles skrungles;
    SkrungleLifter skrungleLifter;

    /** 
     * Creates a new RunSkrungles.
     * @param intake Takes an intake subsytem for dependency injection
     */
    public RunSkrungles(Skrungles skrunglesSubsystem, SkrungleLifter lifter) {
        skrungles = skrunglesSubsystem;
        skrungleLifter = lifter;
        addRequirements(skrunglesSubsystem);
        addRequirements(skrungleLifter);
    }

    /** 
     * Called when the command is initially scheduled. Turns on the skrungles.
     */
    @Override
    public void initialize() {
        skrungles.runSkrungles();
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute(
    ) {
        skrungleLifter.lowerScrungles();
    }

    /**
     * Called once the command ends or is interrupted. Turns off the skrungles.
     */
    @Override
    public void end(boolean interrupted) {
        skrungles.stopSkrungles();
        skrungleLifter.endCommand();
    }

    /** 
     * Returns true when the command should end. Will always return false as this command is designed to be used with .whenHeld or a similar method.
     */
    @Override
    public boolean isFinished() {
        return false;
    }
}
