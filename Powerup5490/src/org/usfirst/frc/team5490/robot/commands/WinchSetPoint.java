package org.usfirst.frc.team5490.robot.commands;

import org.usfirst.frc.team5490.robot.EnhancedPIDSetpoint;
import org.usfirst.frc.team5490.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.PIDSubsystem;

/**
 *
 */
public class WinchSetPoint extends Command {

	private static PIDSubsystem pid_subsystem = Robot.m_Winch;
	private static double pid_active_range = 400;
	private static int ramp_length = 100;
	
	private static double m_setpoint;
	
	private EnhancedPIDSetpoint setpoint = 
			new EnhancedPIDSetpoint(pid_subsystem, pid_active_range, ramp_length);
	
	public WinchSetPoint(double setpoint) {
		m_setpoint = setpoint;
	}
	
	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		setpoint.initialize(m_setpoint);
	}
	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		setpoint.execute();
	}
	
	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return setpoint.isFinished();
	}
	
}
