package frc.robot.commands;

import frc.robot.subsystems.Climber;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class ClimbForward extends CommandBase {

    /* Variables */
    private Climber climberInABox;

    /* Initializes the ClimbUp method */
    public ClimbForward(Climber subsystemClimb) {

        climberInABox = subsystemClimb;

        addRequirements(subsystemClimb);

    }

    @Override
    public void initialize() {

        climberInABox.runClimberForward();

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
