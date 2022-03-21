package frc.robot.commands;

import frc.robot.subsystems.Climber;
import frc.robot.Constants;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class ClimbUp extends CommandBase {

    /* Variables */
    // this variable maybe shouldn't be static, but I don't know enough java to fix it when it's private
    static Climber climberInABox;

    /* Initializes the ClimbUp method */
    public ClimbUp(Climber subsystemClimb) {

        climberInABox = subsystemClimb;

        addRequirements(subsystemClimb);

    }

    @Override
    public void initialize() {
        climberInABox.runClimberUp();
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
