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

        /* Initializes winch motor */
        winchEncoder = winch0.getEncoder();

        winchPID = winch0.getPIDController();

        /* This may need to set more constants, like setIZone(), setFF(), or setOutputRange(). idk for now. */
        winchPID.setP(Constants.kWINCH_P);
        winchPID.setI(Constants.kWINCH_I);
        winchPID.setD(Constants.kWINCH_D);
        winch0.burnFlash();

    }

    public void setVelocity(double velocity) {
        winchPID.setReference(velocity, ControlType.kVelocity);
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

    /**
     * Safety method to bring ALL motors controlled by this subsystem to a complete stop.
     */
    public void stopAll() {
        winch0.set(0);
        winch1.follow(winch0);
    }

}
