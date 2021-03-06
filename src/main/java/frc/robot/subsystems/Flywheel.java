package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMax.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Flywheel extends SubsystemBase {

    private CANSparkMax flywheel;
    /*private RelativeEncoder flywheelEncoder;*/
    private SparkMaxPIDController flywheelPID;

    /** Creates a new Flywheel. */
    public Flywheel() {
        flywheel = new CANSparkMax(Constants.ID_SPARKMAX_FLYWHEEL, MotorType.kBrushed);

        /*flywheelEncoder = flywheel.getEncoder();*/

        flywheelPID = flywheel.getPIDController();

        /* 
         * *************************************************************************************
         * ONLY COMMENT THIS BACK IN IF YOU ARE NOT GOING TO TRY AND TUNE THINGS WITH REV CLIENT
         * *************************************************************************************
         */
        /*flywheelPID.setP(Constants.kFLYWHEEL_P);
        flywheelPID.setI(Constants.kFLYWHEEL_I);
        flywheelPID.setD(Constants.kFLYWHEEL_D);
        flywheelPID.setFF(Constants.kFLYWHEEL_FF);*/
        /* TODO: set encoder counts per rev here. cpr of our encoder is 8192, ppr is 2048 */
        //flywheelEncoder.
        /*flywheel.burnFlash();*/
    }

    public void setVelocity(double velocity) {
        flywheelPID.setReference(velocity, ControlType.kVelocity);
    }

    public void testFlywheel() {
        flywheel.set(Constants.FLYWHEEL_PERCENT_OUT_SPEED);
    }

    public void stopFlywheel() {
        flywheel.set(0);
    }

    /**
     * Subsystem periodic, called once per scheduler run. Not used here.
     */
    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }
    
}
