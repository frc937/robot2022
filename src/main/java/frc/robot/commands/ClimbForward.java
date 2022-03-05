package frc.robot.commands;

import frc.robot.subsystems.Climber;
import frc.robot.Constants;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import edu.wpi.first.math.controller.PIDController;


public class ClimbForward extends PIDCommand {

    /* Variables */
    /* this variable maybe shouldn't be static, but I don't know enough java to fix it when it's private */
    static Climber climberInABox;

    /* Initializes the ClimbUp method */
    public ClimbForward(Climber subsystemClimb) {
        super(
            // The controller that the command will use
            new PIDController(Constants.kWINCH_P, Constants.kWINCH_I, Constants.kWINCH_D),
            // This should return the measurement
            climberInABox::getMeasurement,
            // This should return the setpoint (can also be a constant)
            Constants.WINCH_SPEED,
            // This uses the output
            output -> {
                // Use the output here
            });

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
