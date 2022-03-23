package frc.robot.commands;

import frc.robot.subsystems.Climber;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class ClimbDown extends CommandBase {

    /* Variables */
    private Climber climberInABox;

    /* Initializes the ClimbUp method */
    public ClimbDown(Climber subsystemClimb) {

        climberInABox = subsystemClimb;

        addRequirements(subsystemClimb);

    }

    @Override
    public void initialize() {
        climberInABox.runClimberDown();
    }
    
    @Override
    public void end(boolean interrupted) {
        climberInABox.stopClimber();
    }

    @Override
    public boolean isFinished() {
        return false;
    }

}
