// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {

    /*
     * ****************************
     * TODO: TUNE ALLLLLLL OF THESE
     * ****************************
     */

    /* Drive motor CAN IDs */
    /* Officially TUNED */
    public static final int ID_TALON_FRONT_LEFT = 2;
    public static final int ID_TALON_FRONT_RIGHT = 3;
    public static final int ID_TALON_REAR_LEFT = 1;
    public static final int ID_TALON_REAR_RIGHT = 4;

    /* Drive scale factor */
    public static final double DRIVE_SCALE = 0.5;

    /* SPARK MAX CAN IDs for shooter */
    public static final int ID_SPARKMAX_FLYWHEEL = 7;
    public static final int ID_SPARKMAX_CONTROL_WHEEL = 5;

    /* SPARK MAX CAN IDs for climber */
    /* Currently dummy IDs since we're saving climbers for cow town */
    public static final int ID_SPARKMAX_WINCH_0 = 9;
    public static final int ID_SPARKMAX_WINCH_1 = 10;

    /* DIO pin numbers for shooter flywheel encoder */
    public static final int DIO_PIN_FLYWHEEL_ENCODER_0 = 0;
    public static final int DIO_PIN_FLYWHEEL_ENCODER_1 = 1;

    /* DIO pin numbers for climber winch encoder */
    public static final int DIO_PIN_WINCH_ENCODER_0 = 2;
    public static final int DIO_PIN_WINCH_ENCODER_1 = 3;

    /* PID gains for shooter flywheel */
    public static final double kFLYWHEEL_P = 1;
    public static final double kFLYWHEEL_I = 0;
    public static final double kFLYWHEEL_D = 0;
    public static final double kFLYWHEEL_FF= 0.000245;

    /* PID gains for climber winch */
    public static final int kWINCH_P = 1;
    public static final int kWINCH_I = 0;
    public static final int kWINCH_D = 0;

    /* Flywheel feedforward settings */
    /* 
     * This, like the I and D above, may not actually be necessary for what we want to do.
     * I'm going to do more research into PID and figure out if we actually need this or not.
     */
    public static final double FLYWHEEL_kSVOLTS = 0;
    public static final double FLYWHEEL_kVVOLT_SECONDS_PER_ROTATION = 0;

    /* Speed to run the control wheel at */
    public static final double CONTROL_WHEEL_SPEED = 0.4;

    /* Speed to run the flywheel at. */
    public static final double FLYWHEEL_SPEED = 0.6;

    /* Multiplexer port for color sensor */
    public static final int COLOR_SENSOR_PORT = 1;

    /* Speed to run the winch at. It's a percent output setpoint */
    public static final double WINCH_SPEED_DOWN = 1.0;
    public static final double WINCH_SPEED_UP = 0.5;

    /* Shooter/intake color sensor calibration values */
    /* TODO: these are stolen from REV's examples, and won't be accurate to the actual ball colors. calibrate them. */
    public static final double[] RED_BALL = {0.561, 0.232, 0.114};
    public static final double[] BLUE_BALL = {0.143, 0.427, 0.429};

    /* Intake sparkmax IDs */
    public static final int ID_SPARKMAX_SKRUNGLES = 8;
    public static final int ID_SPARKMAX_CONVEYOR = 6;

    /* Intake speeds */
    public static final double SKRUNGLE_SPEED = 0.5;
    public static final double CONVEYOR_SPEED = 0.3;

    /* 
     * ***************
     * Limelight stuff 
     * ***************
    */
    /* The number of degrees the limelight is mounted back from perfectly vertical */
    public static final double LIMELIGHT_MOUNT_ANGLE = 25.0;

    /* The number of inches from the center of the Limelight lens to the floor */
    public static final double LIMELIGHT_MOUNT_HEIGHT = 36.5;

    /* The number of inches from the retroreflective tape on the upper hub to the floor */
    /* This is 8'8", which is what the manual says the height from the floor to the top of the upper hub is. */
    public static final double UPPER_HUB_TAPE_HEIGHT = 104.0;

    /* How far in inches we want to be from the target when we shoot */
    public static final double LIMELIGHT_DIST_FROM_TARGET = 205.0;

    /* How hard to turn toward the target. Double between 0 and 1, standard way to drive a motor */
    public static final double LIMELIGHT_STEER_STRENGTH = 0.01;

    /* How hard to drive toward the target. Same notation as above. */
    public static final double LIMELIGHT_DRIVE_STRENGTH = 0.01;

    /* VERY BASIC speed limit to make sure we don't drive too fast towards the target. Will need to be changed when implementing PID. */
    public static final double LIMELIGHT_SPEED_LIMIT = 0.2;

    /* When we're at or below this number of degrees from where we want to be, we'll consider the limelight's aim routine "done" */
    public static final double LIMELIGHT_TURN_DONE_THRESHOLD = 1.0;

    /* When we're at or below this number of inches from the target distance, we'll consider the limelight's drive routine "done" */
    public static final double LIMELIGHT_DISTANCE_DONE_THRESHOLD = 4.0;

    /* Controller ID */
    public static final int CONTROLLER_NUMBER = 0;

    /* Controller button numbers */
    public static final int A_NUMBER = 1;
    public static final int B_NUMBER = 2;
    public static final int X_NUMBER = 3;
    public static final int Y_NUMBER = 4;
    public static final int LEFT_BUMPER_NUMBER = 5;
    public static final int RIGHT_BUMPER_NUMBER = 6;
    public static final int BACK_NUMBER = 7;
    public static final int START_NUMBER = 8;
    public static final int LEFT_STICK_NUMBER = 9;
    public static final int RIGHT_STICK_NUMBER = 10;

}
