package org.usfirst.frc.team5490.robot.commands;

import org.usfirst.frc.team5490.robot.HermiteSpline;
import org.usfirst.frc.team5490.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DrivePath extends Command {
	
	private HermiteSpline[] m_path;
	private double unit_step_per_tick;	// units per tick
	private double scan_rate = 0.02;	// 20 ms update rate
										// units per tick
	private double segment;				// what segment are we in
	
	

    public DrivePath(HermiteSpline[] path, double unit_step_second) {
    	m_path = path;
    	segment = 0;
    	unit_step_per_tick = unit_step_second * scan_rate;
    	
    	requires(Robot.m_Chassis);    
    	
    }
    
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {    	
    	//Robot.m_Chassis.Drive(Robot.m_oi.getJoystick());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.m_Chassis.StopMotors();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    		end();
    }

}
