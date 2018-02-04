package org.usfirst.frc.team5490.robot.commands;

import org.usfirst.frc.team5490.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Move the lift (and gripper) to a given location. This command finishes when it is
 * within the tolerance, but leaves the PID loop running to maintain the
 * position. Other commands using the lift should make sure they disable
 * PID!
 * 
 * Notes - any or all will cause mayhem with the control..
 * 
 *     when things go wrong with the control, check:
 *     	encoder wires, connected
 *      encoder wires swapped (A/B)
 *      motor wires swapped (A/B)
 */
public class LiftSetpoint extends Command {
	private double m_setpoint;
	
	private double pid_active_range = 25; // this is +/- mm, where we want to do PID control 
	private int ramp_length = 10;
	private int velocity_step;
	private double[] S_Curve;
	
	private enum ControlStates {
		Accelerate_Up,
		Fast_Up,
		PID,
		Fast_Down,		
		Accelerate_Down,
	}
	
	private ControlStates m_state;

	public LiftSetpoint(double setpoint) {
		m_setpoint = setpoint;
		
		
		S_Curve = new double[ramp_length];
		
		m_state = ControlStates.PID;
		
		requires(Robot.m_Lift);
	}
	
	
	private void Create_Ramp() {		
		double step = Math.PI / S_Curve.length;
		// use a 0..Pi sine wave for the motor curve
		for(int i = 0; i < S_Curve.length; i++) {
			S_Curve[i] = Math.sin(step);
		}	
	}

	// Determines the initial state for the move request
	private ControlStates CheckState()
	{
		// where are we to start
		double required_delta = Robot.m_Lift.getSetpoint() - Robot.m_Lift.getPosition();
		
		if (required_delta > 0)  {
			if (required_delta > pid_active_range)  {
				return ControlStates.Accelerate_Up;
			} else {
				return ControlStates.PID;
			}			
		} else {
			if (required_delta < (-1 * pid_active_range* -1))  {
				return  ControlStates.Accelerate_Down;
			} else {
				return  ControlStates.PID;
			}
		}		
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		
		Create_Ramp();
		
		velocity_step = 0;		

		m_state = CheckState();
		
		// if we are starting in PID mode, then just kick it off here
		if (m_state == ControlStates.PID) {		
			Robot.m_Lift.enable();
			Robot.m_Lift.setSetpoint(m_setpoint);
		}
	}
	
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	// use absolute value to make comparisons less confusing..
    	double required_delta = Math.abs(Robot.m_Lift.getSetpoint() - Robot.m_Lift.getPosition());
    	
    	switch(m_state) {
	    	case Accelerate_Up:
	    		// follow velocity curve to get to full output
	    		Robot.m_Lift.raise(S_Curve[velocity_step++]);
	    		
	    		if (required_delta > pid_active_range)  {
	    			if (velocity_step >= S_Curve.length) m_state = ControlStates.Fast_Up;
	    		} else {
	    			m_state = ControlStates.PID;
	    		}	    		
	    		break;
	    	case Fast_Up:
	    		// full output up....
	    		Robot.m_Lift.raise(1);
	    		if (required_delta <= pid_active_range)  {
	    			m_state = ControlStates.PID;
	    		}
	    		break;
	    	case PID:
	    		// nothing to do and no escape once we're here
	    		// control runs in background 
	    		break;
	    	case Fast_Down:
	    		 // full output down....
	    		Robot.m_Lift.lower(1);  
	    		if (required_delta <= pid_active_range)  {
	    			m_state = ControlStates.PID;
	    		}
	    		break;
	    	case Accelerate_Down:	
	    		// follow velocity curve to get to full output
	    		Robot.m_Lift.lower(S_Curve[velocity_step++]);
	    		if (required_delta > pid_active_range)  {
	    			if (velocity_step >= S_Curve.length) m_state = ControlStates.Fast_Down;
	    		} else {
	    			m_state = ControlStates.PID;
	    		}	
	    		break;
    	}
    }

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return Robot.m_Lift.onTarget();
	}
}
