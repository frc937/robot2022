// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.*;
import frc.robot.Constants;

public class Drive extends SubsystemBase {
  /** Creates a new drivetrain subsystem. */
  public Drive() {
      /* Create all our motors */
      WPI_TalonSRX frontLeft = new WPI_TalonSRX(Constants.ID_TALON_FRONT_LEFT);
      WPI_TalonSRX frontRight = new WPI_TalonSRX(Constants.ID_TALON_FRONT_RIGHT);
      WPI_TalonSRX rearLeft = new WPI_TalonSRX(Constants.ID_TALON_REAR_LEFT);
      WPI_TalonSRX rearRight = new WPI_TalonSRX(Constants.ID_TALON_REAR_RIGHT);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
