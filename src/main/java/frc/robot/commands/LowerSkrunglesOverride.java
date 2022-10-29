// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.SkrungleLifter;

public class LowerSkrunglesOverride extends CommandBase {
    SkrungleLifter skrungleLifter;

  /** Creates a new LiftSkrungles. */
  public LowerSkrunglesOverride(SkrungleLifter skrungleLifter) {
    this.skrungleLifter = skrungleLifter;
    addRequirements(skrungleLifter);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
      this.skrungleLifter.lowerSkrunglesOverride();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
      
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
      this.skrungleLifter.stopMotors();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
