package org.usfirst.frc.team5490.robot.commands;

import org.usfirst.frc.team5490.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ResetPID extends Command {

    public ResetPID() {
    	requires(Robot.m_Lift);
    	requires(Robot.m_Gripper);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.m_Gripper.gripperEncoder.reset();
    	Robot.m_Lift.liftEncoder.reset();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.m_Gripper.getPosition() == 0 && Robot.m_Lift.getPosition() == 0;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
