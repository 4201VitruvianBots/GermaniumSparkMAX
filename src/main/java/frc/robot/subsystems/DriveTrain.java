/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANEncoder;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import frc.robot.RobotMap;
import frc.robot.commands.SetArcadeDrive;
import frc.vitruvianlib.util.MathCustom;

/**
 *
 */
public class DriveTrain extends Subsystem {
    CANSparkMax[] driveMotors = {
        new CANSparkMax(RobotMap.leftFrontDriveMotor, MotorType.kBrushless),
        //new CANSparkMax(RobotMap.leftRearDriveMotor, MotorType.kBrushless),
        new CANSparkMax(RobotMap.rightFrontDriveMotor, MotorType.kBrushless),
        //new CANSparkMax(RobotMap.rightRearDriveMotor, MotorType.kBrushless)
    };

    Spark[] PWMDriveMotors = {
        new Spark(RobotMap.leftRearDriveMotorPWM),
        new Spark(RobotMap.rightRearDriveMotorPWM)
    };

    CANEncoder[] driveEncoders = {
        driveMotors[0].getEncoder(),
//        driveMotors[1].getEncoder(),
        driveMotors[1].getEncoder(),
//        driveMotors[3].getEncoder(),
    };

    public DriveTrain() {
        super("DriveTrain");

        for(CANSparkMax driveMotor : driveMotors) {
            driveMotor.restoreFactoryDefaults();
            driveMotor.setMotorType(MotorType.kBrushless);
            driveMotor.setSmartCurrentLimit(30);
            driveMotor.setOpenLoopRampRate(0.1);
        }

        driveMotors[0].setInverted(true);
        driveMotors[1].setInverted(false);

        PWMDriveMotors[0].setInverted(true);
        PWMDriveMotors[1].setInverted(false);
        //driveMotors[2].setInverted(false);
        //driveMotors[3].setInverted(false);

        //driveMotors[1].follow(driveMotors[0]);
        //driveMotors[3].follow(driveMotors[2]);
    }

    public void setDriveMotor(int index, double percent) {
        driveMotors[index].set(percent);
        PWMDriveMotors[index].set(percent);
    }

    public void setDriveMotors(double percent) {
        setDriveMotor(0, percent);
        setDriveMotor(1, percent);
    }

    public void setTankDrive(double left, double right) {
        setDriveMotor(0, left);
        setDriveMotor(1, right);
    }

    public void setArcadeDrive(double straight, double turn) {
        double left = straight + turn;
        double right = straight - turn;
        right = MathCustom.Clamp(right, -1, 1);
        left = MathCustom.Clamp(left, -1, 1);
        setTankDrive(left, right);
    }

    public void setPWMDriverMotor(int index, double percent) { PWMDriveMotors[index].set(percent); }

    @Override
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new SetArcadeDrive());
    }
}
