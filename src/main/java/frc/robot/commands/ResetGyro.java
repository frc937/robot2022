// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drive;

public class ResetGyro extends CommandBase {
    private Drive drive;
    private boolean done;
    /** Creates a new ResetGyro. */
    public ResetGyro(Drive drive) {
        // Use addRequirements() here to declare subsystem dependencies.
        this.drive = drive;
        this.done = false;
        addRequirements(drive);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        drive.resetGyro();
        done = true;
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {}

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {}

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return done;
    }
}
