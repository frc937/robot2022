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


    /* 
     * ***************
     * Drive constants
     * ***************
     */
    /* Drive motor CAN IDs */
    public static final int ID_TALON_FRONT_LEFT = 0;
    public static final int ID_TALON_FRONT_RIGHT = 1;
    public static final int ID_TALON_REAR_LEFT = 2;
    public static final int ID_TALON_REAR_RIGHT = 3;

    /* 
     * Sensor phases for each motor controller.
     * These are in the order of their CAN IDs, but because of the way arrays work, they are off by one from their actual IDs.
     * For example, in order to get the phase for the FL controller, one would call DRIVE_SENSOR_PHASE[Constants.ID_TALON_FRONT_LEFT + 1].
     */
    public static final boolean[] DRIVE_SENSOR_PHASE = {false, false, false, false};

    /* 
     * Invert values for each motor controller.
     * Off by one, see above.
     */
    public static final boolean[] DRIVE_INVERTED = {false, false, false, false};

    /* 
     * Gains for drive PID.
     * Format is DRIVE_GAINS[kP, kI, kD, kF]
     */
    public static final double[] DRIVE_GAINS = {0.0, 0.0, 0.0, 0.0};

    /* SPARK MAX CAN IDs for shooter */
    public static final int ID_SPARKMAX_FLYWHEEL = 4;
    public static final int ID_SPARKMAX_CONTROL_WHEEL = 5;

    /* SPARK MAX CAN IDs for climber */
    public static final int ID_SPARKMAX_WINCH_0 = 6;
    public static final int ID_SPARKMAX_WINCH_1 = 7;

    /* DIO pin numbers for shooter flywheel encoder */
    public static final int DIO_PIN_FLYWHEEL_ENCODER_0 = 0;
    public static final int DIO_PIN_FLYWHEEL_ENCODER_1 = 1;

    /* DIO pin numbers for climber winch encoder */
    public static final int DIO_PIN_WINCH_ENCODER_0 = 2;
    public static final int DIO_PIN_WINCH_ENCODER_1 = 3;

    /* PID gains for shooter flywheel */
    public static final int kFLYWHEEL_P = 1;
    public static final int kFLYWHEEL_I = 0;
    public static final int kFLYWHEEL_D = 0;

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
    public static final double CONTROL_WHEEL_SPEED = 0.1;

    /* Speed to run the flywheel at. I THINK it's in RPM. */
    public static final double FLYWHEEL_SETPOINT = 10;

    /* Speed to run the winch at. Supposedly it's in RPM */
    public static final double WINCH_SPEED = 0.5;

    /* Shooter/intake color sensor calibration values */
    /* TODO: these are stolen from REV's examples, and won't be accurate to the actual ball colors. calibrate them. */
    public static final double[] RED_BALL = {0.561, 0.232, 0.114};
    public static final double[] BLUE_BALL = {0.143, 0.427, 0.429};

    /* Intake sparkmax IDs */
    public static final int ID_SPARKMAX_SKRUNGLES = 6;
    public static final int ID_SPARKMAX_CONVEYOR = 7;

    /* Intake speeds */
    public static final double SKRUNGLE_SPEED = 0.1;
    public static final double CONVEYOR_SPEED = 0.1;
    
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
