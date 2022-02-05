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

    /* Drive motor CAN IDs */
    public static final int ID_TALON_FRONT_LEFT = 0;
    public static final int ID_TALON_FRONT_RIGHT = 1;
    public static final int ID_TALON_REAR_LEFT = 2;
    public static final int ID_TALON_REAR_RIGHT = 3;

    /* SPARK MAX CAN IDs for shooter */
    public static final int ID_SPARKMAX_FLYWHEEL = 4;
    public static final int ID_SPARKMAX_CONTROL_WHEEL = 5;

    /* DIO pin numbers for shooter flywheel encoder */
    public static final int DIO_PIN_FLYWHEEL_ENCODER_0 = 0;
    public static final int DIO_PIN_FLYWHEEL_ENCODER_1 = 1;

    /* DIO pin numbers for shooter control wheel encoder */
    public static final int DIO_PIN_CONTROL_WHEEL_ENCODER_0 = 2;
    public static final int DIO_PIN_CONTROL_WHEEL_ENCODER_1 = 3;

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
