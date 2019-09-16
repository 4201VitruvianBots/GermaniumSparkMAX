/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

/**
 *
 */
public class SetArcadeDrive extends Command {
    public SetArcadeDrive() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.driveTrain);
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() { }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
        double throttle = Math.abs(Robot.m_oi.leftJoystick.getY()) < 0.05 ? 0 : -Robot.m_oi.leftJoystick.getY();
        double turn = Math.abs(Robot.m_oi.rightJoystick.getX()) < 0.05 ? 0 : Robot.m_oi.rightJoystick.getX();


        Robot.driveTrain.setArcadeDrive(throttle, turn);
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
        Robot.driveTrain.setDriveMotors(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
        end();
    }
}
