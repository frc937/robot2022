// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.subsystems.Shooter;
import frc.robot.RobotContainer;

public class PollColorSensor extends CommandBase {
    /** Creates a new PollColorSensor. */
    private Shooter shooter;
    private RobotContainer robotContainer;

    private int counter;

    public PollColorSensor(Shooter shooter) {
        this.shooter = shooter;
        this.counter = 0;
        this.robotContainer = new RobotContainer();
        addRequirements(shooter);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {}

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        if (counter == 0) {
            if (!shooter.canShoot()) {
                robotContainer.getConveyorReverseCommand().schedule();
            }
        } else {
            if (counter == 5) {
                counter = 0;
            } else {
                counter++;
            }
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
