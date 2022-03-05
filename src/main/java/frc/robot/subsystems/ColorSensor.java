package frc.robot.subsystems;

import frc.robot.Constants;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;

import com.revrobotics.ColorSensorV3;
import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;

public class ColorSensor extends SubsystemBase {
    
    /* Variables */
    private I2C.Port colorSensorPort;
    private ColorSensorV3 colorSensor;
    private ColorMatch colorMatcher;

    private Color redBall;
    private Color blueBall;

    private SendableChooser<Color> allianceChoice;

    public ColorSensor () {
        
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

    public void periodic() {

        /* Gets the distance between the color sensor's IR sensor and the object */
        int distance = colorSensor.getProximity();

        /* TODO: This value is a placeholder, while it should work, it might need tweaking */
        /* Tells the driver if the ball is in the conveyor mechanism */
        if (distance > 1000) {
            SmartDashboard.putString("Conveyor", "Present");
        }
        else {
            SmartDashboard.putString("Conveyor", "Missing");
        }

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

}
