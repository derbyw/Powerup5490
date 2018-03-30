package org.usfirst.frc.team5490.robot.commands;

import org.usfirst.frc.team5490.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *  lower lift until we reach the lower limit switch -- this calibrates the lift position
 */
public class LiftFindBottom extends Command {
	
	private double rate = 0.25;

    public LiftFindBottom() {
        requires(Robot.m_Lift);
        
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
		Robot.m_Lift.lower(rate);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return Robot.m_Lift.isAtBottom();        
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.m_Lift.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
