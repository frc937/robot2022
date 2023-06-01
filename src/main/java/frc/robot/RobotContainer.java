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
import frc.robot.commands.AimWithLimelight;
import frc.robot.commands.ClimbUp;
import frc.robot.commands.ClimbDown;
import frc.robot.commands.RunIndexWheel;
import frc.robot.commands.RunConveyorForward;
import frc.robot.commands.RunConveyorReverse;
import frc.robot.commands.RunSkrungles;
import frc.robot.commands.RunFlywheel;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.Flywheel;
import frc.robot.subsystems.IndexWheel;
import frc.robot.subsystems.Limelight;
import frc.robot.subsystems.Skrungles;
import frc.robot.subsystems.Drive;
import frc.robot.subsystems.Camera;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.ColorSensor;
import frc.robot.subsystems.Conveyor;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.ConditionalCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.POVButton;

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
    private final Climber climberSubsystem = new Climber();
    /*private final ColorSensor colorSensor = new ColorSensor();*/
    private final Conveyor conveyorSubsystem = new Conveyor();
    private final Flywheel flywheelSubsystem = new Flywheel();
    private final IndexWheel indexSubsystem = new IndexWheel();
    private final Skrungles skrunglesSubsystem = new Skrungles();
    private final Camera driverCamera = new Camera(Constants.ID_CAMERA_DRIVER);
    private final Camera conveyorCamera = new Camera(Constants.ID_CAMERA_CONVEYOR);
    private final Limelight limelight = new Limelight();

    private final ExampleCommand m_autoCommand = new ExampleCommand(m_exampleSubsystem);
    private final DriveRobotOriented driveRO = new DriveRobotOriented(driveSubsystem);
    private final DriveFieldOriented driveFO = new DriveFieldOriented(driveSubsystem);
    private final DriveAutonomous driveA = new DriveAutonomous(driveSubsystem);
    private final InstantCommand resetGyro = new InstantCommand(driveSubsystem::resetGyro, driveSubsystem);
    private final ClimbDown climbDown = new ClimbDown(climberSubsystem);
    private final ClimbUp climbUp = new ClimbUp(climberSubsystem);
    private final RunFlywheel runFlywheel = new RunFlywheel(flywheelSubsystem);
    private final RunSkrungles runSkrungles = new RunSkrungles(skrunglesSubsystem);
    private final RunConveyorForward runConveyorForward = new RunConveyorForward(conveyorSubsystem);
    private final RunConveyorReverse runConveyorReverse = new RunConveyorReverse(conveyorSubsystem);
    private final RunIndexWheel runIndex = new RunIndexWheel(indexSubsystem);
    // private final ParallelRaceGroup runIndexTimed = new ParallelRaceGroup(runIndex, new WaitCommand(1));
    private final InstantCommand displayDriverVideo = new InstantCommand(driverCamera::startCamera, driverCamera);
    private final InstantCommand displayConveyorVideo = new InstantCommand(conveyorCamera::startCamera, conveyorCamera);
    private final AimWithLimelight aimWithLimelight = new AimWithLimelight(driveSubsystem, limelight);
    // private final SequentialCommandGroup aimAndShoot = new SequentialCommandGroup(aimWithLimelight, runIndex);
    
    public static XboxController controller = new XboxController(Constants.CONTROLLER_NUMBER);

    /** The container for the robot. Contains subsystems, OI devices, and commands. */
    public RobotContainer() {
        configureButtonBindings();

        driveSubsystem.setDefaultCommand(driveRO);
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
        POVButton dPadUp = new POVButton(controller, 0);
        POVButton dPadRight= new POVButton(controller, 90);        
        POVButton dPadDown = new POVButton(controller, 180);
        POVButton dPadLeft = new POVButton(controller, 270);

        /* TODO: Change these buttons when a more convenient controller layout becomes obvious */
        /* Buttons for the climber in a box */
        dPadUp.whileTrue(climbUp);
        dPadDown.whileTrue(climbDown);

        /* Buttons for shooter/intake */
        leftBumper.whileTrue(runSkrungles.alongWith(runConveyorForward));
        // aAndB.whileTrue(runIndex);
        xButton.whileTrue(runConveyorReverse);
        /*xButton.whileTrue(new ConditionalCommand(runConveyorForward, runConveyorReverse, colorSensor::canShoot));*/

        /*xButton.whenPressed(new InstantCommand(flywheelSubsystem::testFlywheel, flywheelSubsystem));
        yButton.whenPressed(new InstantCommand(flywheelSubsystem::stopFlywheel, flywheelSubsystem));*/

        aButton.whileTrue(aimWithLimelight);
        bButton.whileTrue(runIndex); 

        //backButton.whenPressed(resetGyro);

        //leftStick.toggleWhenPressed(driveFO);


    }
    

    /**
     * Use this to pass the autonomous command to the main {@link Robot} class.
     *
     * @return the command to run in autonomous
     */
    public Command getAutonomousCommand() {
        /* Autonomous runs driveA then Shooter */
        return new AimWithLimelight(driveSubsystem, limelight).withTimeout(5).andThen(new RunIndexWheel(indexSubsystem).withTimeout(5));
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
        //return runFlywheel;
        return new InstantCommand(flywheelSubsystem::testFlywheel, flywheelSubsystem);
    }

    public Command getRunIndexCommand() {
        return runIndex;
    }

    public Command getLimelightCommand() {
        return aimWithLimelight;
    }
    
    public Command getDisplayDriverVideoCommand() {
        return displayDriverVideo;
    }

    public Command getDisplayConveyorVideoCommand() {
        return displayConveyorVideo;
    }

    public static double getLeftXAxis() {
        return controller.getLeftX();
    }

    public static double getScaledLeftXAxis() {
        return scaleAxis(getLeftXAxis());
       }


    public static double getLeftYAxis() {
        return controller.getLeftY() * -1.0;
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
        return controller.getRightY() * -1.0;
    }

    public static double getScaledRightYAxis() {
        return scaleAxis(getRightYAxis());
    }

    private static double scaleAxis(double a) {
        return Math.signum(a) * Math.pow(a, 2);
    }

}
