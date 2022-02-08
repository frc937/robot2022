package frc.robot.subsystems;

import frc.robot.Constants;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class Climber {

    private CANSparkMax winch;
    
    public Climber() {

        winch = new CANSparkMax(Constants.ID_SPARKMAX_WINCH, MotorType.kBrushed);

    }

    public void runClimber() {
        
        winch.set(Constants.WINCH_SPEED);

    }

    public void stopClimber() {
        winch.set(0);
    }

}
