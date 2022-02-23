// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;

import com.revrobotics.ColorSensorV3;
import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;

public class Intake extends SubsystemBase {
    private CANSparkMax scrungles;
    private CANSparkMax conveyor;

    private I2C.Port colorSensorPort;
    private ColorSensorV3 colorSensor;
    private ColorMatch colorMatcher;

    private Color redBall;
    private Color blueBall;

    private SendableChooser<Color> allianceChoice;

    /** Creates a new Intake. */
    public Intake() {
        scrungles = new CANSparkMax(Constants.ID_SPARKMAX_SCRUNGLES, MotorType.kBrushed);
        conveyor = new CANSparkMax(Constants.ID_SPARKMAX_CONVEYOR, MotorType.kBrushed);

        scrungles.setInverted(false);
        conveyor.setInverted(false);

        /* TODO: This might not be the right port */
        colorSensorPort = I2C.Port.kOnboard;

        colorSensor = new ColorSensorV3(colorSensorPort);

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

    public void runScrungles() {
        scrungles.set(Constants.SCRUNGLE_SPEED);
    }

    public void stopScrungles() {
        scrungles.set(0);
    }

    public void runConveyorForward() {
        conveyor.set(Constants.CONVEYOR_SPEED);
    }

    public void runConveyorReverse() {
        conveyor.set(Constants.CONVEYOR_SPEED * -1);
    }

    public void stopConveyor() {
        conveyor.set(0);
    }

    public void stopAll() {
        scrungles.set(0);
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
