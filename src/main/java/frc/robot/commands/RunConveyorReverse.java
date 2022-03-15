// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Conveyor;
import frc.robot.subsystems.Intake;

/**
 * A command to run the conveyor backwards, mostly to eject balls from the robot (usually the opposing alliance's balls).
 * 
 * <p>Designed to be used with .whenHeld() or simliar methods.
 */
public class RunConveyorReverse extends CommandBase {
    Conveyor conveyor;

    /** 
     * Creates a new RunConveyor. 
     * @param intake Takes an intake subsystem for dependency injection
    */
    public RunConveyorReverse(Conveyor conveyorSubsystem) {
        conveyor = conveyorSubsystem;
        addRequirements(conveyorSubsystem);
    }

    /** 
     * Called when the command is initially scheduled. Moves the conveyor backwards.
     */
    @Override
    public void initialize() {
        conveyor.runConveyorReverse();
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {}

    /** 
     * Called once the command ends or is interrupted. Stops the conveyor's movement.
     */
    @Override
    public void end(boolean interrupted) {
        conveyor.stopConveyor();
    }

    /** 
     * Returns true when the command should end. Will always return false as this command is designed to be used with .whenHeld or a similar method.
     */
    @Override
    public boolean isFinished() {
        return false;
    }
}
