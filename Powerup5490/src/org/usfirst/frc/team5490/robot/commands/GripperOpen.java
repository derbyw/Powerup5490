package org.usfirst.frc.team5490.robot.commands;

import org.usfirst.frc.team5490.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class GripperOpen extends Command {

    public GripperOpen() {
    	requires(Robot.m_Gripper);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.m_Gripper.open(1);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return Robot.m_Gripper.isOpen();        
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.m_Gripper.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
