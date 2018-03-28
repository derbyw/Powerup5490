package org.usfirst.frc.team5490.robot.commands;

import org.usfirst.frc.team5490.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class GripperRelease extends Command {
	
	private  static final double release_duration = 0.4;		// seconds
	private  static double release_time;		// seconds
	
	private static final double open_speed = 0.4;

    public GripperRelease() {
    	requires(Robot.m_Gripper);
    	
    	
    	
    	release_time = 0.0;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	///back off 5 degrees    	
    	//Robot.m_Gripper.SetDistanceSetpoint(13, true);	// open to 13 inches in quadrant I
		//Robot.m_Gripper.enable();    	
    	Robot.m_Gripper.disable();	// no PID anymore
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.m_Gripper.manual_open(open_speed);
    	release_time += 0.02;
    }

    // Make this return true when this Command no longer needs to run execute()    
    protected boolean isFinished() {
    	return (release_time > release_duration);
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.m_Gripper.disable();
    	Robot.m_Gripper.manual_stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
