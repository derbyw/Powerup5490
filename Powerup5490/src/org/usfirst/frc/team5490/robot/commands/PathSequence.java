package org.usfirst.frc.team5490.robot.commands;

import org.usfirst.frc.team5490.robot.PathRecord;
import org.usfirst.frc.team5490.robot.Robot;


import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *  This is a base class - do not call directly
 *  derived classes need to define the path array
 */
public class PathSequence extends Command {
	
	
	private int state;
	private int index;
	private double timer;
	private double tickrate = 0.02;
	
	private double mspeed;
	
	private boolean done = false;
	private double start_angle;
	private double target_angle;
	
	protected PathRecord[] 	path;


    public PathSequence() {
    	requires(Robot.m_Chassis);
    	

    }
    
    public PathSequence(PathRecord[] p) {
    	requires(Robot.m_Chassis);
    	
    	path = p;    
    }

    // Called just before this Command runs the first time
    protected void initialize() {    	
    	timer = 0.0;
    	state = 0;
    	index = 0;
    	done = false;
    	mspeed = 0;
    	start_angle = Robot.m_Chassis.gyro.getAngle() % 360;
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double angle = Robot.m_Chassis.gyro.getAngle() % 360;

    	switch(state) {
		case 0:						
			start_angle = angle;
			target_angle =  path[index].Z;
			if (path[index].duration == 0) {				
				state = 2;
			} else { 
				state++;
			}
			break;
		case 1:    // just x & y
			mspeed = .9 * mspeed + 0.1 * path[index].speed;
			
			double diff = angle - start_angle;
			double z = 0;
			if (Math.abs(diff) > 1) {
				if (diff > 0)
					z = .15;
				else
					z = -.15;
			}			
			
			Robot.m_Chassis.Drive(path[index].X, path[index].Y, z, mspeed);		
			
			if (timer > path[index].duration)  {
				timer = 0;				
				index++;
				if (index >= path.length) 
					state = 3;
				else
					state = 0;
			}			
			break;
		case 2:
			//double mspeed = 1 - (Math.abs(target_angle - angle) / 360) * .25; 
			double mspeed =  .25;
			
			if (target_angle > angle)
				Robot.m_Chassis.Drive(0,0,-1,mspeed);
			else
				Robot.m_Chassis.Drive(0,0,1,mspeed);				
			
			if (Math.abs(target_angle - angle) < 1) {
				timer = 0;				
				index++;
				if (index >= path.length) 
					state = 3;
				else
					state = 0;
			}			
			break;
		case 3:
			done = true;
			break;
		default:
			state =  0;
			break;
		
    	}
    	timer += tickrate;    	
    	SmartDashboard.putNumber("Path timer ", timer);
    	SmartDashboard.putNumber("Path state", state);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return done;
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
