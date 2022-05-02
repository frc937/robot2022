// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.Drive;

public class DriveAutonomous extends CommandBase {

    /* Variables */
    private final Drive drivetrain;
    private double leftSpeed;
    private double rightSpeed;

    public DriveAutonomous(Drive driveSubsystem) {
        drivetrain = driveSubsystem;
        addRequirements(driveSubsystem);

    }

    @Override
    public void initialize() {
        SmartDashboard.putString("Drive Perspective", "Autonomous");
        leftSpeed = -0.2;
        rightSpeed = -0.2;
        drivetrain.moveSimple(leftSpeed, rightSpeed);
    }

    @Override
    public void execute() {}

    @Override
    public void end(boolean interrupted) {
        drivetrain.stop();
    }

    @Override
    public boolean isFinished() {
        return false;
    }

}
