package org.usfirst.frc.team5490.robot.commands;

import org.usfirst.frc.team5490.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Command for manually opening the gripper.
 */
public class GripperOpen extends Command {

	public double rateL;
	public double rateR;
	
	public GripperOpen() {
    	requires(Robot.m_Gripper);
    	this.rateL = 0.05;
    	this.rateR = 0.05;
    }
	
    public GripperOpen(double rateL, double rateR) {
    	requires(Robot.m_Gripper);
    	this.rateL = rateL;
    	this.rateR = rateR;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.m_Gripper.open(rateL, rateR);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return false;
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
