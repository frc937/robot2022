package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.Encoder;

import frc.robot.Constants;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class Climber extends SubsystemBase {

    /* Variables */
    private CANSparkMax winch0;
    private CANSparkMax winch1;
    private double winchSpeed;

    private Encoder winchEncoder;
    
    /* Creates a new Climber Subsystem */
    public Climber() {

        /* Initializes winch motor */
        winch0 = new CANSparkMax(Constants.ID_SPARKMAX_WINCH_0, MotorType.kBrushed);
        winch1 = new CANSparkMax(Constants.ID_SPARKMAX_WINCH_1, MotorType.kBrushed);

        winchEncoder = new Encoder(Constants.DIO_PIN_WINCH_ENCODER_0, Constants.DIO_PIN_WINCH_ENCODER_1);

    }

    public void runClimberForward() {
        winch0.set(Constants.WINCH_SPEED);
        winch1.follow(winch0);
    }

    public void stopClimber() {
        winch0.set(0);
        winch1.follow(winch0);
    }

    public void reset() {

        winchSpeed = -0.30;
        winch0.set(winchSpeed);
        winch1.follow(winch0);

    }


}
