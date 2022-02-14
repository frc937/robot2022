// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Shooter;

public class RunControlWheel extends CommandBase {
    Shooter shooter;

    /** Creates a new RunControlWheel. */
    public RunControlWheel(Shooter shooter) {
        this.shooter = shooter;
        addRequirements(shooter);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        if (shooter.canShoot()) {
            shooter.runFeeder();
        } else {
            /* TODO: figure out what this button combo is and put it there */
            SmartDashboard.putString("Tried to shoot wrong color", "WARNING: YOU HAVE ATTEMPTED TO SHOOT THE WRONG ALLIANCE COLOR.\nTo override, press the override button combo.")
        }
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {}

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        shooter.stopFeeder();
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }
}
