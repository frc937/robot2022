// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.ExampleCommand;
import frc.robot.commands.RoboOrientedDrive;
import frc.robot.commands.RunFlywheel;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.Drive;
import frc.robot.subsystems.Shooter;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
    // The robot's subsystems and commands are defined here...
    private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();
    private final Drive driveSubsystem = new Drive();
    private final Shooter shooter = new Shooter();

    private final ExampleCommand m_autoCommand = new ExampleCommand(m_exampleSubsystem);
    private final RoboOrientedDrive driveRO = new RoboOrientedDrive(driveSubsystem);
    private final RunFlywheel runFlywheel = new RunFlywheel(shooter);

    public static XboxController controller = new XboxController(Constants.CONTROLLER_NUMBER);

    /** The container for the robot. Contains subsystems, OI devices, and commands. */
    public RobotContainer() {
        // Configure the button bindings
        configureButtonBindings();
    }

    /**
     * Use this method to define your button->command mappings. Buttons can be created by
     * instantiating a {@link GenericHID} or one of its subclasses ({@link
     * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
     * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
     */
    private void configureButtonBindings() {
        JoystickButton aButton = new JoystickButton(controller, Constants.A_NUMBER);
        JoystickButton bButton = new JoystickButton(controller, Constants.B_NUMBER);
        JoystickButton xButton = new JoystickButton(controller, Constants.X_NUMBER);
        JoystickButton yButton = new JoystickButton(controller, Constants.Y_NUMBER);
        JoystickButton leftBumper = new JoystickButton(controller, Constants.LEFT_BUMPER_NUMBER);
        JoystickButton rightBumper = new JoystickButton(controller, Constants.RIGHT_BUMPER_NUMBER);
        JoystickButton backButton = new JoystickButton(controller, Constants.BACK_NUMBER);
        JoystickButton startButton = new JoystickButton(controller, Constants.START_NUMBER);
        JoystickButton leftStick = new JoystickButton(controller, Constants.LEFT_STICK_NUMBER);
        JoystickButton rightStick = new JoystickButton(controller, Constants.RIGHT_STICK_NUMBER);
    }
    

    /**
     * Use this to pass the autonomous command to the main {@link Robot} class.
     *
     * @return the command to run in autonomous
     */
    public Command getAutonomousCommand() {
        // An ExampleCommand will run in autonomous
        return m_autoCommand;
    }

    public Command getDriveROCommand() {
        return driveRO;
    }
    
    /**
     * Used to pass the command to run the flywheel to {@link Robot}
     * @return The command to run the flywheel
     */
    public Command getRunFlywheelCommand() {
        return runFlywheel;
    }
}
