package frc.robot.commands;

import frc.robot.subsystems.Climber;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class ClimbUp extends CommandBase {

    /* Variables */
    private Climber climberInABox;

    /* Initializes the ClimbUp method */
    public ClimbUp(Climber subsystemClimb) {

        subsystemClimb = climberInABox;

        addRequirements(climberInABox);

    }kadsjf alkjdsf a

    @Override
    public void initialize() {

    }
    g
    @Override
    public void end(boolean interrupted) {
        ClimbUp.stopClimber();
    }

    @Override
    public boolean isFinished() {
        return false;
    }

}
