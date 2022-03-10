// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;

import com.revrobotics.ColorSensorV3;
import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;

/**
 * Intake subsystem. Handles Wibbly Skrungles, the initial mechanism to bring balls off the floor and into the robot, and the conveyor that moves and indexes balls within the robot.
 */
public class Intake extends SubsystemBase {
    private CANSparkMax skrungles;
    private CANSparkMax conveyor;

    private I2C multiplexer;
    private ColorSensorV3 colorSensor;
    private ColorMatch colorMatcher;

    private Color redBall;
    private Color blueBall;

    private SendableChooser<Color> allianceChoice;

    /** Creates a new Intake. */
    public Intake() {
        skrungles = new CANSparkMax(Constants.ID_SPARKMAX_SKRUNGLES, MotorType.kBrushed);
        conveyor = new CANSparkMax(Constants.ID_SPARKMAX_CONVEYOR, MotorType.kBrushed);

        skrungles.setInverted(false);
        conveyor.setInverted(false);

        /* TODO: This might not be the right port */
        multiplexer = new I2C(I2C.Port.kOnboard, 0x70);

        multiplexer.write(0x70, 1 << Constants.COLOR_SENSOR_PORT);
        colorSensor = new ColorSensorV3(Port.kOnboard);

        colorMatcher = new ColorMatch();
        
        redBall = ColorMatch.makeColor(Constants.RED_BALL[0], Constants.RED_BALL[1], Constants.RED_BALL[2]);
        blueBall = ColorMatch.makeColor(Constants.BLUE_BALL[0], Constants.BLUE_BALL[1], Constants.BLUE_BALL[2]);

        colorMatcher.addColorMatch(redBall);
        colorMatcher.addColorMatch(blueBall);

        /* this should maybe done somewhere in init so we can have access to the alliance globally, but this is fine for now */
        allianceChoice.setDefaultOption("Red", redBall);
        allianceChoice.addOption("Blue", blueBall);

        SmartDashboard.putData(allianceChoice);
    }

    /**
     * Runs the Wibbly Skrungles.
     */
    public void runSkrungles() {
        skrungles.set(Constants.SKRUNGLE_SPEED);
    }

    /**
     * Stops the Wibbly Skrungles.
     */
    public void stopSkrungles() {
        skrungles.set(0);
    }

    /**
     * Runs the conveyor forward, to move balls further into the robot and closer to the shooter.
     */
    public void runConveyorForward() {
        conveyor.set(Constants.CONVEYOR_SPEED);
    }

    /**
     * Runs the conveyor backwards, to move balls out of the robot.
     */
    public void runConveyorReverse() {
        conveyor.set(Constants.CONVEYOR_SPEED * -1);
    }

    /**
     * Stops the conveyor.
     */
    public void stopConveyor() {
        conveyor.set(0);
    }

    /**
     * Safety method that stops all motors associated with this subsystem.
     */
    public void stopAll() {
        skrungles.set(0);
        conveyor.set(0);
    }

    /**
     * Returns true if the ball loaded into the robot is the same color as our alliance color.
     * 
     * <p>This is so that we don't shoot a ball that isn't our alliance color, since if we score with
     * the opposing alliance's ball, they get the point.
     * @return If we can shoot without potentially scoring for the opposing alliance
     */
    public boolean canShoot() {
        Color detectedColor = colorSensor.getColor();
        ColorMatchResult colorMatchResult = colorMatcher.matchClosestColor(detectedColor);

        return colorMatchResult.color.equals(allianceChoice);
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }
}
