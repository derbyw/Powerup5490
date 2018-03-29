package org.usfirst.frc.team5490.robot.commands;

import org.usfirst.frc.team5490.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class WinchToOperate extends Command {

	private  static final double max_store_duration = 2.0;		// seconds
	private  static double winch_time;		// seconds

    public WinchToOperate() {
    	requires(Robot.m_Winch);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	winch_time = 0.0;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	winch_time += 0.02;
    	Robot.m_Winch.wind();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return (Robot.m_Winch.isLiftVertical()|| (winch_time > max_store_duration));       
    	
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.m_Winch.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
