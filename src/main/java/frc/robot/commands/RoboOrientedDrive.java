package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj.GenericHID;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Drive;


public class RoboOrientedDrive extends CommandBase {

    /* Variables */
    private final Drive drivetrain;
    private double RoboOrientedZ;
    private double RoboOrientedX;

    public RoboOrientedDrive(Drive driveSubsystem) {
        drivetrain = driveSubsystem;
        addRequirements(driveSubsystem);

    }

    @Override
    public void execute() {
        /* Gets the left and right axes of the robot and uses that to move */
        RoboOrientedZ = RobotContainer.controller.getLeftY();
        RoboOrientedX = RobotContainer.controller.getLeftX();
        /* The parentheses here still need a third (Z) axis to go off of. */
        drivetrain.move(RoboOrientedX,RoboOrientedZ, );

    }

}
