package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Drive;


public class RoboOrientedDrive extends CommandBase {

    /* Variables */
    private final Drive drivetrain;
    // private double RoboOrientedY;
    // private double RoboOrientedX;

    public RoboOrientedDrive(Drive driveSubsystem) {
        drivetrain = driveSubsystem;
        addRequirements(driveSubsystem);

    }

    @Override
    public void initialize() {
        SmartDashboard.putString("Drive Perspective", "Robot");
    }

    @Override
    public void execute() {
        /* Gets the left and right axes of the robot and uses that to move */
        // RoboOrientedY = RobotContainer.controller.getLeftY();
        // RoboOrientedX = RobotContainer.controller.getLeftX();
        drivetrain.move(RobotContainer.getScaledLeftXAxis(), RobotContainer.getScaledLeftYAxis(), RobotContainer.getScaledRightXAxis());

    }

    @Override
    public void end(boolean interrupted) {
        drivetrain.stop();

    }

    @Override
    public boolean isFinished() {
        return false;
    }

}
