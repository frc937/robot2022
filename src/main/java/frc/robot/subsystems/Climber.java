package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.Constants;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class Climber extends SubsystemBase {

    /* Variables */
    private CANSparkMax winch;
    
    /* Creates a new Climber Subsystem */
    public Climber() {

        /* Initializes winch motor */
        winch = new CANSparkMax(Constants.ID_SPARKMAX_WINCH, MotorType.kBrushed);

    }

    public void runClimberForward() {
        winch.set(Constants.WINCH_SPEED);
    }

    public void runClimberReverse() {

        winch.set(Constants.WINCH_SPEED * -1);

    }

    public void stopClimber() {
        winch.set(0);
    }

}
