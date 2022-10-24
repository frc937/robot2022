package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.SparkMaxRelativeEncoder;
import com.revrobotics.CANSparkMax.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Flywheel extends SubsystemBase {

    private CANSparkMax flywheel;
    private RelativeEncoder flywheelEncoder;
    private SparkMaxPIDController flywheelPID;

    /** Creates a new Flywheel. */
    public Flywheel() {
        flywheel = new CANSparkMax(Constants.ID_SPARKMAX_FLYWHEEL, MotorType.kBrushed);

        flywheelEncoder = flywheel.getEncoder(SparkMaxRelativeEncoder.Type.kQuadrature, 8192);

        flywheelPID = flywheel.getPIDController();

        /* 
         * *************************************************************************************
         * ONLY COMMENT THIS IF YOU ARE GOING TO TRY AND TUNE THINGS WITH REV CLIENT
         * *************************************************************************************
         */
        flywheelPID.setP(Constants.kFLYWHEEL_P);
        flywheelPID.setI(Constants.kFLYWHEEL_I);
        flywheelPID.setD(Constants.kFLYWHEEL_D);
        flywheelPID.setFF(Constants.kFLYWHEEL_FF);
        flywheel.burnFlash();


        SmartDashboard.putNumber("Flywheel setpoint", Constants.FLYWHEEL_SPEED);
    }

    public void setVelocity(double velocity) {
        flywheelPID.setReference(velocity, ControlType.kVelocity);
    }

    // public void testFlywheel() {
    //     flywheel.set(Constants.FLYWHEEL_PERCENT_OUT_SPEED);
    // }

    public void stopFlywheel() {
        flywheel.set(0);
    }

    /**
     * Subsystem periodic, called once per scheduler run. Not used here.
     */
    @Override
    public void periodic() {
        /*
         * *************************************************************************************************************************************************************
         * WHATEVER YOU DO, COMMENT THIS CODE OUT BEFORE DRIVING THE BOT AT COMP OR COW TOWN. It spams the crap out of NetworkTables, which FMS will NOT take kindly to.
         * *************************************************************************************************************************************************************
         */
        SmartDashboard.putNumber("Flywheel speed", flywheelEncoder.getVelocity());
        /*System.out.println(SmartDashboard.getNumber("Flywheel setpoint", Constants.FLYWHEEL_SPEED));*/
    }
    
}
