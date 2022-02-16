package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj2.command.PIDSubsystem;
import edu.wpi.first.math.controller.PIDController;

import frc.robot.Constants;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class Climber extends PIDSubsystem {

    /* Variables */
    private CANSparkMax winch0;
    private CANSparkMax winch1;
    private double winchSpeed;

    private Encoder winchEncoder;
    
    /* Creates a new Climber Subsystem */
    public Climber() {

        super(new PIDController(Constants.kWINCH_P, Constants.kWINCH_I, Constants.kWINCH_D));

        /* Initializes winch motor */
        winch0 = new CANSparkMax(Constants.ID_SPARKMAX_WINCH_0, MotorType.kBrushed);
        winch1 = new CANSparkMax(Constants.ID_SPARKMAX_WINCH_1, MotorType.kBrushed);

        winchEncoder = new Encoder(Constants.DIO_PIN_WINCH_ENCODER_0, Constants.DIO_PIN_WINCH_ENCODER_1);

    }

    @Override
    public void useOutput(double output, double setpoint) {
        winch0.setVoltage(output + setpoint);
    }

    @Override
    public double getMeasurement() {
        return winchEncoder.getRate();
    }

    public void runClimberForward() {
        setSetpoint(Constants.WINCH_SPEED);
        winch1.follow(winch0);
    }

    public void stopClimber() {
        setSetpoint(0);
        winch1.follow(winch0);
    }

    public void reset() {

        winchSpeed = -0.30;
        setSetpoint(winchSpeed);
        winch1.follow(winch0);

    }

}