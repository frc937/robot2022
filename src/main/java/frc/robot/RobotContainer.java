// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.util.Color;
import frc.robot.commands.ExampleCommand;
import frc.robot.commands.DriveFieldOriented;
import frc.robot.commands.DriveRobotOriented;
import frc.robot.commands.DriveAutonomous;
import frc.robot.commands.ClimbForward;
import frc.robot.commands.ClimberReset;
import frc.robot.commands.RunControlWheel;
import frc.robot.commands.RunConveyorForward;
import frc.robot.commands.RunConveyorReverse;
import frc.robot.commands.RunSkrungles;
import frc.robot.commands.RunFlywheel;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Drive;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.ColorSensor;
import frc.robot.subsystems.Shooter;
import frc.robot.TwoButtonCombo;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.ConditionalCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
    /* The robot's subsystems and commands are defined here... */
    private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();
    private final Drive driveSubsystem = new Drive();
    private final Shooter shooter = new Shooter();
    private final Intake intake = new Intake();
    private final Climber climberSubsystem = new Climber();
    private final ColorSensor colorSensor = new ColorSensor();

    private final ExampleCommand m_autoCommand = new ExampleCommand(m_exampleSubsystem);
    private final DriveRobotOriented driveRO = new DriveRobotOriented(driveSubsystem);
    private final DriveFieldOriented driveFO = new DriveFieldOriented(driveSubsystem);
    private final DriveAutonomous driveA = new DriveAutonomous(driveSubsystem);
    private final InstantCommand resetGyro = new InstantCommand(driveSubsystem::resetGyro, driveSubsystem);
    private final ClimbForward climbForward = new ClimbForward(climberSubsystem);
    private final ClimberReset climberReset = new ClimberReset(climberSubsystem);
    private final RunFlywheel runFlywheel = new RunFlywheel(shooter);
    private final RunSkrungles runSkrungles = new RunSkrungles(intake);
    private final RunConveyorForward runConveyorForward = new RunConveyorForward(intake);
    private final RunConveyorReverse runConveyorReverse = new RunConveyorReverse(intake);
    private final RunControlWheel runControlWheel = new RunControlWheel(shooter);
    
    public static XboxController controller = new XboxController(Constants.CONTROLLER_NUMBER);

    /** The container for the robot. Contains subsystems, OI devices, and commands. */
    public RobotContainer() {
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
        TwoButtonCombo xAndY = new TwoButtonCombo(controller, Constants.X_NUMBER, Constants.Y_NUMBER);

        /* TODO: Change these buttons when a more convenient controller layout becomes obvious */
        /* Buttons for the climber in a box */
        leftBumper.whenHeld(climbForward);
        rightBumper.whenHeld(climberReset);

        /* Buttons for shooter/intake */
        aButton.whenHeld(runSkrungles);
        bButton.whenHeld(runControlWheel);
        xButton.whenHeld(new ConditionalCommand(runConveyorForward, runConveyorReverse, colorSensor::canShoot));
        xAndY.whenHeld(runConveyorForward);

        backButton.whenPressed(resetGyro);

        /* Field oriented while stick pressed, we should make sure this is viable on the human end */
        leftStick.whileHeld(driveFO);

    }
    

    /**
     * Use this to pass the autonomous command to the main {@link Robot} class.
     *
     * @return the command to run in autonomous
     */
    public Command getAutonomousCommand() {
        /* Autonomous runs driveA then Shooter */
        return new ParallelCommandGroup(driveA, runControlWheel);
        //return m_autocommand;
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

    public static double getLeftXAxis() {
        return controller.getLeftX();
    }

    public static double getScaledLeftXAxis() {
        return scaleAxis(getLeftXAxis());
       }


    public static double getLeftYAxis() {
        return controller.getLeftX();
    }

    public static double getScaledLeftYAxis() {
       return scaleAxis(getLeftYAxis());
    }


    public static double getRightXAxis() {
        return controller.getRightX();
    }

    public static double getScaledRightXAxis() {
        return scaleAxis(getRightXAxis());
    }


    public static double getRightYAxis() {
        return controller.getRightY();
    }

    public static double getScaledRightYAxis() {
        return scaleAxis(getRightYAxis());
    }

    private static double scaleAxis(double a) {
        return Math.signum(a) * Math.pow(a, 4);
    }

}
