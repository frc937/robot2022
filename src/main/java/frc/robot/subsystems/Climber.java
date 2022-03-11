package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj2.command.PIDSubsystem;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;

import frc.robot.Constants;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class Climber extends SubsystemBase {

    /* Variables */
    private CANSparkMax winch0;
    private CANSparkMax winch1;
    private double winchSpeed;
    
    private CANEncoder winchEncoder;

    private CANPIDController winchPID;
    
    /* Creates a new Climber Subsystem */
    public Climber() {

        winch0 = new CANSparkMax(Constants.ID_SPARKMAX_WINCH_0, MotorType.kBrushed);
        winch1 = new CANSparkMax(Constants.ID_SPARKMAX_WINCH_1, MotorType.kBrushed);

        winch1.follow(winch0);

        /* Initializes winch motor */
        winchEncoder = winch0.getEncoder();

        winchPID = winch0.getPIDController();

        /* This may need to set more constants, like setIZone(), setFF(), or setOutputRange(). idk for now. */
        winchPID.setP(Constants.kWINCH_P);
        winchPID.setI(Constants.kWINCH_I);
        winchPID.setD(Constants.kWINCH_D);
        winch0.burnFlash();

    }

    /*
     * TODO: add something here to convert whatever units the setpoint is in to real-world units
     */
    public void setPosition(double position) {
        winchPID.setReference(position, ControlType.kPosition);
    }

    public void runClimberForward() {
        winch0.set(Constants.WINCH_SPEED);
    }

    public void stopClimber() {
        winch0.set(0);
    }

    public void reset() {

        winchSpeed = -0.30;
        winch0.set(winchSpeed);

    }

    /**
     * Safety method to bring ALL motors controlled by this subsystem to a complete stop.
     */
    public void stopAll() {
        winch0.set(0);
    }

}
