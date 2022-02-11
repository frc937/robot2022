package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.Encoder;

import frc.robot.Constants;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class Climber extends SubsystemBase {

    /* Variables */
    private CANSparkMax winch;
    private double winchSpeed;

    private Encoder winchEncoder;
    
    /* Creates a new Climber Subsystem */
    public Climber() {

        /* Initializes winch motor */
        winch = new CANSparkMax(Constants.ID_SPARKMAX_WINCH, MotorType.kBrushed);

        winchEncoder = new Encoder(Constants.DIO_PIN_WINCH_ENCODER_0, Constants.DIO_PIN_WINCH_ENCODER_1);

    }

    public void runClimberForward() {
        winch.set(Constants.WINCH_SPEED);
    }

    public void stopClimber() {
        winch.set(0);
    }

    public void reset() {

        winchSpeed = -0.30;
        winch.set(winchSpeed);

    }


}
